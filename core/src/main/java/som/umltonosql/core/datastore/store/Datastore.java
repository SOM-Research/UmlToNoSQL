package som.umltonosql.core.datastore.store;

import som.umltonosql.core.bean.Bean;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract class representing a datastore used to persist and access a model region.
 * <p>
 * {@link Datastore}s are used as an abstraction layer on top of concrete database implementations, and hide their
 * diversity under a unified modeling layer. Created and retrieved elements are returned as {@link Bean} instances,
 * that wrap database records representing an UML class and provide utility methods to access, update, and delete
 * attributes and navigate associations.
 *
 * @see Bean
 */
public abstract class Datastore {

    /**
     * The path of the {@link Datastore}.
     * <p>
     * This attribute typically contains the raw URL of the database storing the model region, or a local path if the
     * database is embedded.
     */
    protected String path;

    /**
     * Constructs a new {@link Datastore} with the provided {@code path}.
     *
     * @param path the raw path of the {@link Datastore}
     */
    public Datastore(String path) {
        checkNotNull(path, "Cannot construct a Datastore from a null path");
        this.path = path;
    }

    /**
     * Returns the path of the {@link Datastore}.
     *
     * @return the path of the {@link Datastore}
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Returns a raw {@link Object} representing the underlying database.
     * <p>
     * This method is typically used by the end-user when manipulating core concepts of a specific database
     * implementation that are not covered by the <i>UmlToDatabase</i> translations.
     * <p>
     * <b>Note:</b> modifying the internal structure of the database may create consistency issues when creating,
     * accessing, or deleting {@link Bean}s.
     *
     * @return a raw {@link Object} representing the underlying database
     */
    public abstract Object getDatabase();

    /**
     * Creates and stores a new {@code clazz} {@link Bean}.
     * <p>
     * This method is part of the UmlToNoSQL generic API, that can be used in generated code to create {@link Bean}'s
     * subclasses instances transparently. Note that the generated code embeds a
     * {@link som.umltonosql.core.Middleware} subclass that should be used by end users to create
     * statically-typed {@link Bean}s.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to create
     * @param <T>   the concrete type of the {@link Bean} instance to create
     * @return the created {@link Bean}
     * @see som.umltonosql.core.Middleware
     */
    public abstract <T extends Bean> T createElement(Class<T> clazz);

    /**
     * Retrieves the {@link Bean} element with the type {@code clazz} and the unique identifier {@code id}.
     * <p>
     * This method is part of the UmlToNoSQL generic API, that can be used in generated code to retrieve
     * {@link Bean}'s subclasses instances transparently. Note that the generated code embeds a
     * {@link som.umltonosql.core.Middleware} subclass that should be used by end users to retrieve statically-typed
     * {@link Bean}s.
     *
     * @param id    the unique identifier of the {@link Bean} element to retrieve
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve
     * @param <T>   the concrete type of the {@link Bean} instance to retrieve
     * @return the retrieved {@link Bean}
     * @see som.umltonosql.core.Middleware
     */
    public abstract <T extends Bean> T getElement(String id, Class<T> clazz);

    /**
     * Returns an {@link Iterable} containing all the {@link Bean} instances with the type {@code clazz}.
     * <p>
     * This method is part of the UmlToNoSQL generic API, that can be used in generated code to retrieve
     * {@link Bean}'s subclasses instances transparently. Note that the generated code embeds a
     * {@link som.umltonosql.core.Middleware} subclass that should be used by end users to retrieve statically-typed
     * {@link Bean}s.
     *
     * @param clazz the {@link Class} of the {@link Bean} type to retrieve the instances of
     * @param <T>   the concrete type of the {@link Bean} instances to retrieve
     * @return an {@link Iterable} containing all the {@link Bean} instances with the type {@code clazz}
     * @see som.umltonosql.core.Middleware
     */
    public abstract <T extends Bean> Iterable<T> getAllInstances(Class<T> clazz);

    /**
     * Commits the pending modifications to the underlying database, if any.
     * <p>
     * This method is specific to each datastore implementation: if the datastore does not provide a transactional
     * context then every operation is directly performed on the database, and calling this method will not have any
     * effect. On the contrary, full transactional datastores require to explicitly call this method to apply the
     * modifications to the underlying data.
     *
     * @throws Exception if the underlying database is not able to commit the modifications
     */
    public abstract void commit() throws Exception;

    /**
     * Returns a textual representation of the Drill driver that should be used to access this {@link Datastore}
     * through the Drill framework.
     * <p>
     * TODO: this method should not be part of the {@link Datastore} API. Using Drill as a query solution is not the
     * {@link Datastore}'s responsability.
     *
     * @return a textual representaiton of the Drill driver that should be used to access this {@link Datastore}
     * through the Drill framework
     */
    public abstract String getDrillDriver();
}
