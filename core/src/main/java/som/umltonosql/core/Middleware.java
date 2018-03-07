package som.umltonosql.core;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.constraint.ConstraintManager;
import som.umltonosql.core.constraint.ConstraintResult;
import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;

import java.text.MessageFormat;
import java.util.List;

public abstract class Middleware {

    public abstract List<Datastore> getDatastores();

    public abstract List<QueryProcessor> getProcessors();

    public final <T extends Query> QueryProcessor<T> getProcessorFor(Class<T> queryClazz) {
        for(QueryProcessor processor : getProcessors()) {
            if(processor.accepts(queryClazz)) {
                return processor;
            }
        }
        throw new RuntimeException(MessageFormat.format("Cannot find a processor for {0}", queryClazz.getSimpleName()));
    }

    public abstract Bean getElement(long id, Class<? extends Bean> clazz) throws ConsistencyException;

    public abstract void commit() throws LifeCycleException;

    public Iterable<ConstraintResult> checkConstraints() throws ConsistencyException {
        return ConstraintManager.getInstance().checkConstraints();
    }
}
