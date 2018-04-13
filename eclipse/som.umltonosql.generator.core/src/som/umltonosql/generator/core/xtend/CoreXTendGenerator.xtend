package som.umltonosql.generator.core.xtend

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import region.Partition
import som.umltonosql.generator.helper.CoreGeneratorHelper
import som.umltonosql.generator.helper.DatastoreHandlerHelper
import som.umltonosql.generator.util.CoreGeneratorUtil

class CoreXTendGenerator implements IGenerator {
	

	new() {
		
	}
	
	/**
	 * Generate Java beans for UML entities (regardless the actual mapping
	 * to low-level representation of these entities)
	 */
	override doGenerate(Resource regionModel, IFileSystemAccess fsa) {
		val Partition partition = regionModel.contents.get(0) as Partition
		val CoreGeneratorHelper helper = new CoreGeneratorHelper(partition)
		fsa.generateFile("pom.xml",'''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0"
		         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		    <modelVersion>4.0.0</modelVersion>
		    
	        <groupId>som.umltonosql.generated</groupId>
	       	<artifactId>«partition.name.toFirstLower»</artifactId>
	        <version>1.0-SNAPSHOT</version>
	        
	        <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
            </properties>
		
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
		fsa.generateFile("src\\main\\java\\" + partition.name.toLowerCase + "\\" + CoreGeneratorUtil.instance.basePackage + "\\" + partition.name.toFirstUpper + "Middleware.java", '''
		package «partition.name.toLowerCase».«CoreGeneratorUtil.instance.basePackage»;
		
		import som.umltonosql.core.Middleware;
		import fr.inria.atlanmod.commons.log.Log;
		import som.umltonosql.core.datastore.store.Datastore;
		import som.umltonosql.core.datastore.query.processor.QueryProcessor;
		import som.umltonosql.core.exceptions.ConsistencyException;
		import som.umltonosql.core.exceptions.LifeCycleException;
		«FOR r : partition.regions»
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
		
		public class «partition.name.toFirstUpper»Middleware extends Middleware {
			
			«FOR r : partition.regions»
			«helper.getDatastoreType(r)» «helper.getDatastoreVariableName(r)»;
			
			«helper.getProcessorType(r)» «helper.getProcessorVariableName(r)»;
			
			«ENDFOR»
			private static «partition.name.toFirstUpper»Middleware INSTANCE;
			
			public static «partition.name.toFirstUpper»Middleware getInstance() {
				return INSTANCE;
			}
			
			public «partition.name.toFirstUpper»Middleware(«getMiddlewareConstructorArguments(partition, helper)») {
				«FOR r : partition.regions»
				this.«helper.getDatastoreVariableName(r)» = «helper.getDatastoreVariableName(r)»;
				«ENDFOR»
				«FOR r : partition.regions»
				this.«helper.getProcessorVariableName(r)» = new «helper.getProcessorType(r)»(«helper.getProcessorArgumenst(r)»);
				«ENDFOR»
				
				if(nonNull(INSTANCE)) {
					Log.warn("Multiple instances of «partition.name.toFirstUpper»Middleware have been created");
				}
				INSTANCE = this;
			}
			
			@Override
			public List<Datastore> getDatastores() {
				return Arrays.asList(«getDatastoreVariablesAsParameters(partition, helper)»);
			}
			
			@Override
			public List<QueryProcessor> getProcessors() {
				return Arrays.asList(«getProcessorVariablesAsParameters(partition, helper)»);
			}
			
			«FOR r : partition.regions»
			public «helper.getDatastoreType(r)» get«helper.getDatastoreVariableName(r).toFirstUpper»() {
				return this.«helper.getDatastoreVariableName(r)»;
			}
			
			public «helper.getProcessorType(r)» get«helper.getProcessorVariableName(r).toFirstUpper»() {
				return this.«helper.getProcessorVariableName(r)»;
			}
			
			«ENDFOR»
			@Override
			public Bean createElement(Class<? extends Bean> clazz) throws ConsistencyException {
				«FOR b : helper.beanTypes»
				if(clazz.equals(«b».class)) {
					return create«b»();
				}
				«ENDFOR»
				throw new ConsistencyException(MessageFormat.format("Cannot create the element with the provided class: {0}", clazz.getName()));
			}
			
			«FOR b : helper.beanTypes»
			«val r = helper.getRegionForBean(b)»
			public «b» create«b»() {
				return («b») «helper.getDatastoreVariableName(r)».createElement(«b».class);
			}
			
			«ENDFOR»
			@Override
			public Bean getElement(String id, Class<? extends Bean> clazz) throws ConsistencyException {
				«FOR b : helper.beanTypes»
				if(clazz.equals(«b».class)) {
					return get«b»(id);
				}
				«ENDFOR»
				throw new ConsistencyException(MessageFormat.format("Cannot get the element with the provided class: {0}", clazz.getName()));
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
					«FOR r : partition.regions»
					«helper.getDatastoreVariableName(r)».commit();
					«ENDFOR»
				} catch(Exception e) {
					throw new LifeCycleException("An error occured during the committing operations", e);
				}
			}
		}
		''')
		fsa.generateFile("src\\main\\java\\" + partition.name.toLowerCase + "\\" + CoreGeneratorUtil.instance.basePackage + "\\" + partition.name.toFirstUpper + "Bootstrap.java", '''
		package «partition.name.toLowerCase».«CoreGeneratorUtil.instance.basePackage»;
		
		import som.umltonosql.core.Bootstrap;
		import som.umltonosql.core.LifeCycleManager;
		import java.util.Arrays;
		
		«FOR r : partition.regions»
		import «DatastoreHandlerHelper.getHandlerImport(r)»;
		import «helper.getDatastoreImport(r)»;
		«ENDFOR» 
		
		public class «partition.name.toFirstUpper»Bootstrap extends Bootstrap {
			
			@Override
			public void init() {
				lcManager = new LifeCycleManager(Arrays.asList(
					«getHandlersInvocationAsParameters(partition)»
				));
				
				// Creates the datastores
				«FOR r : partition.regions»
				«helper.getDatastoreType(r)» «helper.getDatastoreVariableName(r)» = new «helper.getDatastoreType(r)»(«helper.getDatastoreInvocationParameters(r)»);
				«ENDFOR»
				
				middleware = new «partition.name.toFirstUpper»Middleware(«getDatastoreVariablesAsParameters(partition, helper)»);
				
				// Need to generate the constraints
			}
		}
		''')
		
	}
	
	def String getMiddlewareConstructorArguments(Partition partition, CoreGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < partition.regions.size; i++) {
			sb.append(helper.getDatastoreType(partition.regions.get(i)))
			sb.append(' ')
			sb.append(helper.getDatastoreVariableName(partition.regions.get(i)))
			if(i < partition.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	def String getDatastoreVariablesAsParameters(Partition partition, CoreGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < partition.regions.size; i++) {
			sb.append(helper.getDatastoreVariableName(partition.regions.get(i)))
			if(i < partition.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	def String getProcessorVariablesAsParameters(Partition partition, CoreGeneratorHelper helper) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < partition.regions.size; i++) {
			sb.append(helper.getProcessorVariableName(partition.regions.get(i)))
			if(i < partition.regions.size - 1) {
				sb.append(", ")
			}
		}
		sb.toString
	}
	
	def String getHandlersInvocationAsParameters(Partition partition) {
		var StringBuilder sb = new StringBuilder()
		for(var i = 0; i < partition.regions.size; i++) {
			sb.append("new ")
			sb.append(DatastoreHandlerHelper.getHandlerType(partition.regions.get(i)))
			sb.append("(")
			sb.append(DatastoreHandlerHelper.getHandlerLocation(partition.regions.get(i)))
			sb.append(")")
			if(i < partition.regions.size - 1) {
				sb.append(",\n")
			}
		}
		sb.toString
	}
	
	
}
