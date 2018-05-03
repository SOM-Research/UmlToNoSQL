package som.umltonosql.test.beans;

import som.umltonosql.core.bean.GremlinBean;
import som.umltonosql.core.datastore.store.GremlinDatastore;

/**
 * A dummy {@link GremlinBean} subclass used to check inheritance and polymorphism-related methods.
 */
public class GremlinElement extends GremlinBean {

    /**
     * Constructs a new {@link GremlinElement} with the provided {@code id} and {@code gremlinDatastore}.
     *
     * @param id               the unique identifier of the database record identifying the bean
     * @param gremlinDatastore the {@link GremlinDatastore} used to access and manipulate the underlying database
     */
    public GremlinElement(String id, GremlinDatastore gremlinDatastore) {
        super(id, gremlinDatastore);
    }
}
