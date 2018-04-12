package som.umltonosql.generator.postgres;

import static java.util.Objects.isNull;

import region.Region;
import som.umltonosql.generator.core.RegionGeneratorUtil;

public class PostgresGeneratorUtil extends RegionGeneratorUtil {

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
