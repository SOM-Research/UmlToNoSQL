package som.umltonosql.generator.gremlin;

import static java.util.Objects.isNull;

import som.umltonosql.generator.util.AbstractRegionGeneratorUtil;

public class GremlinGeneratorUtil extends AbstractRegionGeneratorUtil{

	private static GremlinGeneratorUtil INSTANCE;
	
	public static GremlinGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new GremlinGeneratorUtil();
		} 
		return INSTANCE;
	}
	
	private GremlinGeneratorUtil() {
		super();
	}
}
