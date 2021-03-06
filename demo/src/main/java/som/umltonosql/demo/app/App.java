package som.umltonosql.demo.app;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.constraint.ConstraintResult;
import som.umltonosql.demo.core.generated.DemoBootstrap;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.Product;
import som.umltonosql.demo.postgres.bean.Client;

import java.sql.Statement;
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

        // Create a valid, unpaid order
        Order unpaidOrder = middleware.createOrder();
        unpaidOrder.setReference("An unpaid order");
        unpaidOrder.setShipmentDate(new Date());
        unpaidOrder.setDeliveryDate(new Date(System.currentTimeMillis() + (3600 * 1000)));
        unpaidOrder.setPaid(false);

        Order unpaidOrder2 = middleware.createOrder();
        unpaidOrder2.setReference("A second unpaid order");
        unpaidOrder2.setShipmentDate(new Date());
        unpaidOrder2.setDeliveryDate(new Date(System.currentTimeMillis() + (3600 * 1000)));
        unpaidOrder2.setPaid(false);

        Order unpaidOrder3 = middleware.createOrder();
        unpaidOrder3.setReference("A second unpaid order");
        unpaidOrder3.setShipmentDate(new Date());
        unpaidOrder3.setDeliveryDate(new Date(System.currentTimeMillis() + (3600 * 1000)));
        unpaidOrder3.setPaid(false);


        // Create a Client

        Client client = middleware.createClient();
        client.setName("John Doe");
        client.setAddress("Wall Street");
        client.addOrder(validOrder);
        client.addOrder(unpaidOrder);
        client.addOrder(unpaidOrder2);
        client.addOrder(unpaidOrder3);

        Log.info("Checking Constraints");
        Iterable<ConstraintResult> constraintResults = middleware.checkConstraints();
        for(ConstraintResult result : constraintResults) {
            Log.info(result.toString());
        }

        middleware.commit();
        demoBootstrap.getLcManager().stopServers();
    }
}
