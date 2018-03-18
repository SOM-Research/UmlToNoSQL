package som.umltonosql.generator.mongodb

import java.util.Arrays
import java.util.List
import som.umltonosql.generator.core.RegionGeneratorHelper

class MongoRegionGeneratorHelper extends RegionGeneratorHelper {
	
	// Bootstrap
	// from RegionModel
	override String getHandlerInvocation() {
		'''new MongoHandler("\"C:\\Program Files\\MongoDB\\Server\\3.0\\bin\\"),'''
	}

	// from RegionModel
	override String getDatastoreInvocation() {
		'''MongoDatastore businessDatastore = new MongoDatastore("demo");'''
	}
	
	override String getDatastoreType() {
		"MongoDatastore"
	}
	
	// from RegionModel
	override String getDatastoreVariableName() {
		"businessDatastore"
	}
	
	// Middleware
	
	override String getQueryProcessorInvocation() {
		'''this.mongoProcessor = new MongoQueryProcessor(this, mongoDatastore);'''
	}
	
	override String getQueryProcessorType() {
		"MongoQueryProcessor"
	}
	
	override getQueryProcessorVariableName() {
		return "mongoProcessor"
	}
	
	// from RegionModel
	override List<String> getBeanTypes() {
		Arrays.asList("Order", "Product", "OrderLine")
	}
}
