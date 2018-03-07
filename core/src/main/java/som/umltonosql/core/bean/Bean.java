package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.Datastore;

public abstract class Bean<T extends Datastore> {

    // UMLToNoSQL ID
    protected String id;

    protected T datastore;

    public Bean(T datastore) {
        this.datastore = datastore;
    }

    public abstract String getId();

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(id);
        sb.append(" (");
        sb.append(this.getClass().getSimpleName());
        sb.append(')');
        return sb.toString();
    }
}
