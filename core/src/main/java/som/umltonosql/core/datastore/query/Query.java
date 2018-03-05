package som.umltonosql.core.datastore.query;

import som.umltonosql.core.bean.Bean;

public class Query<T extends Bean> {

    protected String rawQuery;

    protected Class<T> resultType;

    public Query(String rawQuery, Class<T> resultType) {
        this.rawQuery = rawQuery;
        this.resultType = resultType;
    }

    public String getRawQuery() {
        return rawQuery;
    }

    public Class<T> getResultType() {
        return resultType;
    }
}
