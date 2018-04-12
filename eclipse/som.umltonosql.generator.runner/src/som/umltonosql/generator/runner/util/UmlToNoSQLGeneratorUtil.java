package som.umltonosql.generator.runner.util;

import static java.util.Objects.nonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import mongodb.MongodbPackage;
import postgres.PostgresPackage;
import region.Partition;
import region.Region;
import region.StorageKind;
import som.umltonosql.generator.core.UmlToNoSQLCoreGenerator;
import som.umltonosql.generator.mongodb.UmlToNoSQLMongoGenerator;
import som.umltonosql.generator.postgres.UmlToNoSQLPostgresGenerator;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLGeneratorUtil {

	private File rootFile;
	
	private File basePackage;
	
	private Partition partition;
	
	private ResourceSet resourceSet;

	private static UmlToNoSQLGeneratorUtil INSTANCE;
		
	public static UmlToNoSQLGeneratorUtil getInstance() {
		return INSTANCE;
	}
	
	public static UmlToNoSQLGeneratorUtil init(File rootFile, Partition partition) {
		if(nonNull(INSTANCE)) {
			throw new RuntimeException("An instance of UmlToNoSQLGenerator already exist");
		}
		INSTANCE = new UmlToNoSQLGeneratorUtil(rootFile, partition);
		return INSTANCE;
	}
	
	private UmlToNoSQLGeneratorUtil(File rootFile, Partition partition) {
		this.rootFile = rootFile;
		this.partition = partition;
		this.resourceSet = new ResourceSetImpl();
		EPackage.Registry.INSTANCE.put(MongodbPackage.eINSTANCE.getNsURI(), MongodbPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(PostgresPackage.eINSTANCE.getNsURI(), PostgresPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		basePackage = new File(rootFile, "\\src\\main\\java\\demo");
	}
	
	public List<UmlToNoSQLGenerator> getGenerators() {
		List<UmlToNoSQLGenerator> generators = new ArrayList<>();
		generators.add(new UmlToNoSQLCoreGenerator(partition.eResource(), rootFile));
		for(Region r : partition.getRegions()) {
			StorageKind sKind = r.getStorage();
			if(sKind.equals(StorageKind.DOCUMENT)) {
				Resource mongoResource = resourceSet.getResource(URI.createURI("model/" + r.getName().toLowerCase() + ".xmi"), true);
				generators.add(new UmlToNoSQLMongoGenerator(mongoResource, new File(basePackage, r.getName().toLowerCase()), r));
			} else if(sKind.equals(StorageKind.RELATIONAL)) {
				Resource postgresResource = resourceSet.getResource(URI.createURI("model/" + r.getName().toLowerCase() + ".xmi"), true);
				generators.add(new UmlToNoSQLPostgresGenerator(postgresResource, new File(basePackage, r.getName().toLowerCase()), r));
			}
		}
		return generators;
	}

}
