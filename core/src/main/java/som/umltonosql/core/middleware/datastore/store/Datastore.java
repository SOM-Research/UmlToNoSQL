package som.umltonosql.core.middleware.datastore.store;

import som.umltonosql.core.middleware.bean.Bean;

public abstract class Datastore {

    protected String path;

    public Datastore(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public abstract Bean getElement(int id, Class<? extends Bean> clazz);

    public abstract void commit() throws Exception;

    public abstract String getDrillDriver();
}
