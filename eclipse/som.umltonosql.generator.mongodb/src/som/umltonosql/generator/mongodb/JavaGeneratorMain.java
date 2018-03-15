package som.umltonosql.generator.mongodb;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.inject.Guice;
import com.google.inject.Injector;

import som.umltonosql.generator.core.GeneratorUtil;

public class JavaGeneratorMain {

	private static Injector injector = Guice.createInjector(new EcoreGeneratorModule());
	
	public static void main(String[] args) {
		String regionName = "business";
		String input = "model/mongo.xmi";
		File file = new File("C:\\Users\\gwend\\Documents\\Dev\\UmlToNoSQL\\eclipse\\som.umltonosql.demo.generated");
		if(file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				System.out.println("Cannot delete the existing project");
				e.printStackTrace();
			}
		}
		file.mkdir();
		
		// should be moved in a generic project
		generateMavenStructure(file, regionName);
		
		GeneratorUtil.getInstance().setAppName("demo");
		GeneratorUtil.getInstance().setCorePackageName("core");
		MongoGeneratorUtil.getInstance().setMongoBasePackage(regionName);
		
		String output = file.getAbsolutePath();
		
//		IProgressMonitor progressMonitor = new NullProgressMonitor();
//		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//		IProject project = root.getProject("fr.inria.atlanmod.uml2nosql.graphdb.generated");
//		try {
//			project.create(progressMonitor);
//			project.open(progressMonitor);
//		} catch (CoreException e) {
//			System.out.println("Unable to create the project " + "fr.inria.atlanmod.uml2nosql.graphdb.generated");
//			e.printStackTrace();
//		}
		
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(input, "C:\\Users\\gwend\\Documents\\Dev\\UmlToNoSQL\\eclipse\\som.umltonosql.demo.generated\\src\\main\\java\\" + regionName);
	}
	
	private static void generateMavenStructure(File file, String baseRegionName) {
		File javaFile = new File(file, "src\\main\\java");
		javaFile.mkdirs();
		new File(javaFile, baseRegionName).mkdir();
		new File(file, "src\\main\\resources").mkdirs();
	}

}
