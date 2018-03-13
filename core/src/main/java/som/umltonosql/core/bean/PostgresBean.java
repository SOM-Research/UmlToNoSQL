package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.PostgresDatastore;

import java.util.Collection;
import java.util.List;

public class PostgresBean extends Bean<PostgresDatastore> {

    public PostgresBean(String id, PostgresDatastore postgresDatastore) {
        super(postgresDatastore);
        this.id = id;
    }

    protected <T> T getSimpleValue(String columnName) {
        return (T) datastore.getSimpleValue(id, this.getClass(), columnName);
    }

    protected <T extends List> T getMultiValue(String columnName) {
        return (T) datastore.getMultiValue(id, this.getClass(), columnName);
    }

    protected void updateSimpleValue(String columnName, Object value) {
        datastore.updateSimpleValue(id, this.getClass(), columnName, value);
    }

    protected void addMultiValue(String columnName, Object value) {
        datastore.addMultiValue(id, this.getClass(), columnName, value);
    }

    @Override
    public String getId() {
        return id;
    }
}
