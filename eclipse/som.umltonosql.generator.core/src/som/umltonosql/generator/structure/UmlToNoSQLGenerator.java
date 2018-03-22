package som.umltonosql.generator.structure;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import region.Region;

public abstract class UmlToNoSQLGenerator {
	
	protected Resource resource;
	
	protected File rootFolder;
	
	protected Region region;
	
	public UmlToNoSQLGenerator(Resource resource, File rootFolder) {
		this(resource, rootFolder, null);
	}
	
	public UmlToNoSQLGenerator(Resource resource, File rootFolder, Region region) {
		this.resource = resource;
		this.rootFolder = rootFolder;
		this.region = region;
	}
	
	public abstract void launch();

}
