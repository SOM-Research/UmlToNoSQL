package som.umltonosql.demo.app;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.constraint.ConstraintResult;
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

        // Create a valid order
        Order validOrder = middleware.createOrder();
        validOrder.setReference("A valid order");
        validOrder.setShipmentDate(new Date(System.currentTimeMillis() - (3600 * 1000)));
        validOrder.setDeliveryDate(new Date());
        validOrder.setPaid(true);

        // Create an invalid order
        Order invalidOrder = middleware.createOrder();
        invalidOrder.setReference("An invalid order");
        invalidOrder.setShipmentDate(new Date(System.currentTimeMillis() + (3600 * 1000)));
        invalidOrder.setDeliveryDate(new Date());
        invalidOrder.setPaid(true);

        Log.info("Checking Constraints");
        Iterable<ConstraintResult> constraintResults = middleware.checkConstraints();
        for(ConstraintResult result : constraintResults) {
            Log.info(result.toString());
        }

        middleware.commit();
        demoBootstrap.getLcManager().stopServers();
    }
}
