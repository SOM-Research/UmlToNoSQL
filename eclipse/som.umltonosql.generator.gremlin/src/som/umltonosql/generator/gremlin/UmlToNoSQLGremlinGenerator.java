package som.umltonosql.generator.gremlin;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLGremlinGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new GremlinGeneratorModule());
	
	public UmlToNoSQLGremlinGenerator(Resource documentPsmResource, File rootFolder, Region region, Model pimModel) {
		super(documentPsmResource, rootFolder, region, pimModel);
		GremlinGeneratorUtil.getInstance().setBasePackage(region.getName());
		GremlinGeneratorUtil.getInstance().setRegion(region);
		GremlinGeneratorUtil.getInstance().setPimModel(pimModel);
		
	}
	
	@Override
	public void launch() {
		System.out.println("Running Gremlin Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(psmResource, rootFolder.getAbsolutePath());
	}
	
}
