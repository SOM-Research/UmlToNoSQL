package som.umltonosql.core;

import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;

public abstract class Middleware {

    public abstract void commit() throws LifeCycleException;

    public abstract void checkConstraints() throws ConsistencyException;
}
