package som.umltonosql.core.bean;

import som.umltonosql.core.datastore.store.Datastore;

/**
 * An abstract class representing a mapped conceptual element.
 * <p>
 * A {@link Bean} instance is associated to a {@link Datastore}, that performs the {@link Bean}-level operations on
 * the underlying datastore.
 * <p>
 * Note that {@link Bean}s hold a String representation of their ID (UmlTo[No]SQLID), that can be reused in concrete
 * subclasses to build datastore-specific identifiers.
 *
 * @param <T> the {@link Datastore} type used to delegate {@link Bean}-level operations
 */
public abstract class Bean<T extends Datastore> {

    /**
     * A {@link String} representation of the {@link Bean}'s unique identifier.
     */
    protected String id;

    /**
     * The {@link Datastore} to delegate the {@link Bean}'s operations to.
     */
    protected T datastore;

    /**
     * Constructs a new {@link Bean} with the provided {@code datastore}.
     *
     * @param datastore the {@link Datastore} instance to delegate the {@link Bean}'s operations to
     */
    public Bean(T datastore) {
        this.datastore = datastore;
    }

    /**
     * Returns the unique identifier of the {@link Bean}.
     *
     * @return the unique identifier of the {@link Bean}
     */
    public String getId() {
        return id;
    }

    /**
     * Builds a {@link String} representation of the {@link Bean}.
     * <p>
     * The default behavior of this method returns a String following the pattern "UmlTo[No]SQLID (Bean Type)",
     * additional information can be provided by suclasses.
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(id);
        sb.append(" (");
        sb.append(this.getClass().getSimpleName());
        sb.append(')');
        return sb.toString();
    }
}
