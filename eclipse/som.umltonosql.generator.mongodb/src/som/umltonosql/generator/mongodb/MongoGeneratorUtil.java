package som.umltonosql.generator.mongodb;

import static java.util.Objects.isNull;

import som.umltonosql.generator.util.AbstractRegionGeneratorUtil;

public class MongoGeneratorUtil extends AbstractRegionGeneratorUtil{

	private static MongoGeneratorUtil INSTANCE;
	
	public static MongoGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new MongoGeneratorUtil();
		} 
		return INSTANCE;
	}
	
	private MongoGeneratorUtil() {
		super();
	}
}
