package som.umltonosql.generator.core;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import som.umltonosql.generator.core.xtend.CoreXTendGenerator;

public class CoreGeneratorModule extends AbstractGenericResourceRuntimeModule {

	@Override
	protected String getFileExtensions() {
		return "ecore";
	}

	@Override
	protected String getLanguageName() {
		return "";
	}
	
	public Class<? extends IGenerator> bindIGenerator() {
		return CoreXTendGenerator.class;
	}
	
//	public Class<? extends ResourceSet> bindResourceSet() {
//		return ResourceSetImpl.class;
//	}

	
}
