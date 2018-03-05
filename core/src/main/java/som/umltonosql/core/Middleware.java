package som.umltonosql.core;

import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;

import java.util.List;

public abstract class Middleware {

    public abstract List<Datastore> getDatastores();

    public abstract void commit() throws LifeCycleException;

    public abstract void checkConstraints() throws ConsistencyException;
}
