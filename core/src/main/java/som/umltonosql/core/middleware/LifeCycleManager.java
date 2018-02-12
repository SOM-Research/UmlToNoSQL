package som.umltonosql.core.middleware;

import som.umltonosql.core.middleware.datastore.handler.DatastoreHandler;

import java.util.Collection;

public class LifeCycleManager {

    Collection<DatastoreHandler> handlers;

    public LifeCycleManager(Collection<DatastoreHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Starts the required database and query framework servers.
     */
    public void startServers() {
        for(DatastoreHandler handler : handlers) {
            handler.startDatastore();
        }
    }

    /**
     * Stops the databases and query framework servers, and release related resources.
     */
    public void stopServers() {
        for(DatastoreHandler handler : handlers) {
            handler.stopDatastore();
        }
    }

}
