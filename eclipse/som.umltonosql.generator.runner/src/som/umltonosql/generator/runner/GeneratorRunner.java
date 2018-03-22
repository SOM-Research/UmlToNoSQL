package som.umltonosql.generator.runner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import region.Region;
import region.RegionPackage;
import region.RegionSet;
import som.umltonosql.generator.runner.util.UmlToNoSQLGeneratorUtil;
import som.umltonosql.generator.structure.UmlToNoSQLGenerator;

public class GeneratorRunner {
	
	public static void main(String[] args) {
		
		// Create the output directory
		File rootFolder = new File("C:\\Users\\gwend\\Documents\\Dev\\UmlToNoSQL\\eclipse\\som.umltonosql.demo.generated");
		if(rootFolder.exists()) {
			try {
				FileUtils.forceDelete(rootFolder);
			} catch (IOException e) {
				System.out.println("Cannot delete the existing project");
				e.printStackTrace();
			}
		}
		rootFolder.mkdir();
		
		// Initialize the Region package
		RegionPackage.eINSTANCE.eClass();
		
		// Demo models
		URI regionResourceURI = URI.createURI("../som.umltonosql.demo/models/pim/demo.region");
		URI modelResourceURI = URI.createURI("../som.umltonosql.demo/models/pim/model.uml");
		
		// Load the region model and find the generators to use
		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("region", new XMIResourceFactoryImpl());
		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
		Resource modelResource = rSet.getResource(modelResourceURI, true);
		modelResource.setURI(URI.createURI("model.uml"));
		Resource regionResource = rSet.getResource(regionResourceURI, true);
		RegionSet regionSet = (RegionSet) regionResource.getContents().get(0);
		
		// Initialize the Generator Helper
		UmlToNoSQLGeneratorUtil genHelper = UmlToNoSQLGeneratorUtil.init(rootFolder, regionSet);
		
		List<UmlToNoSQLGenerator> generators = UmlToNoSQLGeneratorUtil.getInstance().getGenerators();

		generateMavenStructure(rootFolder, regionSet);
		
		StreamSupport.stream(generators.spliterator(), false).forEach(g -> g.launch());
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
