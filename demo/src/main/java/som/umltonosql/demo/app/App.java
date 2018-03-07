package som.umltonosql.demo.app;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.MongoQuery;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.datastore.query.processor.MongoQueryProcessor;
import som.umltonosql.demo.core.generated.DemoBootstrap;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.Product;

import java.util.Date;

public class App {

    public static void main(String[] args) throws Exception {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();
        Order order = middleware.createOrder();
        order.setReference("abc");
        order.setDeliveryDate(new Date());
        order.setShipmentDate(new Date(System.currentTimeMillis() - (3600 * 1000)));
        order.setPaid(false);
        Log.info("Created order ID: {0}", order.getObjectId());
        Log.info("Order reference : {0}", order.getReference());
        Log.info("Order shipment date : {0}", order.getShipmentDate());
        Log.info("Order delivery date : {0}", order.getDeliveryDate());
        Log.info("Order paid? : {0}", order.getPaid());

        // Create valid product
        Product validProduct = middleware.createProduct();
        validProduct.setName("Valid Product");
        validProduct.setDescription("A valid product with a valid price");
        validProduct.setPrice(10);

        // Create an invalid product
        Product invalidProduct = middleware.createProduct();
        invalidProduct.setName("Invalid Product");
        invalidProduct.setDescription("An invalid product with an invalid price");
        invalidProduct.setPrice(-10);

        MongoQueryProcessor mongoProcessor = new MongoQueryProcessor(middleware, middleware.getMongoDatastore());
        MongoQuery<Product> constraintQuery = new MongoQuery<>("db.product.find({price: {$lt: 0}})", Product.class);
        QueryResult result = mongoProcessor.query(constraintQuery);
        Log.info("?empty result : {0}", result.isEmpty());
        Log.info("execution time : {0}", result.getExecutionTime());
        Iterable<Bean> resultBeans = result.getResults();
        for(Bean rBean : resultBeans) {
            Log.info("Constraint violated for {0}", rBean.getId());
        }

        middleware.commit();
        demoBootstrap.getLcManager().stopServers();
    }
}
