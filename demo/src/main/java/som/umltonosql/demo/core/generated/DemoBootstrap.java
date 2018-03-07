package som.umltonosql.demo.core.generated;

import som.umltonosql.core.Bootstrap;
import som.umltonosql.core.constraint.Constraint;
import som.umltonosql.core.constraint.ConstraintManager;
import som.umltonosql.core.LifeCycleManager;
import som.umltonosql.core.datastore.handler.MongoHandler;
import som.umltonosql.core.datastore.query.MongoQuery;
import som.umltonosql.core.datastore.query.processor.MongoQueryProcessor;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.region.ModelRegionManager;
import som.umltonosql.core.region.Region;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.Product;

import java.util.Arrays;

public class DemoBootstrap extends Bootstrap {

    public void init() {
        lcManager = new LifeCycleManager(Arrays.asList(
                new MongoHandler("\"C:\\Program Files\\MongoDB\\Server\\3.0\\bin\\")
        ));

        // Creates the Datastores
        MongoDatastore businessDatastore = new MongoDatastore("demo");

        // Create the Regions
        Region businessRegion = new Region("business", businessDatastore);
        ModelRegionManager regionManager = new ModelRegionManager();
        regionManager.addRegion(businessRegion);

        middleware = new DemoMiddleware(businessDatastore);

        ConstraintManager.getInstance().addConstraint(new Constraint("validPrice", new MongoQuery("db.product.find" +
                        "({price: {$lt: 0}})", Product.class), middleware.getProcessorFor(MongoQuery.class)));
        ConstraintManager.getInstance().addConstraint(new Constraint("validOrder", new MongoQuery("db.order" +
                ".find({$where: \"(!(this.shipmentDate < this.deliveryDate))\"})", Order.class), middleware
                .getProcessorFor(MongoQuery.class)));
    }
}
