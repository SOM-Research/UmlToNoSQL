package som.umltonosql.generator.mongodb;

import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import som.umltonosql.generator.mongodb.xtend.MongoBeanGenerator;

public class MongoGeneratorModule extends AbstractGenericResourceRuntimeModule {

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

	
}
