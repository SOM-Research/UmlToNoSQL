package som.umltonosql.generator.core;

import java.text.MessageFormat;

import region.DatastoreDescriptor;
import region.MongoDescriptor;
import region.PostgresDescriptor;
import region.Region;

// should be specified in a property file
public class DatastoreHandlerHelper {

	public static String MONGO_LOCATION = "\"\\\"C:\\\\Program Files\\\\MongoDB\\\\Server\\\\3.0\\\\bin\\\\\"";
	
	public static String DRILL_LOCATION = "\"C:\\\\Users\\\\gwend\\\\Documents\\\\bin\\\\apache-drill-1.12.0\\\\bin\"";
	
	public static String getHandlerType(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "MongoHandler";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "DrillHandler";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler type for {0}", descriptor));
	}
	
	public static String getHandlerImport(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return "som.umltonosql.core.datastore.handler.MongoHandler";
		}
		if(descriptor instanceof PostgresDescriptor) {
			return "som.umltonosql.core.datastore.handler.DrillHandler";
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler import for {0}", descriptor));
	}
	
	public static String getHandlerLocation(Region region) {
		DatastoreDescriptor descriptor = region.getDatastoreDescriptor();
		if(descriptor instanceof MongoDescriptor) {
			return MONGO_LOCATION;
		}
		if(descriptor instanceof PostgresDescriptor) {
			return DRILL_LOCATION;
		}
		throw new RuntimeException(MessageFormat.format("Cannot find the handler location for {0}", descriptor));
	}
	
}
