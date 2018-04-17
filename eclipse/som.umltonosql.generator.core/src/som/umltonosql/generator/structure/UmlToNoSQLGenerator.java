package som.umltonosql.generator.structure;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Model;

import region.Region;

public abstract class UmlToNoSQLGenerator {
	
	protected Resource psmResource;
	
	protected File rootFolder;
	
	protected Region region;
	
	protected Model pimModel;
	
	public UmlToNoSQLGenerator(Resource psmResource, File rootFolder, Model pimModel) {
		this(psmResource, rootFolder, null, pimModel);
	}
	
	public UmlToNoSQLGenerator(Resource psmResource, File rootFolder, Region region, Model pimModel) {
		this.psmResource = psmResource;
		this.rootFolder = rootFolder;
		this.region = region;
		this.pimModel = pimModel;
	}
	
	public abstract void launch();

}
