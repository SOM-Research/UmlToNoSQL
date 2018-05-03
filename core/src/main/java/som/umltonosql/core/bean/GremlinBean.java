package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.GremlinDatastore;

public class GremlinBean extends Bean<GremlinDatastore> {

    public GremlinBean(String id, GremlinDatastore gremlinDatastore) {
        super(gremlinDatastore);
        this.id = id;
    }
}
