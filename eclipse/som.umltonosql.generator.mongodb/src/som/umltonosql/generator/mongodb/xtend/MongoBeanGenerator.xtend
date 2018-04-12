package som.umltonosql.generator.mongodb.xtend

import documentdb.Collection
import documentdb.CollectionType
import documentdb.Field
import documentdb.UmlToNoSQLIDReference
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import region.Partition
import region.Region
import som.umltonosql.generator.core.CoreGeneratorUtil
import som.umltonosql.generator.mongodb.MongoGeneratorUtil

class MongoBeanGenerator implements IGenerator {
	
	Map<String,String> primitiveTypeToJavaTypeMapping
	Map<String,String> javaTypeToImportMapping	
	List<String> mongoBeans
	
	String mongoBasePackage
	String appName
	String corePackage
	
	Region region

	new() {
		
		this.mongoBasePackage = MongoGeneratorUtil.instance.basePackage
		this.appName = CoreGeneratorUtil.instance.appName
		this.corePackage = CoreGeneratorUtil.instance.corePackageName
		this.region = MongoGeneratorUtil.instance.region
		
		primitiveTypeToJavaTypeMapping = new HashMap<String,String>()
		primitiveTypeToJavaTypeMapping.put("String", "String")
		primitiveTypeToJavaTypeMapping.put("Integer","Integer")
		primitiveTypeToJavaTypeMapping.put("Boolean","Boolean")
		primitiveTypeToJavaTypeMapping.put("Date","Date")
		
		javaTypeToImportMapping = new HashMap<String,String>()
		javaTypeToImportMapping.put("String","java.lang.String")
		javaTypeToImportMapping.put("Integer","java.lang.Integer")
		javaTypeToImportMapping.put("Boolean","java.lang.Boolean")
		javaTypeToImportMapping.put("Date","java.util.Date")
		
		mongoBeans = new ArrayList
	}
	
	/**
	 * Generate Java beans for UML entities (regardless the actual mapping
	 * to low-level representation of these entities)
	 */
	override doGenerate(Resource mongoModel, IFileSystemAccess fsa) {
		populateMongoBeans(mongoModel)
		val List<Collection> collections = mongoModel.allContents.filter[o | o instanceof Collection].map[o | o as Collection].toList
		collections.forEach[
			cc | fsa.generateFile((cc as Collection).name + ".java", '''
			package «appName».«mongoBasePackage»;
			
			import org.bson.Document;
			import org.bson.types.ObjectId;
			import som.umltonosql.core.bean.MongoBean;
			import som.umltonosql.core.datastore.store.MongoDatastore;
			import «appName».«corePackage».«appName.toFirstUpper»Middleware;
			
			«FOR imp : getImports(cc)»
			import «imp»;
			«ENDFOR»
			
			public class «cc.name» extends MongoBean {
				
				public «cc.name.toFirstUpper»(ObjectId id, MongoDatastore mongoDatastore) {
					super(id, mongoDatastore);
				}
				
				public «cc.name.toFirstUpper»(Document document, MongoDatastore mongoDatastore) {
					super(document, mongoDatastore);
				}
				
				«FOR doc : cc.documents»
					«FOR field : doc.fields.filter[f | !(f.key.equals("_id"))]»
					public «computeGetterType(field)» get«field.key.toFirstUpper»() {
						«IF field.type.name.equals("Date")»
							Long timestamp = getValue("«field.key.toFirstLower»");
							return new Date(timestamp);
						«ELSE»
							«IF field.type instanceof CollectionType»
							List<ObjectId> «field.key»Id = getValue("«field.key»");
							if(«field.key»Id.isEmpty()) {
								List<«field.type.name.toFirstUpper»> «field.key» = new ArrayList<>();
								for(ObjectId id : «field.key»Id) {
									«field.key».add(«appName.toFirstUpper»Middleware.getInstance().get«field.key.toFirstUpper»(id.toString()));
								}
								return Collections.unmodifiableList(«field.key»);
							}
							else {
								// should check the cardinality to throw an exception when necessary
								return Collections.emptyMap();
							}
							«ELSE»
								«IF field.type instanceof UmlToNoSQLIDReference»
								ObjectId «field.type.name.toFirstLower»Id = getValue("«field.key»");
								return «appName.toFirstUpper»Middleware.getInstance().get«field.type.name.toFirstUpper»(«field.type.name.toFirstLower»Id.toString());
								«ELSE»
								return getValue("«field.key.toFirstLower»");
								«ENDIF»
							«ENDIF»
						«ENDIF»
					}
					
					«IF !(field.type instanceof CollectionType)»
					public void set«field.key.toFirstUpper»(«field.type.name» new«field.key.toFirstUpper») {
						«IF field.type.name.equals("Date")»
						Long timestamp = new«field.key.toFirstUpper».getTime();
						updateField("«field.key»", timestamp);
						«ELSE»
							«IF field.type instanceof UmlToNoSQLIDReference»
								updateField("«field.key»", new ObjectId(new«field.key.toFirstUpper».getId()));
							«ELSE»
								updateField("«field.key»", new«field.key.toFirstUpper»);
							«ENDIF»
						«ENDIF»
					}
					
					«ELSE»
					public void add«field.key.toFirstUpper»(«field.type.name» new«field.key.toFirstUpper») {
						List<ObjectId> «field.key»Id = getValue("«field.key»");
						«field.key»Id.add(new«field.key.toFirstUpper».getObjectId());
						updateField("«field.key»", «field.key»Id);	
					}
					
					«ENDIF»
					«ENDFOR»
				«ENDFOR»
			}
			'''
		)]
	}
	
	def void populateMongoBeans(Resource mongoResource) {
		mongoResource.allContents.filter[o | o instanceof Collection].map[o | (o as Collection).name].forEach[n | mongoBeans.add(n)]
	}
	
	def String computeGetterType(Field field) {
		if(field.type instanceof CollectionType) {
			return "Iterable<" + field.type.name + ">"
		} else {
			return field.type.name
		}
	}
	
	def java.util.Collection<String> getImports(Collection collection) {
		val Set<String> imports = new HashSet
		collection.documents.forEach[
			d | d.fields.forEach[
				f | 
				if(javaTypeToImportMapping.containsKey(f.type.name)) {
					imports.add(javaTypeToImportMapping.get(f.type.name))
				} else if(mongoBeans.contains(f.type.name)) {
					imports.add(mongoBasePackage + '.' + f.type.name)
				}
				if(f.type instanceof CollectionType) {
					// there is at least one collection, we need to import Collection-related classes
					imports.add("java.util.List")
					imports.add("java.util.Collections")
					imports.add("java.util.ArrayList")
					imports.add("org.bson.types.ObjectId")
				}
				if(f.type instanceof UmlToNoSQLIDReference) {
					val beanName = (f.type as UmlToNoSQLIDReference).name
					val beanRegion = findRegion(beanName)
					imports.add(appName + '.' + beanRegion.name + '.' + beanName)
				}
			]
		];
		return imports
	}
	
	def String getImport(String type) {
		return javaTypeToImportMapping.get(type)
	}
	
	def Region findRegion(String beanName) {
		val Partition partition = region.eContainer as Partition
		partition.regions.findFirst[r | r.classes.map[c | c.name.toFirstUpper].contains(beanName)]
	}
	
}
