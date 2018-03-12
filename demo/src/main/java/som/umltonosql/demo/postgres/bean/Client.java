package som.umltonosql.demo.postgres.bean;

import som.umltonosql.core.bean.PostgresBean;
import som.umltonosql.core.datastore.store.PostgresDatastore;

public class Client extends PostgresBean {

    public Client(String id, PostgresDatastore datastore) {
        super(id, datastore);
    }

    public String getName() {
        return getValue("name");
    }

    public String getAddress() {
        return getValue("address");
    }
}
