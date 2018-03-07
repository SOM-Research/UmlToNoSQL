package som.umltonosql.demo.core.generated;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.processor.MongoQueryProcessor;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.OrderLine;
import som.umltonosql.demo.mongodb.beans.Product;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class DemoMiddleware extends Middleware {

    private MongoDatastore mongoDatastore;

    private MongoQueryProcessor mongoProcessor;

    private static DemoMiddleware INSTANCE;

    public static DemoMiddleware getInstance() {
        return INSTANCE;
    }

    public DemoMiddleware(MongoDatastore mongoDatastore) {
        this.mongoDatastore = mongoDatastore;
        this.mongoProcessor = new MongoQueryProcessor(this, mongoDatastore);

        if (nonNull(INSTANCE)) {
            Log.warn("Multiple instances of DemoMiddleware have been created");
        }
        INSTANCE = this;
    }

    @Override
    public List<Datastore> getDatastores() {
        return Arrays.asList(mongoDatastore);
    }

    @Override
    public List<QueryProcessor> getProcessors() {
        return Arrays.asList(mongoProcessor);
    }

    public MongoDatastore getMongoDatastore() {
        return mongoDatastore;
    }

    public MongoQueryProcessor getMongoProcessor() {
        return mongoProcessor;
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

    @Override
    public Bean getElement(long id, Class<? extends Bean> clazz) throws ConsistencyException {
        if(clazz.equals(Order.class)) {
            return getOrder(id);
        }
        if(clazz.equals(Product.class)) {
            return getProduct(id);
        }
        if(clazz.equals(OrderLine.class)) {
            return getOrderLine(id);
        }
        throw new ConsistencyException(MessageFormat.format("Cannot get the element with the provided class : {0}",
                clazz.getName()));
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
}
