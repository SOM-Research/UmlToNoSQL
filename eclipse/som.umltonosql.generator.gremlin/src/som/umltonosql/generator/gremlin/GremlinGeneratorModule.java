package som.umltonosql.generator.gremlin;

import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import som.umltonosql.generator.gremlin.xtend.GremlinBeanGenerator;

public class GremlinGeneratorModule extends AbstractGenericResourceRuntimeModule {

	@Override
	protected String getFileExtensions() {
		return "ecore";
	}

	@Override
	protected String getLanguageName() {
		return "";
	}
	
	public Class<? extends IGenerator> bindIGenerator() {
		return GremlinBeanGenerator.class;
	}

	
}
