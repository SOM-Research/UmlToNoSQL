package som.umltonosql.core.bean;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.datastore.store.MongoDatastore;

public abstract class MongoBean extends Bean<MongoDatastore> {

    // for Mongo base comparisons
    protected ObjectId objectId;

    // subclasses must not add new parameters
    public MongoBean(ObjectId id, MongoDatastore mongoDatastore) {
        super(mongoDatastore);
        this.id = id.getTimestamp();
        this.objectId = id;
    }

    // constructor for new instance creation
    public MongoBean(Document document, MongoDatastore mongoDatastore) {
        super(mongoDatastore);
        ObjectId id = document.getObjectId("_id");
        this.id = id.getTimestamp();
        this.objectId = id;
    }

    protected <T> T getValue(String field) {
        return (T) datastore.getValue(objectId, this.getClass(), field);
    }

    protected void updateField(String field, Object value) {
        datastore.updateDocument(objectId, this.getClass(), field, value);
    }

    @Override
    public int getId() {
        return id;
    }

    public ObjectId getObjectId() {
        return objectId;
    }
}
