package som.umltonosql.generator.postgres.xtend

import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.resource.Resource
import java.util.List
import java.util.Map
import postgres.Table
import region.Region
import som.umltonosql.generator.postgres.PostgresGeneratorUtil
import java.util.HashMap
import java.util.ArrayList

class PostgresBeanGenerator implements IGenerator {

	Map<String, String> primitiveTypeToJavaTypeMapping
	Map<String, String> javaTypeToImportMapping
	List<String> postgresBeans

	String postgresBasePackage
	String appName
	String corePackage
	Region region

	new() {

		this.postgresBasePackage = PostgresGeneratorUtil.instance.postgresBasePackage
		this.appName = PostgresGeneratorUtil.instance.appName
		this.corePackage = PostgresGeneratorUtil.instance.corePackageName
		this.region = PostgresGeneratorUtil.instance.region

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
		postgresBeans.forEach[
			bean | fsa.generateFile(bean + ".java", '''
			'''
		)]
	}

	def void populatePostgresBeans(Resource postgresResource) {
		postgresResource.allContents.filter[o|o instanceof Table].map[o|o as Table].filter [t |
			region.classes.map[c|c.name.toLowerCase].contains(t.name.toLowerCase)
		].forEach[t | postgresBeans.add(t.name)]
	}

}
