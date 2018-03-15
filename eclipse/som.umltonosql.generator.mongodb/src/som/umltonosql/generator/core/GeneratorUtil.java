package som.umltonosql.generator.core;

import static java.util.Objects.isNull;

public class GeneratorUtil {

	private static GeneratorUtil INSTANCE;
	
	public static GeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new GeneratorUtil();
		}
		return INSTANCE;
	}
	
	private String appName;
	
	private String corePackageName;
	
	private GeneratorUtil() {
		
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
		return corePackageName;
	}
	
}
