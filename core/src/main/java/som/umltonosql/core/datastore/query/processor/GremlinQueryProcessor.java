package som.umltonosql.core.datastore.query.processor;

import som.umltonosql.core.Middleware;
import som.umltonosql.core.datastore.query.GremlinQuery;
import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.store.GremlinDatastore;

public class GremlinQueryProcessor extends QueryProcessor<GremlinQuery> {

    private GremlinDatastore gremlinDatastore;

    public GremlinQueryProcessor(Middleware middleware, GremlinDatastore datastore) {
        super(middleware);
        this.gremlinDatastore = datastore;
    }

    @Override
    Iterable<String> doQuery(GremlinQuery query) {
        throw new UnsupportedOperationException("Gremlin query computation is not supported for now");
    }

    @Override
    public boolean accepts(Class<? extends Query> queryClazz) {
        if(queryClazz.equals(GremlinQuery.class)) {
            return true;
        }
        return false;
    }
}
