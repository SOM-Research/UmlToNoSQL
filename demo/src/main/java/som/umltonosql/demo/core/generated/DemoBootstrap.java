package som.umltonosql.demo.core.generated;

import som.umltonosql.core.Bootstrap;
import som.umltonosql.core.LifeCycleManager;
import som.umltonosql.core.constraint.Constraint;
import som.umltonosql.core.constraint.ConstraintManager;
import som.umltonosql.core.datastore.handler.DrillHandler;
import som.umltonosql.core.datastore.handler.MongoHandler;
import som.umltonosql.core.datastore.query.DrillQuery;
import som.umltonosql.core.datastore.query.MongoQuery;
import som.umltonosql.core.datastore.store.GremlinDatastore;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.datastore.store.PostgresDatastore;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.Product;
import som.umltonosql.demo.postgres.bean.Client;

import java.util.Arrays;

public class DemoBootstrap extends Bootstrap {

    @Override
    public void init() {
        lcManager = new LifeCycleManager(Arrays.asList(
                /*
                 * No postgres handler for now, it is already started if installed on windows.
                 * However it is necessary to provide a solution that can start the postgres service if needed.
                 */
                new MongoHandler("\"C:\\Program Files\\MongoDB\\Server\\3.0\\bin\\"),
                new DrillHandler("C:\\Users\\gwend\\Documents\\bin\\apache-drill-1.12.0\\bin")
        ));

        // Creates the Datastores
        MongoDatastore businessDatastore = new MongoDatastore("demo");
        PostgresDatastore clientDatastore = new PostgresDatastore("jdbc:postgresql://127.0.0.1:5432/demo");
        GremlinDatastore economyDatastore = new GremlinDatastore("/tmp/gremlin-db-economy");

        middleware = new DemoMiddleware(businessDatastore, clientDatastore, economyDatastore);

        ConstraintManager.getInstance().addConstraint(new Constraint("validPrice", new MongoQuery("db.product.find" +
                        "({price: {$lt: 0}})", Product.class), middleware.getProcessorFor(MongoQuery.class)));
        ConstraintManager.getInstance().addConstraint(new Constraint("validOrder", new MongoQuery("db.order" +
                ".find({$where: \"(!(this.shipmentDate < this.deliveryDate))\"})", Order.class), middleware
                .getProcessorFor(MongoQuery.class)));
        ConstraintManager.getInstance().addConstraint(new Constraint("validPriceDrill", new DrillQuery("select * " +
                "from mongo2.demo.product where price < 0", Product.class), middleware.getProcessorFor(DrillQuery.class)));
        ConstraintManager.getInstance().addConstraint(new Constraint("orderPaid", new DrillQuery("select " +
                "client_id, count(*) from postgres.public.client_orders where order_id in (select cast(ord._id.`$oid`" +
                " as varchar) from mongo2.demo.`order` as ord where paid = false) group by client_id having count(*)" +
                " >= 3", Client.class), middleware.getProcessorFor(DrillQuery.class)));
    }
}
