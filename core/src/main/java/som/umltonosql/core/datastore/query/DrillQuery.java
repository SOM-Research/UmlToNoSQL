package som.umltonosql.core.datastore.query;

import som.umltonosql.core.bean.Bean;

public class DrillQuery<T extends Bean> extends Query<T> {

    public DrillQuery(String rawQuery, Class<T> resultType) {
        super(rawQuery, resultType);
    }
}
