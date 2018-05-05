package som.umltonosql.demo.core.generated;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.processor.DrillQueryProcessor;
import som.umltonosql.core.datastore.query.processor.GremlinQueryProcessor;
import som.umltonosql.core.datastore.query.processor.MongoQueryProcessor;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.datastore.store.GremlinDatastore;
import som.umltonosql.core.datastore.store.MongoDatastore;
import som.umltonosql.core.datastore.store.PostgresDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;
import som.umltonosql.demo.gremlin.bean.Bank;
import som.umltonosql.demo.gremlin.bean.Company;
import som.umltonosql.demo.mongodb.beans.Order;
import som.umltonosql.demo.mongodb.beans.OrderLine;
import som.umltonosql.demo.mongodb.beans.Product;
import som.umltonosql.demo.postgres.bean.Client;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class DemoMiddleware extends Middleware {

    private MongoDatastore mongoDatastore;

    private PostgresDatastore postgresDatastore;

    private GremlinDatastore gremlinDatastore;

    private MongoQueryProcessor mongoProcessor;

    private DrillQueryProcessor drillProcessor;

    private GremlinQueryProcessor gremlinProcessor;

    private static DemoMiddleware INSTANCE;

    public static DemoMiddleware getInstance() {
        return INSTANCE;
    }

    public DemoMiddleware(MongoDatastore mongoDatastore, PostgresDatastore postgresDatastore, GremlinDatastore
            gremlinDatastore) {
        this.mongoDatastore = mongoDatastore;
        this.postgresDatastore = postgresDatastore;
        this.gremlinDatastore = gremlinDatastore;
        this.mongoProcessor = new MongoQueryProcessor(this, mongoDatastore);
        this.drillProcessor = new DrillQueryProcessor(this);
        this.gremlinProcessor = new GremlinQueryProcessor(this, gremlinDatastore);

        if (nonNull(INSTANCE)) {
            Log.warn("Multiple instances of DemoMiddleware have been created");
        }
        INSTANCE = this;
    }

    @Override
    public List<Datastore> getDatastores() {
        return Arrays.asList(mongoDatastore, postgresDatastore, gremlinDatastore);
    }

    @Override
    public List<QueryProcessor> getProcessors() {
        return Arrays.asList(mongoProcessor, drillProcessor);
    }

    public MongoDatastore getMongoDatastore() {
        return mongoDatastore;
    }

    public PostgresDatastore getPostgresDatastore() {
        return postgresDatastore;
    }

    public GremlinDatastore getGremlinDatastore() {
        return gremlinDatastore;
    }

    public MongoQueryProcessor getMongoProcessor() {
        return mongoProcessor;
    }

    // Create new elements


    @Override
    public <T extends Bean> T createElement(Class<T> clazz) throws ConsistencyException {
        if(clazz.equals(Order.class)) {
            return (T) createOrder();
        }
        if(clazz.equals(Product.class)) {
            return (T) createProduct();
        }
        if(clazz.equals(OrderLine.class)) {
            return (T) createOrderLine();
        }
        if(clazz.equals(Client.class)) {
            return (T) createClient();
        }
        if(clazz.equals(Company.class)) {
            return (T) createCompany();
        }
        if(clazz.equals(Bank.class)) {
            return (T) createBank();
        }
        throw new ConsistencyException(MessageFormat.format("Cannot create the element with the provided class: " +
                "{0}", clazz.getName()));
    }

    public Order createOrder() {
        return (Order) mongoDatastore.createElement(Order.class);
    }

    public Product createProduct() {
        return (Product) mongoDatastore.createElement(Product.class);
    }

    public OrderLine createOrderLine() {
        return (OrderLine) mongoDatastore.createElement(OrderLine.class);
    }

    public Client createClient() {
        return (Client) postgresDatastore.createElement(Client.class);
    }

    public Company createCompany() {
        return (Company) gremlinDatastore.createElement(Company.class);
    }

    public Bank createBank() {
        return (Bank) gremlinDatastore.createElement(Bank.class);
    }

    @Override
    public <T extends Bean> T getElement(String id, Class<T> clazz) throws ConsistencyException {
        if(clazz.equals(Order.class)) {
            return (T) getOrder(id);
        }
        if(clazz.equals(Product.class)) {
            return (T) getProduct(id);
        }
        if(clazz.equals(OrderLine.class)) {
            return (T) getOrderLine(id);
        }
        if(clazz.equals(Client.class)) {
            return (T) getClient(id);
        }
        if(clazz.equals(Company.class)) {
            return (T) getCompany(id);
        }
        if(clazz.equals(Bank.class)) {
            return (T) getBank(id);
        }
        throw new ConsistencyException(MessageFormat.format("Cannot get the element with the provided class : {0}",
                clazz.getName()));
    }

    @Override
    public <T extends Bean> Iterable<T> getAllInstances(Class<T> clazz) throws ConsistencyException {
        if(clazz.equals(Order.class) || clazz.equals(Product.class) || clazz.equals(OrderLine.class)) {
            return mongoDatastore.getAllInstances(clazz);
        }
        if(clazz.equals(Client.class)) {
            return postgresDatastore.getAllInstances(clazz);
        }
        if(clazz.equals(Company.class) || clazz.equals(Bank.class)) {
            return gremlinDatastore.getAllInstances(clazz);
        }
        throw new ConsistencyException(MessageFormat.format("Cannot get the instances of the provided class: {0}", clazz.getName()));
    }

    // Get existing elements

    public Order getOrder(String id) {
        return (Order) mongoDatastore.getElement(id, Order.class);
    }

    public Product getProduct(String id) {
        return (Product) mongoDatastore.getElement(id, Product.class);
    }

    public OrderLine getOrderLine(String id) {
        return (OrderLine) mongoDatastore.getElement(id, OrderLine.class);
    }

    public Client getClient(String id) {
        return (Client) postgresDatastore.getElement(id, Client.class);
    }

    public Company getCompany(String id) {
        return (Company) gremlinDatastore.getElement(id, Company.class);
    }

    public Bank getBank(String id) {
        return (Bank) gremlinDatastore.getElement(id, Bank.class);
    }

    @Override
    public void commit() throws LifeCycleException {
        try {
            mongoDatastore.commit();
        } catch (Exception e) {
            throw new LifeCycleException("An error occured during the committing operations", e);
        }
    }
}
