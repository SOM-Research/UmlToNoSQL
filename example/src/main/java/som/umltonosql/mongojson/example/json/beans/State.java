package som.umltonosql.mongojson.example.json.beans;

import som.umltonosql.core.middleware.bean.JsonBean;
import som.umltonosql.core.middleware.datastore.store.JsonDatastore;

// Should be generated
public class State extends JsonBean {

    public State(JsonDatastore jsonDatastore) {
        super(jsonDatastore);
    }

    public int getId() {
        return getState_id();
    }

    private int state_id;

    private String name;

    private String fullname;

    private long pop;

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPop() {
        return pop;
    }

    public void setPop(long pop) {
        this.pop = pop;
    }

}
