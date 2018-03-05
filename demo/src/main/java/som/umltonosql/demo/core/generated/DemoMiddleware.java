package som.umltonosql.demo.core.generated;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.OrderLine;
import som.umltonosql.demo.mongodb.beans.Product;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class DemoMiddleware extends Middleware {

    private MongoDatastore mongoDatastore;

    private static DemoMiddleware INSTANCE;

    public static DemoMiddleware getInstance() {
        return INSTANCE;
    }

    public DemoMiddleware(MongoDatastore mongoDatastore) {
        this.mongoDatastore = mongoDatastore;
        if (nonNull(INSTANCE)) {
            Log.warn("Multiple instances of DemoMiddleware have been created");
        }
        INSTANCE = this;
    }

    @Override
    public List<Datastore> getDatastores() {
        return Arrays.asList(mongoDatastore);
    }

    public MongoDatastore getMongoDatastore() {
        return mongoDatastore;
    }

    // Create new elements

    public Order createOrder() {
        return (Order) mongoDatastore.createElement(Order.class);
    }

    public Product createProduct() {
        return (Product) mongoDatastore.createElement(Product.class);
    }

    public OrderLine createOrderLine() {
        return (OrderLine) mongoDatastore.createElement(OrderLine.class);
    }

    // Get existing elements

    public Order getOrder(long id) {
        return (Order) mongoDatastore.getElement(id, Order.class);
    }

    public Product getProduct(long id) {
        return (Product) mongoDatastore.getElement(id, Product.class);
    }

    public OrderLine getOrderLine(long id) {
        return (OrderLine) mongoDatastore.getElement(id, OrderLine.class);
    }

    public void commit() throws LifeCycleException {
        try {
            mongoDatastore.commit();
        } catch (Exception e) {
            throw new LifeCycleException("An error occured during the committing operations", e);
        }
    }

    public void checkConstraints() throws ConsistencyException, RuntimeException {

    }
}
