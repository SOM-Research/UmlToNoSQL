package som.umltonosql.generator.postgres;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLPostgresGenerator extends UmlToNoSQLGenerator {

private static Injector injector = Guice.createInjector(new PostgresGeneratorModule());
	
	public UmlToNoSQLPostgresGenerator(Resource res, File rootFolder, Region region) {
		super(res, rootFolder, region);
		PostgresGeneratorUtil.getInstance().setAppName("demo");
		PostgresGeneratorUtil.getInstance().setCorePackageName("core");
		PostgresGeneratorUtil.getInstance().setPostgresBasePackage(region.getName());
		PostgresGeneratorUtil.getInstance().setRegion(region);
	}
	
	@Override
	public void launch() {
		System.out.println("Running Postgres Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(resource, rootFolder.getAbsolutePath());
	}
	
}
