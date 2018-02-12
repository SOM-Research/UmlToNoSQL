package som.umltonosql.core.datastore.store;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.inria.atlanmod.commons.log.Log;
import org.bson.Document;
import som.umltonosql.core.bean.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.mongodb.client.model.Filters.eq;

public class MongoDatastore extends Datastore {

    private MongoClient client;

    private MongoDatabase database;

    private MongoCollection<Document> collection;

    // This should be provided, but let's keep it simple for now
    private static String DRILL_DRIVER = "mongo2";

    public MongoDatastore(String path) {
        this(path.substring(0, path.indexOf(".")), path.substring(path.indexOf(".") + 1));
    }

    public MongoDatastore(String databaseName, String collectionName) {
        super(databaseName + "." + collectionName);
        Log.info("Creating MongoDatastore(db={0}, col={1}");
        client = new MongoClient();
        database = client.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    @Override
    public Bean getElement(int id, Class<? extends Bean> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor(int.class, MongoDatastore.class);
            return (Bean)constructor.newInstance(id, this);
        } catch(NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    public Document getDocument(int id) {
//        collection.find(eq("_id", id))
        return collection.find(eq("_id", "0" + Integer.toString(id))).first(); // only for the demo, should be int
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
