package som.umltonosql.demo.mongodb.beans;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.MongoBean;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.demo.core.generated.DemoMiddleware;

public class OrderLine extends MongoBean {

    public OrderLine(ObjectId id, MongoDatastore mongoDatastore) {
        super(id, mongoDatastore);
    }

    public OrderLine(Document document, MongoDatastore mongoDatastore) {
        super(document, mongoDatastore);
    }

    public Integer getQuantity() {
        return getValue("quantity");
    }

    public void setQuantity(Integer newQuantity) {
        updateField("quantity", newQuantity);
    }

    public Integer getProductPrive() {
        return getValue("productPrice");
    }

    public void setProductPrice(Integer newProductPrice) {
        updateField("productPrice", newProductPrice);
    }

    public Order getOrder() {
        // cardinality ?
        ObjectId orderId = getValue("order");
        return DemoMiddleware.getInstance().getOrder(orderId.getDate().getTime());
    }

    public void setOrder(Order newOrder) {
        updateField("order", newOrder.getObjectId());
    }

    public Product getProduct() {
        // cardinality ?
        ObjectId productId = getValue("product");
        return DemoMiddleware.getInstance().getProduct(productId.getDate().getTime());
    }

    public void setProduct(Product newProduct) {
        updateField("product", newProduct.getObjectId());
    }
}
