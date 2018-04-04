package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.PostgresDatastore;

import java.util.Collection;
import java.util.List;

/**
 * A Postgres-based {@link Bean} implementation.
 * <p>
 * This class delegates {@link Bean}-level operations to a {@link PostgresDatastore} that manages the underlying
 * database.
 * <p>
 * <b>Note:</b> the current implementation does not define its own UmlTo[No]SQLID implementation, and simply reuses
 * the {@link String}-based identifier to retrieve and compare elements.
 *
 * @see PostgresDatastore
 */
public class PostgresBean extends Bean<PostgresDatastore> {

    /**
     * Constructs a new {@link PostgresBean} with the provided {@code id} and {@code postgresDatastore}.
     * <p>
     * This constructor does not check whether the {@link PostgresDatastore} contains a record with the provided
     * {@code id}, and assumes that such record exists.
     * <p>
     * <b>Note:</b> {@link PostgresBean} implementations should provide a constructor with the exact same parameters,
     * in order to allow generic bean creation/retrieval method invocations.
     *
     * @param id                the unique identifier of the database record identifying the bean
     * @param postgresDatastore the {@link PostgresDatastore} used to access and manipulate the underlying Postgres
     *                          database
     * @see PostgresDatastore#getElement(String, Class)
     * @see PostgresDatastore#createElement(Class)
     */
    public PostgresBean(String id, PostgresDatastore postgresDatastore) {
        super(postgresDatastore);
        this.id = id;
    }

    /**
     * An utility method that retrieves the single-valued feature value associated to the provided {@code fieldName}.
     *
     * @param fieldName the name of the bean's field to access
     * @param <T>       the expected return type
     * @return the value associated to the {@code fieldName} field
     */
    protected <T> T getSimpleValue(String fieldName) {
        return (T) datastore.getSimpleValue(id, this.getClass(), fieldName);
    }

    /**
     * An utility method that retrieves the multi-valued feature value associated to the provided {@code fieldName}.
     * <p>
     * This method returns subclasses of {@link List}, note that if the feature's value is empty an empty
     * {@link List} is returned.
     *
     * @param fieldName the name of the bean's field to access
     * @param <T>       the expected return type
     * @return the value associated to the {@code fieldName} field
     */
    protected <T extends List> T getMultiValue(String fieldName) {
        return (T) datastore.getMultiValue(id, this.getClass(), fieldName);
    }

    /**
     * An utility method that updates the single-valued {@code fieldName} feature of the bean with the provided {@code
     * value}.
     *
     * @param fieldName the name of the bean's field to update
     * @param value     the new value to set
     */
    protected void updateSimpleValue(String fieldName, Object value) {
        datastore.updateSimpleValue(id, this.getClass(), fieldName, value);
    }

    /**
     * An utility method that adds the provided {@code value} to the multi-valued {@code fieldName} feature of the bean.
     * <p>
     * This method appends {@code value} to the multi-valued feature {@code fieldName}, and cannot replace and/or
     * delete existing values stored in the feature.
     *
     * @param fieldName the name of the bean's field to update
     * @param value     the new value to set
     */
    protected void addMultiValue(String fieldName, Object value) {
        datastore.addMultiValue(id, this.getClass(), fieldName, value);
    }

}
