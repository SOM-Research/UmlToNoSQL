package som.umltonosql.mongojson.example.mongodb.beans;

import som.umltonosql.core.middleware.bean.MongoBean;
import som.umltonosql.core.middleware.datastore.store.MongoDatastore;
import som.umltonosql.mongojson.example.core.generated.ExampleMiddleware;
import som.umltonosql.mongojson.example.json.beans.State;

import java.io.IOException;

public class Zip extends MongoBean {

    public Zip(int id, MongoDatastore mongoDatastore) {
        super(id, mongoDatastore);
    }

    public String getCity() {
        return document.getString("city");
    }

    public State getState() throws IOException {
        Integer stateId = document.getInteger("state_id");
        return ExampleMiddleware.getInstance().getState(stateId);
    }

}
