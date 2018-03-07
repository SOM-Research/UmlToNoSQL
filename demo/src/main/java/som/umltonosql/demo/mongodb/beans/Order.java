package som.umltonosql.demo.mongodb.beans;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.demo.core.generated.DemoMiddleware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order extends MongoBean {

    public Order(ObjectId id, MongoDatastore mongoDatastore) {
        super(id, mongoDatastore);
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

    public Iterable<OrderLine> getOrderLines() throws ConsistencyException {
        List<ObjectId> orderLinesId = getValue("orderLines");
        if (!orderLinesId.isEmpty()) {
            List<OrderLine> orderLines = new ArrayList<>();
            for (ObjectId id : orderLinesId) {
                orderLines.add(DemoMiddleware.getInstance().getOrderLine(id.toString()));
            }
            return Collections.unmodifiableList(orderLines);
        } else {
            // 1..* constraint
            throw new ConsistencyException("There is no OrderLine associated to the Order, but the association " +
                    "cardinality is 1..*");
        }
    }

    public void addOrderLine(OrderLine newOrderLine) {
        List<ObjectId> orderLinesId = getValue("orderLines");
        // Don't check the cardinality here, otherwise we cannot add order lines to the order
        orderLinesId.add(newOrderLine.getObjectId());
        updateField("orderLines", orderLinesId);
    }
}
