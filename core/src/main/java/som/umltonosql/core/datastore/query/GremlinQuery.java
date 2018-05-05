package som.umltonosql.core.datastore.query;

import som.umltonosql.core.bean.Bean;

public class GremlinQuery<T extends Bean> extends Query<T> {

    public GremlinQuery(String rawQuery, Class<T> resultType) {
        super(rawQuery, resultType);
        throw new UnsupportedOperationException("Gremlin queries are not supported for now");
    }
}
