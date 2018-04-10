package som.umltonosql.generator.postgres;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;

import com.google.inject.Inject;

public class Generator {

	@Inject
	private IGenerator generator;
	@Inject
	private JavaIoFileSystemAccess fileAccess;
	
	public void runGenerator(Resource resource, String outputURI) {
		fileAccess.setOutputPath(outputURI);
		generator.doGenerate(resource, fileAccess);
		System.out.println("Code Generation Finished");
	}
}
