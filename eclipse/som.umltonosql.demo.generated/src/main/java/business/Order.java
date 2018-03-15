package business;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import core.DemoMiddleware;

import java.util.Date;
import java.lang.Boolean;
import java.lang.String;

public class Order extends MongoBean {
	
	public Order(ObjectId id, MongoDatastore mongoDatastore) {
		super(id, mongodatastore);
	}
	
	public Order(Document document, MongoDatastore mongoDatastore) {
		super(document, mongoDatastore);
	}
	
	public String getReference() {
		return getValue("reference");
	}
	
	public void setReference(String newReference) {
		updateField("reference", newReference);
	}
	public Date getShipmentDate() {
		Long timestamp = getValue("shipmentDate");
		return new Date(timestamp);
	}
	
	public void setShipmentDate(Date newShipmentDate) {
		Long timestamp = newShipmentDate.getTime();
		updateField("shipmentDate", timestamp);
	}
	public Date getDeliveryDate() {
		Long timestamp = getValue("deliveryDate");
		return new Date(timestamp);
	}
	
	public void setDeliveryDate(Date newDeliveryDate) {
		Long timestamp = newDeliveryDate.getTime();
		updateField("deliveryDate", timestamp);
	}
	public Boolean getPaid() {
		return getValue("paid");
	}
	
	public void setPaid(Boolean newPaid) {
		updateField("paid", newPaid);
	}
	public Client getClient() {
		ObjectId clientId = getValue("client");
		return DemoMiddleware.getClient(clientId.toString());
	}
	
	public void setClient(Client newClient) {
		updateField("client", newClient.getObjectId());
	}
}
