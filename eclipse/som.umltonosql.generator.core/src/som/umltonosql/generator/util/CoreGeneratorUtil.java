package som.umltonosql.generator.core;

import static java.util.Objects.isNull;

import region.Partition;

public class CoreGeneratorUtil {
	
	private static CoreGeneratorUtil INSTANCE;
	
	public static CoreGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new CoreGeneratorUtil();
		}
		return INSTANCE;
	}
	
	private String appName;
	
	private Partition partition;
	
	private String corePackageName;
	
	private CoreGeneratorUtil() {
		
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getAppName() {
		return this.appName;
	}
	
	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	public Partition getPartition() {
		return this.partition;
	}
	
	public void setCorePackageName(String corePackageName) {
		this.corePackageName = corePackageName;
	}
	
	public String getCorePackageName() {
		return corePackageName;
	}
	

}
