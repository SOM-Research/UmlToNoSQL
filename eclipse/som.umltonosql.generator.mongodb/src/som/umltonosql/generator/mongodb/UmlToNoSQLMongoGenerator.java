package som.umltonosql.generator.mongodb;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLMongoGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new MongoGeneratorModule());
	
	public UmlToNoSQLMongoGenerator(Resource documentPsmResource, File rootFolder, Region region, Model pimModel) {
		super(documentPsmResource, rootFolder, region, pimModel);
		MongoGeneratorUtil.getInstance().setBasePackage(region.getName());
		MongoGeneratorUtil.getInstance().setRegion(region);
		MongoGeneratorUtil.getInstance().setPimModel(pimModel);
		
	}
	
	@Override
	public void launch() {
		System.out.println("Running MongoDB Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(psmResource, rootFolder.getAbsolutePath());
	}
	
}
