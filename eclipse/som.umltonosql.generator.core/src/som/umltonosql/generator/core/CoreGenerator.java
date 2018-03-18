package som.umltonosql.generator.core;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import region.RegionPackage;
import region.RegionSet;

public class CoreGenerator {

	public static void main(String[] args) {
		
		RegionPackage.eINSTANCE.eClass();
		
		// Should be a platform URI
		URI regionResourceURI = URI.createURI("../som.umltonosql.demo/models/pim/demo.region");
		
		// Load the region model and find the generators to use
		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("region", new XMIResourceFactoryImpl());
		Resource regionResource = rSet.getResource(regionResourceURI, true);
		RegionSet regionSet = (RegionSet) regionResource.getContents().get(0);
		
		UmlToNoSQLGeneratorLauncher.launch(regionResource);
		
		
	}

}
