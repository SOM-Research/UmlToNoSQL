package som.umltonosql.core;

import som.umltonosql.core.datastore.handler.DatastoreHandler;

import java.util.Collection;

/**
 * Manages the life-cycle of application's databases and query solutions.
 * <p>
 * This class is used to wrap database starting scripts invocations and query solutions setups. It is typically
 * initialized by a {@link Bootstrap} implementation, that creates the {@link DatastoreHandler}s to be used by the
 * application.
 * <p>
 * Client applications need to explicitly start the servers ({@link LifeCycleManager#startServers()}) before
 * manipulating the stored model. The {@link LifeCycleManager#stopServers()} should also be called when the data
 * stores are no longer needed. Shutting down the application without stopping the servers may create consistency
 * issues.
 *
 * @see som.umltonosql.core.exceptions.LifeCycleException
 * @see Bootstrap
 */
public class LifeCycleManager {

    /**
     * The {@link DatastoreHandler}s managed by the {@link LifeCycleManager}.
     */
    private Collection<DatastoreHandler> handlers;

    /**
     * Constructs a new {@link LifeCycleManager} managing the provided {@code handlers}.
     *
     * @param handlers the {@link Collection} of {@link DatastoreHandler}s to manage
     */
    public LifeCycleManager(Collection<DatastoreHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Starts the required data store and query framework servers.
     */
    public void startServers() {
        for (DatastoreHandler handler : handlers) {
            handler.startDatastore();
        }
    }

    /**
     * Stops the data store and query framework servers, and release related resources.
     * <p>
     * This method should be called when the data stores are no longer needed. Shutting down the application without
     * stopping the servers may create consistency issues.
     */
    public void stopServers() {
        for (DatastoreHandler handler : handlers) {
            handler.stopDatastore();
        }
    }

}
