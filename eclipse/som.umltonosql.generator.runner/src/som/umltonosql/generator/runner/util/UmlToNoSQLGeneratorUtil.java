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

import documentdb.Database;
import documentdb.DatabaseKind;
import documentdb.DocumentdbPackage;
import region.Partition;
import region.Region;
import region.StorageKind;
import relationaldb.RelationaldbPackage;
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
		EPackage.Registry.INSTANCE.put(DocumentdbPackage.eINSTANCE.getNsURI(), DocumentdbPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(RelationaldbPackage.eINSTANCE.getNsURI(), RelationaldbPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		basePackage = new File(rootFile, "\\src\\main\\java\\demo");
	}
	
	public List<UmlToNoSQLGenerator> getGenerators() {
		List<UmlToNoSQLGenerator> generators = new ArrayList<>();
		generators.add(new UmlToNoSQLCoreGenerator(partition.eResource(), rootFile));
		for(Region r : partition.getRegions()) {
			StorageKind sKind = r.getStorage();
			if(sKind.equals(StorageKind.DOCUMENT)) {
				Resource documentResource = resourceSet.getResource(URI.createURI("model/" + r.getName().toLowerCase() + ".xmi"), true);
				Database db = (Database)documentResource.getContents().get(0);
				if(db.getRawDatabase().equals(DatabaseKind.MONGODB)) {
					generators.add(new UmlToNoSQLMongoGenerator(documentResource, new File(basePackage, r.getName().toLowerCase()), r));
				} else {
					throw new RuntimeException("Unknown Document Database Kind: " + db.getRawDatabase());
				}
			} else if(sKind.equals(StorageKind.RELATIONAL)) {
				Resource relationalResource = resourceSet.getResource(URI.createURI("model/" + r.getName().toLowerCase() + ".xmi"), true);
				relationaldb.Database db = (relationaldb.Database)relationalResource.getContents().get(0);
				if(db.getRawDatabase().equals(relationaldb.DatabaseKind.POSTGRES)) {
					generators.add(new UmlToNoSQLPostgresGenerator(relationalResource, new File(basePackage, r.getName().toLowerCase()), r));
				} else {
					throw new RuntimeException("Unknown Relational Database Kind: " + db.getRawDatabase());
				}
			}
		}
		return generators;
	}

}
