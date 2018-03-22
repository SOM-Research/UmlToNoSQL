package som.umltonosql.generator.core;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLCoreGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new CoreGeneratorModule());
	
	public UmlToNoSQLCoreGenerator(Resource resource, File rootFolder) {
		super(resource, rootFolder);
	}
	
	@Override
	public void launch() {
		System.out.println("Running Core Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(resource, rootFolder.getAbsolutePath());
	}

}
