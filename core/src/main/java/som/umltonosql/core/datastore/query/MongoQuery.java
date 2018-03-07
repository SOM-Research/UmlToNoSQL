package som.umltonosql.core.datastore.query;

import som.umltonosql.core.bean.Bean;

public class MongoQuery<T extends Bean> extends Query<T> {

    public MongoQuery(String rawQuery, Class<T> resultType) {
        super(rawQuery, resultType);
    }
}
