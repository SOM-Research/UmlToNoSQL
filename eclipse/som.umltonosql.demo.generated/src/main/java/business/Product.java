package business;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import core.DemoMiddleware;

import java.lang.String;
import java.lang.Integer;

public class Product extends MongoBean {
	
	public Product(ObjectId id, MongoDatastore mongoDatastore) {
		super(id, mongodatastore);
	}
	
	public Product(Document document, MongoDatastore mongoDatastore) {
		super(document, mongoDatastore);
	}
	
	public String getName() {
		return getValue("name");
	}
	
	public void setName(String newName) {
		updateField("name", newName);
	}
	public Integer getPrice() {
		return getValue("price");
	}
	
	public void setPrice(Integer newPrice) {
		updateField("price", newPrice);
	}
	public String getDescription() {
		return getValue("description");
	}
	
	public void setDescription(String newDescription) {
		updateField("description", newDescription);
	}
}
