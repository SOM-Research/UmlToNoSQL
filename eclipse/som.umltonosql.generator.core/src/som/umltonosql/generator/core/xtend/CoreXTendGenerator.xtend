package som.umltonosql.generator.core.xtend

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import region.Region
import region.RegionSet
import som.umltonosql.generator.core.RegionGeneratorHelper

class CoreXTendGenerator implements IGenerator {
	

	new() {
		
	}
	
	/**
	 * Generate Java beans for UML entities (regardless the actual mapping
	 * to low-level representation of these entities)
	 */
	override doGenerate(Resource regionModel, IFileSystemAccess fsa) {
		val RegionSet rSet = regionModel.contents.get(0) as RegionSet
		val RegionGeneratorHelper helper = new RegionGeneratorHelper(rSet)
		fsa.generateFile("pom.xml",'''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0"
		         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		    <modelVersion>4.0.0</modelVersion>
		    
	        <groupId>som.umltonosql.generated</groupId>
	       	<artifactId>«rSet.name.toFirstLower»</artifactId>
	        <version>1.0-SNAPSHOT</version>
		
		    <dependencies>
		        <dependency>
		            <groupId>som.umltonosql</groupId>
		            <artifactId>core</artifactId>
		            <version>1.0-SNAPSHOT</version>
		        </dependency>
		
				<!-- Should be removed -->
		        <dependency>
		            <groupId>org.postgresql</groupId>
		            <artifactId>postgresql</artifactId>
		            <version>42.2.1</version>
		        </dependency>
		    </dependencies>
		
		
		</project>
		''')
		fsa.generateFile("src\\main\\java\\" + rSet.name.toLowerCase + "\\core\\" + rSet.name.toFirstUpper + "Middleware.java", '''
		package «rSet.name.toLowerCase».core;
		
		import som.umltonosql.core.Middleware;
		import fr.inria.atlanmod.commons.log.Log;
		import som.umltonosql.core.datastore.store.Datastore;
		import som.umltonosql.core.datastore.query.processor.QueryProcessor;
		import som.umltonosql.core.exceptions.ConsistencyException;
		import som.umltonosql.core.exceptions.LifeCycleException;
		«FOR r : rSet.regions»
		import «helper.getDatastoreImport(r)»;
		import «helper.getProcessorImport(r)»;
		«ENDFOR»
		import som.umltonosql.core.bean.Bean;
		«FOR bean : helper.beanTypes»
		import «helper.getBeanImport(bean)»;
		«ENDFOR»
		import java.text.MessageFormat;
		import java.util.Arrays;
		import java.util.List;
		import static java.util.Objects.nonNull;
		
		public class «rSet.name.toFirstUpper»Middleware extends Middleware {
			
			«FOR r : rSet.regions»
			«helper.getDatastoreType(r)» «helper.getDatastoreVariableName(r)»;
			
			«helper.getProcessorType(r)» «helper.getProcessorVariableName(r)»;
			
			«ENDFOR»
			private static «rSet.name.toFirstUpper»Middleware INSTANCE;
			
			public static «rSet.name.toFirstUpper»Middleware getInstance() {
				return INSTANCE;
			}
			
			public «rSet.name.toFirstUpper»Middleware(«getMiddlewareConstructorArguments(rSet, helper)») {
				«FOR r : rSet.regions»
				this.«helper.getDatastoreVariableName(r)» = «helper.getDatastoreVariableName(r)»
				«ENDFOR»
				«FOR r : rSet.regions»
				this.«helper.getProcessorVariableName(r)» = new «helper.getProcessorType(r)»(«helper.getProcessorArgumenst(r)»);
				«ENDFOR»
				
				if(nonNull(INSTANCE)) {
					Log.warn("Multiple instances of «rSet.name.toFirstUpper»Middleware have been created");
				}
				INSTANCE = this;
			}
			
			@Override
			public List<Datastore> getDatastores() {
				return Arrays.asList(«getDatastoreVariablesAsParameters(rSet, helper)»);
			}
			
			@Override
			public List<QueryProcessor> getProcessors() {
				return Arrays.asList(«getProcessorVariablesAsParameters(rSet, helper)»);
			}
			
			«FOR r : rSet.regions»
			public «helper.getDatastoreType(r)» get«helper.getDatastoreVariableName(r).toFirstUpper»() {
				return this.«helper.getDatastoreVariableName(r)»;
			}
			
			public «helper.getProcessorType(r)» get«helper.getProcessorVariableName(r).toFirstUpper»() {
				return this.«helper.getProcessorVariableName(r)»
			}
			
			«ENDFOR»
			
			«FOR b : helper.beanTypes»
			«val r = helper.getRegionForBean(b)»
			public «b» create«b»() {
				return («b») «helper.getDatastoreVariableName(r)».createElement(«b».class);
			}
			
			«ENDFOR»
			@Override
			public Bean getElement(String id, Class<? extends Bean> clazz) throws ConsistencyException {
				«FOR b : helper.beanTypes»
				«val r = helper.getRegionForBean(b)»
				if(clazz.equals(«b».class)) {
					return get«b»(id);
				}
				«ENDFOR»
				throw new ConsistencyException(MessageFormat.format("Cannot get the element with the provided class : {0}", clazz.getName()));
			}
			
			«FOR b : helper.beanTypes»
			«val r = helper.getRegionForBean(b)»
			public «b» get«b»(String id) {
				return («b») «helper.getDatastoreVariableName(r)».getElement(id, «b».class);
			}
			
			«ENDFOR»
			
			@Override
			public void commit() throws LifeCycleException {
				try {
					«FOR r : rSet.regions»
					«helper.getDatastoreVariableName(r)».commit();
					«ENDFOR»
				} catch(Exception e) {
					throw new LifeCycleException("An error occured during the committing operations", e);
				}
			}
		}
		''')
	}
	
	def String getMiddlewareConstructorArguments(RegionSet rSet, RegionGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < rSet.regions.size; i++) {
			sb.append(helper.getDatastoreType(rSet.regions.get(i)))
			sb.append(' ')
			sb.append(helper.getDatastoreVariableName(rSet.regions.get(i)))
			if(i < rSet.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	def String getDatastoreVariablesAsParameters(RegionSet rSet, RegionGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < rSet.regions.size; i++) {
			sb.append(helper.getDatastoreVariableName(rSet.regions.get(i)))
			if(i < rSet.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	def String getProcessorVariablesAsParameters(RegionSet rSet, RegionGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < rSet.regions.size; i++) {
			sb.append(helper.getProcessorVariableName(rSet.regions.get(i)))
			if(i < rSet.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	
}
