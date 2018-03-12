package som.umltonosql.demo.app;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.demo.core.generated.DemoBootstrap;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.postgres.bean.Client;

public class App {

    public static void main(String[] args) throws Exception {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();

        // Create valid product
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
//
//        // Create a valid order
//        Order validOrder = middleware.createOrder();
//        validOrder.setReference("A valid order");
//        validOrder.setShipmentDate(new Date(System.currentTimeMillis() - (3600 * 1000)));
//        validOrder.setDeliveryDate(new Date());
//        validOrder.setPaid(true);
//
//        // Create an invalid order
//        Order invalidOrder = middleware.createOrder();
//        invalidOrder.setReference("An invalid order");
//        invalidOrder.setShipmentDate(new Date(System.currentTimeMillis() + (3600 * 1000)));
//        invalidOrder.setDeliveryDate(new Date());
//        invalidOrder.setPaid(true);
//

        // statement.execute("insert into client values ('abc', 'name', 'address');");
        // Create a Client
        Client client = middleware.getClient("abc");
        Log.info("Client id: {0}", client.getId());
        Log.info("Client name: {0}", client.getName());
        Log.info("Client address {0}", client.getAddress());

        client.setName("myName");
        client.setAddress("myAddress");

        Log.info("Client id: {0}", client.getId());
        Log.info("Client name: {0}", client.getName());
        Log.info("Client address {0}", client.getAddress());

//        Log.info("Checking Constraints");
//        Iterable<ConstraintResult> constraintResults = middleware.checkConstraints();
//        for(ConstraintResult result : constraintResults) {
//            Log.info(result.toString());
//        }

        middleware.commit();
        demoBootstrap.getLcManager().stopServers();
    }
}
