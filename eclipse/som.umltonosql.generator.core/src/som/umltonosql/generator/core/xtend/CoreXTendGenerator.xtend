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
		    <parent>
		        <artifactId>umltonosql</artifactId>
		        <groupId>som.umltonosql</groupId>
		        <version>1.0-SNAPSHOT</version>
		    </parent>
		    <modelVersion>4.0.0</modelVersion>
		
		    <artifactId>«rSet.name.toFirstLower»</artifactId>
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
		fsa.generateFile("src\\main\\java\\core\\" + rSet.name.toFirstUpper + "Middleware.java", '''
		import som.umltonosql.core.Middleware;
		import fr.inria.atlanmod.commons.log.Log;
		«FOR r : rSet.regions»
		import «helper.getDatastoreImport(r)»;
		import «helper.getProcessorImport(r)»;
		«ENDFOR»
		import static java.util.Objects.nonNull;
		
		public class «rSet.name.toFirstUpper»Middleware extends Middleware {
			
			«FOR r : rSet.regions»
			«helper.getDatastoreType(r)» «helper.getDatastoreVariableName(r)»;
			
			«helper.getProcessorType(r)» «helper.getProcessorVariableName(r)»
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
					Log.warn("Multiple instances of «rSet.name.toFirstUpper»"Middleware have been created");
				}
				INSTANCE = this;
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
	
	
}
