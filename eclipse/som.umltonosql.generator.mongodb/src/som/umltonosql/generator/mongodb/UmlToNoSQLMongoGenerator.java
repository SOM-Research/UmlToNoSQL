package som.umltonosql.generator.mongodb;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLMongoGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new MongoGeneratorModule());
	
	public UmlToNoSQLMongoGenerator(Resource documentPsmResource, File rootFolder, Region region) {
		super(documentPsmResource, rootFolder, region);
		MongoGeneratorUtil.getInstance().setBasePackage(region.getName());
		MongoGeneratorUtil.getInstance().setRegion(region);
	}
	
	@Override
	public void launch() {
		System.out.println("Running MongoDB Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(psmResource, rootFolder.getAbsolutePath());
	}
	
}
