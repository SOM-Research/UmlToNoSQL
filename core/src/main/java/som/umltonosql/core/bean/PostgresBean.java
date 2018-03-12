package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.PostgresDatastore;

public class PostgresBean extends Bean<PostgresDatastore> {

    public PostgresBean(String id, PostgresDatastore postgresDatastore) {
        super(postgresDatastore);
        this.id = id;
    }

    protected <T> T getValue(String columnName) {
        return (T) datastore.getValue(id, this.getClass(), columnName);
    }

    @Override
    public String getId() {
        return id;
    }
}
