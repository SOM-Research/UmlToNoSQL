package som.umltonosql.generator.gremlin.xtend

import graphdb.PrimitiveType
import graphdb.Property
import graphdb.Vertex
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.uml2.uml.Model
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import region.Partition
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
			«val String className = vv.labels.get(0)»
			package «appName».«gremlinBasePackage»;
			
			import som.umltonosql.core.bean.GremlinBean;
			import som.umltonosql.core.datastore.store.GremlinDatastore;
			import som.umltonosql.core.exceptions.ConsistencyException;
			
			«FOR imp : getImports(gremlinModel, vv)»
			import «imp»;
			«ENDFOR»
			
			public class «className.toFirstUpper» extends GremlinBean {
				
				public «className.toFirstUpper»(String id, GremlinDatastore datastore) {
					super(id, datastore);
				}
				
				«FOR prop : vv.properties.filter[pp | !(pp.type.equals(PrimitiveType.UML_TO_NO_SQLID))]»
				public «getJavaType(prop.type)» get«prop.key.toFirstUpper»() {
					return this.getAttribute("«prop.key.toFirstLower»");
				}
				
				public void set«prop.key.toFirstUpper»(«getJavaType(prop.type)» new«prop.key.toFirstUpper») {
					this.setAttribute("«prop.key.toFirstLower»", new«prop.key.toFirstUpper»);
				}
				«ENDFOR»
				
				«FOR outEdge : vv.outEdges»
				«val Vertex otherEnd = outEdge.head»
				«val String otherEndBeanType = getType(otherEnd).toFirstUpper»
				public Iterable<«otherEndBeanType»> get«outEdge.type.toFirstUpper»() {
					Iterable<String> «outEdge.type.toFirstLower»Ids = this.getAssociation("«outEdge.type.toFirstLower»", «otherEndBeanType».class);
					if(«outEdge.type.toFirstLower»Ids.iterator().hasNext()) {
						List<«otherEndBeanType»> «outEdge.type.toFirstLower» = new ArrayList<>();
						for(String «outEdge.type.toFirstLower»Id : «outEdge.type.toFirstLower»Ids) {
							«outEdge.type.toFirstLower».add(«appName.toFirstUpper»Middleware.getInstance().get«otherEndBeanType.toFirstUpper»(«outEdge.type.toFirstLower»Id));
						}
						return Collections.unmodifiableList(«outEdge.type.toFirstLower»);
					}
					return Collections.emptyList();
				}
				
				public void add«outEdge.type.toFirstUpper»(«otherEndBeanType» new«otherEndBeanType») {
					this.addAssociation("«outEdge.type.toFirstLower»", new«otherEndBeanType»);
				}
				«ENDFOR»
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
	
	def Collection<String> getImports(Resource gremlinModel, Vertex vertex) {
		val Set<String> imports = new HashSet
		if(!vertex.outEdges.isEmpty) {
			imports.add("java.util.ArrayList")
			imports.add("java.util.Collections")
			imports.add("java.util.List")
			for(e : vertex.outEdges) {
				val beanName = getType(e.head)
				val beanRegion = findRegion(beanName)
				imports.add(appName + '.' + beanRegion.name + '.' + beanName)
			}
			imports.add(appName + '.' + corePackage + '.' + appName.toFirstUpper + "Middleware");
		}
		for(crossDatastoreProperty : vertex.properties.filter[pp | pp.type.equals(PrimitiveType.UML_TO_NO_SQLID)]) {
			val beanName = getCrossDatastoreBeanName(crossDatastoreProperty)
			val beanRegion = findRegion(beanName)
			imports.add(appName + '.' + beanRegion.name + '.' + beanName)
			imports.add(appName + '.' + corePackage + '.' + appName.toFirstUpper + "Middleware");
		}
		return imports;
	}
	
	def String getJavaType(PrimitiveType type) {
		return primitiveTypeToJavaTypeMapping.get(type.getName);
	}
	
	// Return the name of the bean associated to the vertex
	def String getType(Vertex vv) {
		return umlHelper.getClassWithName(vv.labels.get(0)).name
	}
	
	def String getCrossDatastoreBeanName(Property property) {
		val Vertex container = property.owner as Vertex
		return property.key.substring(container.labels.get(0).length)
	}
	
	def Region findRegion(String beanName) {
		val Partition partition = region.eContainer as Partition
		partition.regions.findFirst[r | r.classes.map[c | c.name.toFirstUpper].contains(beanName)]
	}
	

}
