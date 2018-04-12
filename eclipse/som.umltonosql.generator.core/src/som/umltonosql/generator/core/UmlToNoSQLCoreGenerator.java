package som.umltonosql.generator.core;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Partition;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;
import som.umltonosql.generator.util.CoreGeneratorUtil;

public class UmlToNoSQLCoreGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new CoreGeneratorModule());
	
	public UmlToNoSQLCoreGenerator(Resource resource, File rootFolder) {
		super(resource, rootFolder);
		Partition partition = (Partition)resource.getContents().get(0);
		CoreGeneratorUtil.getInstance().setAppName(partition.getName());
		CoreGeneratorUtil.getInstance().setPartition(partition);
		CoreGeneratorUtil.getInstance().setBasePackage("core");
	}
	
	@Override
	public void launch() {
		System.out.println("Running Core Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(resource, rootFolder.getAbsolutePath());
	}

}
