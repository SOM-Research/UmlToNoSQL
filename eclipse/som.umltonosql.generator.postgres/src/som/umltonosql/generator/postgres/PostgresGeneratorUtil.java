package som.umltonosql.generator.postgres;

import static java.util.Objects.isNull;

import region.Region;

public class PostgresGeneratorUtil {

	private static PostgresGeneratorUtil INSTANCE;
	
	public static PostgresGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new PostgresGeneratorUtil();
		} 
		return INSTANCE;
	}
	
	private String postgresBasePackage;
	
	private String appName;
	
	private String corePackageName;
	
	private Region region;
	
	private PostgresGeneratorUtil() {
		
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getAppName() {
		return this.appName;
	}
	
	public void setCorePackageName(String corePackageName) {
		this.corePackageName = corePackageName;
	}
	
	public String getCorePackageName() {
		return this.corePackageName;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Region getRegion() {
		return this.region;
	}
	
	public void setPostgresBasePackage(String postgresBasePackage) {
		this.postgresBasePackage = postgresBasePackage;
	}
	
	public String getPostgresBasePackage() {
		return this.postgresBasePackage;
	}
	
}
