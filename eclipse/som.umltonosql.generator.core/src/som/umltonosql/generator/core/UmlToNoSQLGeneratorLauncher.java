package som.umltonosql.generator.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

import region.Region;
import region.RegionSet;

public class UmlToNoSQLGeneratorLauncher {

	private static Injector injector = Guice.createInjector(new CoreGeneratorModule());
	
	public static void launch(Resource regionResource) {
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
		
		String output = file.getAbsolutePath();
		
		RegionSet regionSet = (RegionSet) regionResource.getContents().get(0);
		
		generateMavenStructure(file, regionSet);
		
		Generator generator = injector.getInstance(Generator.class);
		generator.runGenerator(regionResource, output);
	}
	
	private static void generateMavenStructure(File file, RegionSet regionSet) {
		File basePackage = new File(file, "src\\main\\java\\" + regionSet.getName() + "\\");
		basePackage.mkdirs();
		new File(basePackage, "core").mkdir();
		new File(file, "src\\main\\resources").mkdirs();
		for(Region region : regionSet.getRegions()) {
			new File(basePackage, region.getName()).mkdir();
		}
	}

}
