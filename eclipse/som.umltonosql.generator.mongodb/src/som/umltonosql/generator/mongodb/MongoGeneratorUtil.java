package som.umltonosql.generator.mongodb;

import static java.util.Objects.isNull;

public class MongoGeneratorUtil {

	private static MongoGeneratorUtil INSTANCE;
	
	public static MongoGeneratorUtil getInstance() {
		if(isNull(INSTANCE)) {
			INSTANCE = new MongoGeneratorUtil();
		} 
		return INSTANCE;
	}
	
	private String mongoBasePackage;
	
	private MongoGeneratorUtil() {
		
	}
	
	
	
	public void setMongoBasePackage(String mongoBasePackage) {
		this.mongoBasePackage = mongoBasePackage;
	}
	
	public String getMongoBasePackage() {
		return this.mongoBasePackage;
	}
	
}
