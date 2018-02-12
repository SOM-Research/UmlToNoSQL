package som.umltonosql.core.middleware.bean;

import som.umltonosql.core.middleware.datastore.store.Datastore;

public abstract class Bean {

    // UMLToNoSQL ID
    protected int id;

    private Datastore datastore;

    public Bean(Datastore datastore) {
        this.datastore = datastore;
    }

    public abstract int getId();
}
