package som.umltonosql.core.middleware;

import som.umltonosql.core.middleware.exceptions.ConsistencyException;
import som.umltonosql.core.middleware.exceptions.LifeCycleException;

public abstract class Middleware {

    public abstract void commit() throws LifeCycleException;

    public abstract void checkConstraints() throws ConsistencyException;
}
