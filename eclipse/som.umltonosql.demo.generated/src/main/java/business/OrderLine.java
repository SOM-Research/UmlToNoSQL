package business;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import core.DemoMiddleware;

import java.lang.String;

public class OrderLine extends MongoBean {
	
	public OrderLine(ObjectId id, MongoDatastore mongoDatastore) {
		super(id, mongodatastore);
	}
	
	public OrderLine(Document document, MongoDatastore mongoDatastore) {
		super(document, mongoDatastore);
	}
	
}
