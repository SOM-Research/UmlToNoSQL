package som.umltonosql.generator.mongodb;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import som.umltonosql.generator.mongodb.xtend.MongoBeanGenerator;

public class EcoreGeneratorModule extends AbstractGenericResourceRuntimeModule {

	@Override
	protected String getFileExtensions() {
		return "ecore";
	}

	@Override
	protected String getLanguageName() {
		return "";
	}
	
	public Class<? extends IGenerator> bindIGenerator() {
		return MongoBeanGenerator.class;
	}
	
	public Class<? extends ResourceSet> bindResourceSet() {
		return ResourceSetImpl.class;
	}

	
}
