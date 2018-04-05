package som.umltonosql.core.datastore.handler;

/**
 * An abstract class representing a system handler to manage the life cycle of a datastore.
 * <p>
 * {@link DatastoreHandler}s are typically used to start local database, initialize query solutions, and call local
 * scripts that need to be executed before any model manipulation.
 * <p>
 * Instances of this class are embedded in a {@link som.umltonosql.core.LifeCycleManager}, that takes care of the
 * application life-cycle.
 *
 * @see som.umltonosql.core.LifeCycleManager
 */
public abstract class DatastoreHandler {

    /**
     * Starts the underlying datastore.
     */
    public abstract void startDatastore();

    /**
     * Stops the underlying datastore.
     */
    public abstract void stopDatastore();
}
