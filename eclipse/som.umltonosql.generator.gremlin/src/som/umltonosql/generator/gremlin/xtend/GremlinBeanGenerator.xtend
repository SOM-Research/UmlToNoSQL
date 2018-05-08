package som.umltonosql.generator.gremlin.xtend

import graphdb.Vertex
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.uml2.uml.Model
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import region.Region
import som.umltonosql.generator.gremlin.GremlinGeneratorUtil
import som.umltonosql.generator.util.CoreGeneratorUtil
import som.umltonosql.generator.util.UmlHelper

class GremlinBeanGenerator implements IGenerator {

	Map<String, String> primitiveTypeToJavaTypeMapping
	Map<String, String> javaTypeToImportMapping
	List<String> gremlinBeans

	String gremlinBasePackage
	String appName
	String corePackage

	Region region
	Model pimModel
	
	UmlHelper umlHelper

	new() {

		this.gremlinBasePackage = GremlinGeneratorUtil.instance.basePackage
		this.appName = CoreGeneratorUtil.instance.appName
		this.corePackage = CoreGeneratorUtil.instance.basePackage
		this.region = GremlinGeneratorUtil.instance.getRegion
		this.pimModel = GremlinGeneratorUtil.instance.pimModel
		this.umlHelper = new UmlHelper(pimModel)

		primitiveTypeToJavaTypeMapping = new HashMap<String, String>()
		primitiveTypeToJavaTypeMapping.put("String", "String")
		primitiveTypeToJavaTypeMapping.put("Integer", "Integer")
		primitiveTypeToJavaTypeMapping.put("Boolean", "Boolean")
		primitiveTypeToJavaTypeMapping.put("Date", "Date")

		javaTypeToImportMapping = new HashMap<String, String>()
		javaTypeToImportMapping.put("String", "java.lang.String")
		javaTypeToImportMapping.put("Integer", "java.lang.Integer")
		javaTypeToImportMapping.put("Boolean", "java.lang.Boolean")
		javaTypeToImportMapping.put("Date", "java.util.Date")

		gremlinBeans = new ArrayList
	}

	/**
	 * Generate Java beans for UML entities (regardless the actual mapping
	 * to low-level representation of these entities)
	 */
	override doGenerate(Resource gremlinModel, IFileSystemAccess fsa) {
		val List<Vertex> vertices = gremlinModel.allContents.filter[o | o instanceof Vertex].map[o | o as Vertex].toList
		vertices.forEach[vv | fsa.generateFile(vv.labels.get(0).toFirstUpper + ".java",
			'''
			package «appName».«gremlinBasePackage»;
			
			import som.umltonosql.core.bean.GremlinBean;
			import som.umltonosql.core.datastore.store.GremlinDatastore;
			
			public class «vv.labels.get(0).toFirstUpper» extends GremlinBean {
				
				public «vv.labels.get(0).toFirstUpper»(String id, GremlinDatastore datastore) {
					super(id, datastore);
				}
			}
			'''
		)]
		// populateMongoBeans(mongoModel)
		//val List<Collection> collections = mongoModel.allContents.filter[o|o instanceof Collection].map [o |
		//	o as Collection
		//].toList
		//collections.forEach [
		//	cc |
		//	fsa.generateFile(
		//		(cc as Collection).name + ".java",
		//		''' '''
	}

}
