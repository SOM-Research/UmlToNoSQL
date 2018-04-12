package som.umltonosql.generator.core;

import java.text.MessageFormat;

import region.Region;
import region.StorageKind;

// should be specified in a property file
public class DatastoreHandlerHelper {

	public static String MONGO_LOCATION = "\"\\\"C:\\\\Program Files\\\\MongoDB\\\\Server\\\\3.0\\\\bin\\\\\"";
	
	public static String DRILL_LOCATION = "\"C:\\\\Users\\\\gwend\\\\Documents\\\\bin\\\\apache-drill-1.12.0\\\\bin\"";
	
	public static String getHandlerType(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "MongoHandler";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "DrillHandler";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler type for {0}", sKind));
	}
	
	public static String getHandlerImport(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return "som.umltonosql.core.datastore.handler.MongoHandler";
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return "som.umltonosql.core.datastore.handler.DrillHandler";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler import for {0}", sKind));
	}
	
	public static String getHandlerLocation(Region region) {
		StorageKind sKind = region.getStorage();
		if(sKind.equals(StorageKind.DOCUMENT)) {
			return MONGO_LOCATION;
		}
		if(sKind.equals(StorageKind.RELATIONAL)) {
			return DRILL_LOCATION;
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler location for {0}", sKind));
	}
	
}
