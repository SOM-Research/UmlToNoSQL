package som.umltonosql.core.bean;

import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.datastore.store.MongoDatastore;

/**
 * A MongoDB-based {@link Bean} implementation.
 * <p>
 * This class delegates {@link Bean}-level operations to a {@link MongoDatastore} that manages the underlying database.
 *
 * @see MongoDatastore
 */
public abstract class MongoBean extends Bean<MongoDatastore> {

    /**
     * The MongoDB implementation of the bean's unique identifier.
     * <p>
     * This identifier is used to manipulate elements within the same {@link MongoDatastore}, and can be constructed
     * from / serialized to the bean's {@link String} {@link #id} to manipulate cross-datastore elements.
     */
    protected ObjectId objectId;

    /**
     * Constructs a new {@link MongoBean} with the provided {@code id} and {@code mongoDatastore}.
     * <p>
     * This constructor does not check whether the {@link MongoDatastore} contains a document with the provided
     * {@code id}, and assumes that this document exists.
     * <p>
     * <b>Note:</b> {@link MongoBean} implementations should provide a constructor with the exact same parameters, in
     * order to allow generic bean creation/retrieval method invocations.
     *
     * @param id             the MongoDB unique identifier of the document representing the bean
     * @param mongoDatastore the {@link MongoDatastore} used to access and manipulate the underlying MongoDB database
     * @see MongoDatastore#getElement(ObjectId, Class)
     */
    public MongoBean(ObjectId id, MongoDatastore mongoDatastore) {
        super(mongoDatastore);
        this.id = id.toString();
        this.objectId = id;
    }

    /**
     * Constructs a new {@link MongoBean} with the provided {@code document} and {@code mongoDatastore}.
     * <p>
     * This constructor ensures that the created {@link MongoBean} is stored as a document, and sets its {@link #id}
     * with the document's identifier.
     * <p>
     * <b>Note:</b> {@link MongoBean} implementations should provide a constructor with the exact same parameters, in
     * order to allow generic bean creation/retrieval method invocations.
     *
     * @param document       the MongoDB document representing the bean
     * @param mongoDatastore the {@link MongoDatastore} used to access and manipulate the underlying MongoDB database
     * @see MongoDatastore#createElement(Class)
     */
    public MongoBean(Document document, MongoDatastore mongoDatastore) {
        super(mongoDatastore);
        ObjectId id = document.getObjectId("_id");
        this.id = id.toString();
        this.objectId = id;
    }

    /**
     * An utility method that retrieves the value of the provided {@code field} from the document representing the bean.
     *
     * @param field the identifier of the document's field to access
     * @param <T>   the expected return type
     * @return the document's value associated to the given {@code field}
     */
    protected <T> T getValue(String field) {
        return (T) datastore.getValue(objectId, this.getClass(), field);
    }

    /**
     * An utility method that updates the {@code field} of the document representing the bean with the provided
     * {@code value}.
     *
     * @param field the identifier of the document's field to update
     * @param value the new value to set
     */
    protected void updateField(String field, Object value) {
        datastore.updateDocument(objectId, this.getClass(), field, value);
    }

    /**
     * Returns the MongoDB identifier associated to this bean.
     * <p>
     * This method allows to speed-up MongoDB element comparisons, avoiding costly serialization/deserialization of
     * the database ID to its String representation.
     *
     * @return the MongoDB identifier associated to this bean
     */
    public ObjectId getObjectId() {
        return objectId;
    }
}
