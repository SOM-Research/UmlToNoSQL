package som.umltonosql.core.middleware.bean;

import org.bson.Document;
import som.umltonosql.core.middleware.datastore.store.MongoDatastore;

public abstract class MongoBean extends Bean {

    protected Document document;

    // subclasses must not add new parameters
    public MongoBean(int id, MongoDatastore mongoDatastore) {
        super(mongoDatastore);
        this.id = id;
        document = mongoDatastore.getDocument(id);
    }

    @Override
    public int getId() {
        return id;
    }
}
