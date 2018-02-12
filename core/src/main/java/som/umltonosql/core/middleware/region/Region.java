package som.umltonosql.core.middleware.region;

import som.umltonosql.core.middleware.datastore.store.Datastore;

public class Region {

    private String id;


    private Datastore datastore;

    public Region(String id, Datastore datastore) {
        this.id = id;
        this.datastore = datastore;
    }

    public String getId() {
        return id;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
