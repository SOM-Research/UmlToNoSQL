package som.umltonosql.core.middleware.bean;

import som.umltonosql.core.middleware.datastore.store.JsonDatastore;

public abstract class JsonBean extends Bean {

    public JsonBean(JsonDatastore jsonDatastore) {
        super(jsonDatastore);
    }
}
