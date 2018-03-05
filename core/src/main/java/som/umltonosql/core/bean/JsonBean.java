package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.JsonDatastore;

public abstract class JsonBean extends Bean<JsonDatastore> {

    public JsonBean(JsonDatastore jsonDatastore) {
        super(jsonDatastore);
    }
}
