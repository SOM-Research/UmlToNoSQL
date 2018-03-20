package som.umltonosql.generator.core;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import region.RegionPackage;
import region.RegionSet;

public class CoreGenerator {

	public static void main(String[] args) {
		
		RegionPackage.eINSTANCE.eClass();
		
		// Should be a platform URI
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
		
		UmlToNoSQLGeneratorLauncher.launch(regionResource);
		
		
	}

}
