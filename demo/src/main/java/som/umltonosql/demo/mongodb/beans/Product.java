package som.umltonosql.demo.mongodb.beans;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.demo.core.generated.DemoMiddleware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product extends MongoBean {

    public Product(ObjectId id, MongoDatastore mongoDatastore) {
        super(id, mongoDatastore);
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

    public String description() {
        return getValue("description");
    }

    public void setDescription(String newDescription) {
        updateField("description", newDescription);
    }

    Iterable<OrderLine> getOrderLines() {
        List<ObjectId> orderLinesId = getValue("orderLines");
        List<OrderLine> orderLines = new ArrayList<>();
        for(ObjectId id : orderLinesId) {
            orderLines.add(DemoMiddleware.getInstance().getOrderLine(id.toString()));
        }
        return Collections.unmodifiableList(orderLines);
        // Don't check the cardinality, it's an *
    }

    public void addOrderLine(OrderLine newOrderLine) {
        List<ObjectId> orderLinesId = getValue("orderLines");
        orderLinesId.add(newOrderLine.getObjectId());
        updateField("orderLines", orderLinesId);
    }
}
