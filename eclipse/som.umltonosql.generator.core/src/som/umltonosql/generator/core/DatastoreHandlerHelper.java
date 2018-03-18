package som.umltonosql.generator.core;

import java.text.MessageFormat;

import region.DatastoreDescriptor;
import region.MongoDescriptor;
import region.PostgresDescriptor;
import region.Region;

// should be specified in a property file
public class DatastoreHandlerHelper {

	public static String MONGO_LOCATION = "\"C:\\Program Files\\MongoDB\\Server\\3.0\\bin\\";
	
	public static String DRILL_LOCATION = "C:\\Users\\gwend\\Documents\\bin\\apache-drill-1.12.0\\bin";
	
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
