package som.umltonosql.demo.gremlin.bean;

import som.umltonosql.core.bean.GremlinBean;
import som.umltonosql.core.datastore.store.GremlinDatastore;

public class Bank extends GremlinBean {

    public Bank(String id, GremlinDatastore gremlinDatastore) {
        super(id, gremlinDatastore);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String newName) {
        setAttribute("name", newName);
    }
}