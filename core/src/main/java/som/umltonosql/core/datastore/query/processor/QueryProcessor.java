package som.umltonosql.core.datastore.query.processor;

import som.umltonosql.core.Middleware;
import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.QueryResult;

public abstract class QueryProcessor<Q extends Query> {

    protected Middleware middleware;

    public QueryProcessor(Middleware middleware) {
        this.middleware = middleware;
    }

    public final QueryResult query(Q query) {
        long before = System.currentTimeMillis();
        Iterable<String> rawResult = doquery(query);
        long after = System.currentTimeMillis();
        long executionTime = (after - before);
        return new QueryResult(rawResult, query.getResultType(), executionTime, middleware);
    }

    abstract Iterable<String> doquery(Q query);

    public abstract boolean accepts(Class<? extends Query> queryClazz);

}
