package som.umltonosql.core.datastore.store;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import fr.inria.atlanmod.commons.log.Log;
import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.bean.MongoBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Objects.isNull;

/**
 * A MongoDB {@link Datastore} implementation that maps modeling-level operations to MongoDB operations.
 * <p>
 * A {@link MongoDatastore} is associated to a unique MongoDB database, and manages its <i>collections</i> and the
 * <i>documents</i> it contains. Note that models containing multiple regions mapped to MongoDB are managed by
 * different instances of this class, that can be retrieved in the application's
 * {@link som.umltonosql.core.Middleware} instance.
 * <p>
 * This class provides utility methods that can be called by {@link MongoBean} instances to retrieve values and
 * update their attributes.
 *
 * @see MongoBean
 * @see Middleware#getDatastores()
 */
public class MongoDatastore extends Datastore {

    /**
     * The {@link MongoClient} used to manipulate the underlying MongoDB database.
     */
    private MongoClient client;

    /**
     * The {@link MongoDatabase} instance managed by this class.
     */
    private MongoDatabase database;

    /**
     * The Drill driver associated to this class.
     * <p>
     * This value is used to construct {@link som.umltonosql.core.datastore.query.DrillQuery} instances that target
     * this datastore.
     * <p>
     * TODO: Drill drivers should be externalized. Using Drill as a query solution is not the
     * {@link MongoDatastore}'s responsibility.
     *
     * @see som.umltonosql.core.datastore.query.DrillQuery
     * @see som.umltonosql.core.datastore.query.processor.DrillQueryProcessor
     */
    private static String DRILL_DRIVER = "mongo2";

    /**
     * Constructs a new {@link MongoDatastore} managing the MongoDB database identified by the provided {@code dbName}.
     * <p>
     * This constructor only manages local databases (i.e. MongoDB servers running on localhost with the default
     * MongoDB port), use {@link #MongoDatastore(String, int, String)} to create a {@link MongoDatastore} managing a
     * remote MongoDB server.
     * <p>
     * The expected {@code dbName} should only contain the identifier of the local MongoDB database to access.
     *
     * @param dbName the identifier of the local MongoDB database to access
     * @see #MongoDatastore(String, int, String)
     */
    public MongoDatastore(String dbName) {
        super(dbName);
        client = new MongoClient();
        database = client.getDatabase(dbName);
    }

    /**
     * Constructs a new {@link MongoDatastore} managing the MongoDB database located at the provided {@code
     * host}:{@code port} with the given {@code dbName}.
     * <p>
     * This constructor can be used to create {@link MongoDatastore} instances managing local servers or remote
     * servers.
     * <p>
     * The expected {@code dbName} should only contain the identifier of the MongoDB database to access.
     *
     * @param host   the host used to access the remote server
     * @param port   the port of the provided host that allows to access the remote server
     * @param dbName the identifier of the MongoDB database to access
     * @see #MongoDatastore(String)
     */
    public MongoDatastore(String host, int port, String dbName) {
        super(dbName);
        client = new MongoClient(host, port);
        database = client.getDatabase(dbName);
    }

    /**
     * {@inheritDoc}
     *
     * @return the internal {@link MongoDatabase} instance used to manipulate the database
     */
    @Override
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Creates and stores a new {@code clazz} {@link MongoBean} in the managed MongoDB database.
     * <p>
     * This method is part of the UmlToNoSQL generic API, that can be used in generated code to create {@link Bean}'s
     * subclasses instances transparently. Note that the generated code embeds a
     * {@link som.umltonosql.core.Middleware} subclass that should be used by end users to create
     * statically-typed {@link Bean}s.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to create
     * @return the created {@link MongoBean}
     * @see Middleware
     */
    @Override
    public MongoBean createElement(Class<? extends Bean> clazz) {
        Document document = new Document("_id", new ObjectId());
        MongoCollection<Document> collection = database.getCollection(getCollectionNameFromBeanClass(clazz));
//        if(isNull(collection)) {
//            database.createCollection(clazz.getName().toLowerCase());
//        }
        MongoBean bean = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(Document.class, MongoDatastore.class);
            bean = (MongoBean) constructor.newInstance(document, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
        collection.insertOne(document);
        return bean;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Note:</b> to retrieve a {@link MongoBean} instance based on its {@link ObjectId} see
     * {@link MongoDatastore#getElement(ObjectId, Class)}.
     *
     * @see #getElement(ObjectId, Class)
     */
    @Override
    public Bean getElement(String id, Class<? extends Bean> clazz) {
        /*
         * This code assumes that the provided id can be used to construct an ObjectId instance. We may need
         * something more robust to handle potential bean retrieval errors.
         */
        return this.getElement(new ObjectId(id), clazz);
    }

    /**
     * Retrieves the {@link MongoBean} element with the type {@code clazz} and the provided {@link ObjectId}.
     * <p>
     * <b>Note:</b> this method only works with {@link MongoBean} instances stored in MongoDB, and does not try to
     * convert
     * the provided {@code id} into a database-compatible identifier. To retrieve a {@link MongoBean} instance with
     * another type of identifier see {@link MongoDatastore#getElement(String, Class)}.
     *
     * @param id    the unique, MongoDB specific, identifier of the {@link MongoBean} element to retrieve
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve
     * @return the retrieved {@link MongoBean}
     * @see #getElement(String, Class)
     */
    public MongoBean getElement(ObjectId id, Class<? extends Bean> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(ObjectId.class, MongoDatastore.class);
            return (MongoBean) constructor.newInstance(id, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    /**
     * An utility method that returns the name of MongoDB collection associated to the provided {@code clazz}.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve the MongoDB collection of
     * @return the name of the MongoDB collection associated to the provided {@code clazz}
     */
    private String getCollectionNameFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    /**
     * An utility method that returns the MongoDB {@link Document} with the provided {@code id} representing an
     * instance of the provided {@code clazz}.
     * <p>
     * <b>Note:</b> this method is internally used to access stored {@link Document}s and retrieve specific values or
     * update fields.
     *
     * @param id    the unique, MongoDB specific, identifier of the {@link Document} to retrieve
     * @param clazz the {@link Class} represented by the {@link Document} to retrieve
     * @return the retrieved {@link Document}
     * @see #getValue(ObjectId, Class, String)
     * @see #updateDocument(ObjectId, Class, String, Object)
     */
    private Document getDocument(ObjectId id, Class<? extends Bean> clazz) {
        return database.getCollection(getCollectionNameFromBeanClass(clazz)).find(eq("_id", id)).first();
    }

    /**
     * An utility method used in {@link MongoBean} to update fields of an existing {@link Document}.
     * <p>
     * <b>Note:</b> calling this method directly may result in inconsistent {@link MongoBean} instances. The
     * operation performed in this class are not reflected at the {@link Bean}-level. To update an existing
     * {@link Bean} see its generated API instead.
     *
     * @param id    the unique, MongoDB specific, identifier of the {@link Document} to update
     * @param clazz the {@link Class} of the {@link MongoBean} element to update
     * @param field the name of the {@link Document}'s field to update
     * @param value the new value to set for the provided {@code field}
     * @see MongoBean
     */
    public void updateDocument(ObjectId id, Class<? extends Bean> clazz, String field, Object value) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(field, value));
        database.getCollection(getCollectionNameFromBeanClass(clazz)).updateOne(searchQuery, newDocument);
    }

    /**
     * An utility method used in {@link MongoBean} to retrieve the value of a given {@code field}.
     *
     * @param id    the unique, MongoDB specific, identifier of the {@link MongoBean} element to retrieve the value from
     * @param clazz the {@link Class} of the {@link MongoBean} element to retrieve the value from
     * @param field the name of the {@link Document}'s field value to retrieve
     * @return the retrieved value
     * @see MongoBean
     */
    public Object getValue(ObjectId id, Class<? extends Bean> clazz, String field) {
        Document dbDocument = getDocument(id, clazz);
        return dbDocument.get(field);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Note:</b> the current implementation of the {@link MongoDatastore} does not provide a transactional
     * environment, and commits each performed operation. As a result, calling this method will not have any impact on
     * the stored data.
     */
    @Override
    public void commit() throws Exception {
        // Nothing to do? (check the documentation)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDrillDriver() {
        return DRILL_DRIVER;
    }
}
