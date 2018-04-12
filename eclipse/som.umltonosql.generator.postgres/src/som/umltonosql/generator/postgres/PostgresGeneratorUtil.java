package som.umltonosql.generator.postgres;

import static java.util.Objects.isNull;

import som.umltonosql.generator.util.AbstractRegionGeneratorUtil;

public class PostgresGeneratorUtil extends AbstractRegionGeneratorUtil {

	private static PostgresGeneratorUtil INSTANCE;
	
	public static PostgresGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new PostgresGeneratorUtil();
		} 
		return INSTANCE;
	}
	
	private PostgresGeneratorUtil() {
		super();
	}
	
}
