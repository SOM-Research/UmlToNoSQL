package som.umltonosql.demo.app;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.BsonDocument;
import org.bson.Document;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.demo.core.generated.DemoBootstrap;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.Product;

import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();
//        Order order = middleware.createOrder();
//        order.setReference("abc");
//        order.setDeliveryDate(new Date());
//        order.setShipmentDate(new Date(System.currentTimeMillis() - (3600 * 1000)));
//        order.setPaid(false);
//        Log.info("Created order ID: {0}", order.getObjectId());
//        Log.info("Order reference : {0}", order.getReference());
//        Log.info("Order shipment date : {0}", order.getShipmentDate());
//        Log.info("Order delivery date : {0}", order.getDeliveryDate());
//        Log.info("Order paid? : {0}", order.getPaid());
//
//        // Create valid product
//        Product validProduct = middleware.createProduct();
//        validProduct.setName("Valid Product");
//        validProduct.setDescription("A valid product with a valid price");
//        validProduct.setPrice(10);
//
//        // Create an invalid product
//        Product invalidProduct = middleware.createProduct();
//        invalidProduct.setName("Invalid Product");
//        invalidProduct.setDescription("An invalid product with an invalid price");
//        invalidProduct.setPrice(-10);

        MongoDatastore datastore = middleware.getMongoDatastore();
        Document result = datastore.getDatabase().runCommand(new Document("eval", "function() {" +
                "var cursor = db.product.find({price: {$lt: 0}});" +
                "return cursor.toArray();" +
                "}"));
        Log.info("Query result : {0}", result.toJson());
        List<Document> results = (List<Document>) result.get("retval");
        for(Document res : results) {
            Log.info("Constraint violated for {0}", res.getObjectId("_id"));
        }

        middleware.commit();
        demoBootstrap.getLcManager().stopServers();
    }
}
