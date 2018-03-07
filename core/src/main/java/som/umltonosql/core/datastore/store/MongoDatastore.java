package som.umltonosql.core.datastore.store;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import fr.inria.atlanmod.commons.log.Log;
import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.bean.MongoBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Objects.isNull;

public class MongoDatastore extends Datastore {

    private MongoClient client;

    private MongoDatabase database;

    // This should be provided, but let's keep it simple for now
    private static String DRILL_DRIVER = "mongo2";

    public MongoDatastore(String path) {
        super(path);
        Log.info("Creating MongoDatastore(db={0}, col={1}");
        client = new MongoClient();
        database = client.getDatabase(path);
    }

    @Override
    public MongoDatabase getDatabase() {
        return database;
    }

    @Override
    public Bean createElement(Class<? extends Bean> clazz) {
        Document document = new Document("_id", new ObjectId());
        MongoCollection<Document> collection = database.getCollection(getCollectionNameFromBeanClass(clazz));
//        if(isNull(collection)) {
//            database.createCollection(clazz.getName().toLowerCase());
//        }
        Bean bean = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(Document.class, MongoDatastore.class);
            bean = (Bean) constructor.newInstance(document, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
        collection.insertOne(document);
        return bean;
    }

    public Bean getElement(ObjectId id, Class<? extends Bean> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(ObjectId.class, MongoDatastore.class);
            return (Bean) constructor.newInstance(id, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    @Override
    public Bean getElement(long id, Class<? extends Bean> clazz) {
        // it should be a timestamp
        return this.getElement(new ObjectId(new Date(id)), clazz);
    }

    private String getCollectionNameFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    public Document getDocument(ObjectId id, Class<? extends Bean> clazz) {
        return database.getCollection(getCollectionNameFromBeanClass(clazz)).find(eq("_id", id)).first();
//        return collection.find(eq("_id", "0" + Integer.toString(id))).first(); // only for the demo, should be int
    }

    public void updateDocument(ObjectId id, Class<? extends Bean> clazz, String field, Object value) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(field, value));
        database.getCollection(getCollectionNameFromBeanClass(clazz)).updateOne(searchQuery, newDocument);
    }

    public Object getValue(ObjectId id, Class<? extends Bean> clazz, String field) {
        Document dbDocument = getDocument(id, clazz);
        return dbDocument.get(field);
    }

    @Override
    public void commit() throws Exception {
        // Nothing to do? (check the documentation)
    }

    @Override
    public String getDrillDriver() {
        return DRILL_DRIVER;
    }
}
