package som.umltonosql.generator.postgres.xtend

import java.util.ArrayList
import java.util.Collection
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
import relationaldb.ForeignKey
import relationaldb.Table
import relationaldb.Type
import som.umltonosql.generator.postgres.PostgresGeneratorUtil
import som.umltonosql.generator.util.CoreGeneratorUtil
import som.umltonosql.generator.util.UmlHelper

import static java.util.Objects.nonNull
import relationaldb.Column

class PostgresBeanGenerator implements IGenerator {

	Map<String, String> primitiveTypeToJavaTypeMapping
	Map<String, String> javaTypeToImportMapping
	List<Table> postgresBeans

	String postgresBasePackage
	String appName
	String corePackage
	Region region
	
	UmlHelper umlHelper

	new() {

		this.postgresBasePackage = PostgresGeneratorUtil.instance.basePackage
		this.appName = CoreGeneratorUtil.instance.appName
		this.corePackage = CoreGeneratorUtil.instance.basePackage
		this.region = PostgresGeneratorUtil.instance.region
		this.umlHelper = new UmlHelper(PostgresGeneratorUtil.instance.pimModel)

		primitiveTypeToJavaTypeMapping = new HashMap<String, String>()
		primitiveTypeToJavaTypeMapping.put("Varchar", "String")
		primitiveTypeToJavaTypeMapping.put("Integer", "Integer")
		// primitiveTypeToJavaTypeMapping.put("Boolean","Boolean")
		// primitiveTypeToJavaTypeMapping.put("Date","Date")
		javaTypeToImportMapping = new HashMap<String, String>()
		javaTypeToImportMapping.put("String", "java.lang.String")
		javaTypeToImportMapping.put("Integer", "java.lang.Integer")
		javaTypeToImportMapping.put("Boolean", "java.lang.Boolean")
		javaTypeToImportMapping.put("Date", "java.util.Date")

		postgresBeans = new ArrayList
	}

	/**
	 * Generate Java beans for UML entities (regardless the actual mapping
	 * to low-level representation of these entities)
	 */
	override doGenerate(Resource postgresModel, IFileSystemAccess fsa) {
		populatePostgresBeans(postgresModel)
		postgresBeans.forEach [ table |
			fsa.generateFile(
				table.name + ".java",
				'''
					package �appName�.�postgresBasePackage�;
					
					import som.umltonosql.core.bean.PostgresBean;
					import som.umltonosql.core.datastore.store.PostgresDatastore;
					import som.umltonosql.core.exceptions.ConsistencyException;
					import �appName�.�corePackage�.�appName.toFirstUpper�Middleware;
					
					�FOR imp : getImports(postgresModel, table)�
					import �imp�;
					�ENDFOR�
					
					public class �table.name� extends PostgresBean {
					�val umlClass = umlHelper.getClassWithName(table.name)�
						
						public �table.name�(String id, PostgresDatastore datastore) {
							super(id, datastore);
						}
						
						�FOR col : table.col.filter[c | !c.name.equals("id")]�
							�IF !(col instanceof ForeignKey)�
							public �computeGetterType(col.type)� get�col.name.toFirstUpper�() {
								return getSimpleValue("�col.name.toFirstLower�");
							}
							
							public void set�col.name.toFirstUpper�(�computeGetterType(col.type)� new�col.name.toFirstUpper�) {
								�/* We should probably generate cardinality checking code here */�
								updateSimpleValue("�col.name.toFirstLower�", new�col.name.toFirstUpper�);
							}
							�ELSE�
							�val outerTypeBean = getOuterTypeBeanSimpleValue(col as ForeignKey)�
							�val outerTypeName = getOuterTypeName(col as ForeignKey)�
							public �outerTypeBean� get�outerTypeName.toFirstUpper�() {
								String id = getSimpleValue("�col.name.toFirstLower�");
								return �appName.toFirstUpper�Middleware.getInstance().get�outerTypeBean�(id);
							}
							
							public void set�outerTypeName.toFirstUpper�(�outerTypeBean� new�outerTypeName.toFirstUpper�) {
								updateSimpleValue("�col.name.toFirstLower�", new�outerTypeName.toFirstUpper�.getId());
							}
							�ENDIF�
							
						�ENDFOR�
						�FOR multiTable : getTablesForMultiValuedFeatures(postgresModel, table)�
							�val outerTypeBean = getOuterTypeBean(multiTable, table)�
							�val outerTypeName = getOuterTypeName(multiTable)�
							public Iterable<�outerTypeBean�> get�outerTypeName.toFirstUpper�() {
								List<String> �outerTypeName�Ids = getMultiValue("�outerTypeName�");
								if(�outerTypeName�Ids != null && !�outerTypeName�Ids.isEmpty()) {
									List<�outerTypeBean�> �outerTypeName� = new ArrayList<>();
									for(String id : �outerTypeName�Ids) {
										�outerTypeName�.add(�appName.toFirstUpper�Middleware.getInstance().get�outerTypeBean�(id));
									}
									return Collections.unmodifiableList(�outerTypeName�);
								}
								return Collections.emptyList();
							}
							
							public void add�outerTypeBean�(�outerTypeBean� new�outerTypeBean�) {
								�val upperBound = umlHelper.getAssociationEnd(umlClass, outerTypeName).getUpper()�
								�IF upperBound > 1�
								if(getMultiValue("�outerTypeName�").size() >= �upperBound�) {
									throw new ConsistencyException("Cannot add a new �outerTypeBean�, the association cardinality is �upperBound� and the list already contains at least �upperBound� elements");
								}
								�ENDIF�
								addMultiValue("�outerTypeName�", new�outerTypeBean�.getId());
							}
							
						�ENDFOR�
					}
				'''
			)
		]
	}

	def void populatePostgresBeans(Resource postgresResource) {
		postgresResource.allContents.filter[o|o instanceof Table].map[o|o as Table].filter [ t |
			region.classes.map[c|c.name.toLowerCase].contains(t.name.toLowerCase)
		].forEach[t|postgresBeans.add(t)]
	}
	
	def Collection<String> getImports(Resource postgresModel, Table table) {
		val Set<String> imports = new HashSet
		val multiTables = getTablesForMultiValuedFeatures(postgresModel, table)
		if(!multiTables.isEmpty) {
			imports.add("java.util.ArrayList")
			imports.add("java.util.Collections")
			imports.add("java.util.List")
			for(Table multi : multiTables) {
				val beanName = getOuterTypeBean(multi, table).toFirstUpper
				val beanRegion = findRegion(beanName)
				imports.add(appName + '.' + beanRegion.name + '.' + beanName)
			}
		}
		val crossDatastoreForeignKeys = getCrossDatastoreForeignKeys(postgresModel)
		for(ForeignKey fk : crossDatastoreForeignKeys) {
			val beanName = getOuterTypeBeanSimpleValue(fk);
			val beanRegion = findRegion(beanName);
			imports.add(appName + '.' + beanRegion.name + '.' + beanName)
		}
		return imports
	}
	
	def Region findRegion(String beanName) {
		val Partition partition = region.eContainer as Partition
		partition.regions.findFirst[r | r.classes.map[c | c.name.toFirstUpper].contains(beanName)]
	}

	def String computeGetterType(Type type) {
		return primitiveTypeToJavaTypeMapping.get(type.eClass.name)
	}
	
	def Collection<ForeignKey> getCrossDatastoreForeignKeys(Resource postgresModel) {
		postgresModel.allContents.filter[o | o instanceof ForeignKey].map[o | o as ForeignKey].filter[fk | fk.referencedColumn == null].toList
	}

	def Collection<Table> getTablesForMultiValuedFeatures(Resource postgresModel, Table table) {
		postgresModel.allContents.filter[o|o instanceof Table].map[o|o as Table].filter [ t |
			!getForeignKeys(t).isEmpty() && 
			isForeignKeyOf(getForeignKeys(t).get(0), table)
			// get(0) retrieves the first Table involved in the association, i.e. the starting point of the association
		].toList
	}
	
	def Iterable<ForeignKey> getForeignKeys(Table table) {
		table.col.filter[c|c instanceof ForeignKey].map[c|c as ForeignKey]
	}

	def boolean isForeignKeyOf(ForeignKey fk, Table table) {
		nonNull(fk.referencedColumn) && (fk.referencedColumn.eContainer as Table).equals(table)
	}
	
	def String getOuterTypeBeanSimpleValue(ForeignKey fk) {
		// == null -> cross datastore association
		if(fk.referencedColumn == null) {
			fk.name.substring(0, fk.name.indexOf('_')).toFirstUpper
		} else {
			fk.referencedColumn.name.toFirstUpper
		}
	}
	
	def String getOuterTypeName(ForeignKey fk) {
		fk.name.substring(0, fk.name.indexOf('_')).toFirstLower
	}
	
	def String getOuterTypeBean(Table multiTable, Table table) {
		val fk = getForeignKeys(multiTable).findFirst[k | !isForeignKeyOf(k, table) || k.referencedColumn == null];
		// == null -> cross datastore association
		if(fk.referencedColumn == null) {
			fk.name.substring(0, fk.name.indexOf('_')).toFirstUpper
		} else {
			fk.referencedColumn.name.toFirstUpper
		}
	}
	
	def getOuterTypeName(Table multiTable) {
		multiTable.name.substring(multiTable.name.indexOf('_') + 1).toFirstLower;
	}

}
