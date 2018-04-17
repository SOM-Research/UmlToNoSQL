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

	/**
	 * Constructs a new {@link UmlToNoSQLCoreGenerator} with the provided
	 * {@code partitionResource} and {@code rootFolder}.
	 * <p>
	 * <b>Note:</b> this generator takes the {@link Resource} containing the
	 * conceptual model partition as its parameter, and not a generated PSM such as
	 * the other {@link UmlToNoSQLGenerator} implementations.
	 * 
	 * @param partitionResource the {@link Resource} containing the {@link Partition} defined over the input conceptual model
	 * @param rootFolder the <i>root</i> folder used to generate core related artifacts
	 */
	public UmlToNoSQLCoreGenerator(Resource partitionResource, File rootFolder) {
		super(partitionResource, rootFolder);
		Partition partition = (Partition) partitionResource.getContents().get(0);
		CoreGeneratorUtil.getInstance().setAppName(partition.getName());
		CoreGeneratorUtil.getInstance().setPartition(partition);
		CoreGeneratorUtil.getInstance().setBasePackage("core");
	}

	@Override
	public void launch() {
		System.out.println("Running Core Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(psmResource, rootFolder.getAbsolutePath());
	}

}
