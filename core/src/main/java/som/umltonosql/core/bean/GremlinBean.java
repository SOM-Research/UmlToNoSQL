package som.umltonosql.core.bean;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import som.umltonosql.core.datastore.store.GremlinDatastore;

/**
 * A Gremlin-based {@link Bean} implementation.
 * <p>
 * This class delegates {@link Bean}-level operations to a {@link GremlinDatastore} that managed the underlying
 * database.
 */
public class GremlinBean extends Bean<GremlinDatastore> {

    public GremlinBean(String id, GremlinDatastore gremlinDatastore) {
        super(gremlinDatastore);
        this.id = id;
    }

    protected void setAttribute(String field, Object value) {
        datastore.setValue(id, this.getClass(), field, value);
    }

    protected <T> T getAttribute(String field) {
        return (T) datastore.getValue(id, this.getClass(), field);
    }

    protected Iterable<Vertex> getAssociation(String field, Class<? extends Bean> fieldClazz) {
        return datastore.getAssociation(id, this.getClass(), field, fieldClazz);
    }

    protected void addAssociation(String field, Bean value) {
        datastore.addAssociation(id, this.getClass(), field, value);
    }
}
