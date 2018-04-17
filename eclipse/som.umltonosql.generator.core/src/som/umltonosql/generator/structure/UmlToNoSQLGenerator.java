package som.umltonosql.generator.structure;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import region.Region;

public abstract class UmlToNoSQLGenerator {
	
	protected Resource psmResource;
	
	protected File rootFolder;
	
	protected Region region;
	
	public UmlToNoSQLGenerator(Resource psmResource, File rootFolder) {
		this(psmResource, rootFolder, null);
	}
	
	public UmlToNoSQLGenerator(Resource psmResource, File rootFolder, Region region) {
		this.psmResource = psmResource;
		this.rootFolder = rootFolder;
		this.region = region;
	}
	
	public abstract void launch();

}
