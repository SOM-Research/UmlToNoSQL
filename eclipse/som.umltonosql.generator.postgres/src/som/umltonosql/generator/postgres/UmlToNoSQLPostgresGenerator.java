package som.umltonosql.generator.postgres;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLPostgresGenerator extends UmlToNoSQLGenerator {

private static Injector injector = Guice.createInjector(new PostgresGeneratorModule());
	
	public UmlToNoSQLPostgresGenerator(Resource relationalPsmResource, File rootFolder, Region region) {
		super(relationalPsmResource, rootFolder, region);
		PostgresGeneratorUtil.getInstance().setBasePackage(region.getName());
		PostgresGeneratorUtil.getInstance().setRegion(region);
	}
	
	@Override
	public void launch() {
		System.out.println("Running Postgres Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(psmResource, rootFolder.getAbsolutePath());
	}
	
}
