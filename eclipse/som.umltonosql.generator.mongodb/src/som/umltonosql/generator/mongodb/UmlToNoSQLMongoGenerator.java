package som.umltonosql.generator.mongodb;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import som.umltonosql.generator.core.GeneratorUtil;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class UmlToNoSQLMongoGenerator extends UmlToNoSQLGenerator {

	private static Injector injector = Guice.createInjector(new MongoGeneratorModule());
	
	public UmlToNoSQLMongoGenerator(Resource res, File rootFolder, Region region) {
		super(res, rootFolder, region);
		GeneratorUtil.getInstance().setAppName("demo");
		GeneratorUtil.getInstance().setCorePackageName("core");
		MongoGeneratorUtil.getInstance().setMongoBasePackage(region.getName());
	}
	
	@Override
	public void launch() {
		System.out.println("Running MongoDB Generator");
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(resource, rootFolder.getAbsolutePath());
	}
	
//	public static void main(String[] args) {
//		String regionName = "business";
//		String input = "model/mongo.xmi";
//		File file = new File("C:\\Users\\gwend\\Documents\\Dev\\UmlToNoSQL\\eclipse\\som.umltonosql.demo.generated");
//		if(file.exists()) {
//			try {
//				FileUtils.forceDelete(file);
//			} catch (IOException e) {
//				System.out.println("Cannot delete the existing project");
//				e.printStackTrace();
//			}
//		}
//		file.mkdir();
//		
//		// should be moved in a generic project
//		generateMavenStructure(file, regionName);
//		
//		
//		
//		String output = file.getAbsolutePath();
//		
////		IProgressMonitor progressMonitor = new NullProgressMonitor();
////		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
////		IProject project = root.getProject("fr.inria.atlanmod.uml2nosql.graphdb.generated");
////		try {
////			project.create(progressMonitor);
////			project.open(progressMonitor);
////		} catch (CoreException e) {
////			System.out.println("Unable to create the project " + "fr.inria.atlanmod.uml2nosql.graphdb.generated");
////			e.printStackTrace();
////		}
//		
//		
//	}
}
