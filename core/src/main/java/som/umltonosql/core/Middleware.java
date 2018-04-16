package som.umltonosql.core;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.constraint.ConstraintManager;
import som.umltonosql.core.constraint.ConstraintResult;
import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.datastore.store.Datastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.core.exceptions.LifeCycleException;

import java.text.MessageFormat;
import java.util.List;

/**
 * An abstract class providing methods to create and retrieve elements from client applications.
 * <p>
 * This class should be used for any model element creation and retrieval, and ensures the consistency of the overall
 * model across the data stores.
 * <p>
 * {@link Middleware} instances also hold the list of {@link QueryProcessor}s that are associated to the generated
 * code, and can be used to compute native data store queries or generated
 * {@link som.umltonosql.core.constraint.Constraint}s.
 *
 * @see Datastore
 * @see QueryProcessor
 */
public abstract class Middleware {

    /**
     * Returns a {@link List} containing the {@link Datastore} instances associated to this {@link Middleware}.
     *
     * @return a {@link List} containing the {@link Datastore} instances associated to this {@link Middleware}
     */
    public abstract List<Datastore> getDatastores();

    /**
     * Returns a {@link List} containing the {@link QueryProcessor} instances associated to this {@link Middleware}.
     *
     * @return a {@link List} containing the {@link QueryProcessor} instances associated to this {@link Middleware}
     */
    public abstract List<QueryProcessor> getProcessors();

    /**
     * Returns the {@link QueryProcessor} that can handle the provided {@code queryClazz}.
     *
     * @param queryClazz the {@link Class} of the {@link Query} to retrieve the {@link QueryProcessor} of
     * @param <T>        the {@link Query} concrete subclass to retrieve the {@link QueryProcessor} of
     * @return the {@link QueryProcessor} that can handle the provided {@code queryClazz}
     */
    public final <T extends Query> QueryProcessor<T> getProcessorFor(Class<T> queryClazz) {
        for (QueryProcessor processor : getProcessors()) {
            if (processor.accepts(queryClazz)) {
                return processor;
            }
        }
        throw new RuntimeException(MessageFormat.format("Cannot find a processor for {0}", queryClazz.getSimpleName()));
    }

    /**
     * Creates a {@link Bean} subclass instance with the type {@code clazz}.
     * <p>
     * This method creates new records in the underlying databases and wrap them in {@link Bean} instances. To
     * retrieve an existing {@link Bean} use {@link Middleware#getElement(String, Class)}.
     *
     * @param clazz the {@link Class} of the {@link Bean} to create
     * @param <T>   the concrete type of {@link Bean} to create
     * @return a {@link Bean} subclass instance with the type {@code clazz}
     * @throws ConsistencyException if the provided {@code clazz} is not a valid {@link Bean} class
     * @see #getElement(String, Class)
     */
    public abstract <T extends Bean> T createElement(Class<T> clazz) throws ConsistencyException;

    /**
     * Returns the {@link Bean} subclass instance with the type {@code clazz} and the unique identifier {@code
     * id}.
     * <p>
     * This method retrieves an existing record and returns it as a {@link Bean} instance. To create and store new
     * {@link Bean}s use {@link Middleware#createElement(Class)}.
     *
     * @param id    the unique identifier of the {@link Bean} element to retrieve
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve
     * @param <T>   the concrete type of {@link Bean} to retrieve
     * @return the {@link Bean} subclass instance with the type {@code clazz} and the unique identifier {@code
     * id}.
     * @throws ConsistencyException if the provided {@code clazz} is not a valid {@link Bean} class
     * @see #createElement(Class)
     */
    public abstract <T extends Bean> T getElement(String id, Class<T> clazz) throws
            ConsistencyException;

    /**
     * Returns an {@link Iterable} containing all the {@link Bean} instances with the type {@code clazz}.
     * <p>
     * This method returns all the instances of an existing {@link Bean} type. To retrieve an element based on its
     * {@code id} use {@link Middleware#getElement(String, Class)}.
     *
     * @param clazz the {@link Class} of the {@link Bean} type to retrieve the instances of
     * @param <T>   the concrete type of {@link Bean} instances to retrieve
     * @return an {@link Iterable} containing all the {@link Bean} instances with the type {@code clazz}
     * @throws ConsistencyException if the provided {@code clazz} is not a valid {@link Bean} class
     * @see #getElement(String, Class)
     */
    public abstract <T extends Bean> Iterable<T> getAllInstances(Class<T> clazz) throws
            ConsistencyException;

    /**
     * Commits the pending modeling operations to the underlying database, if any.
     * <p>
     * This method is specific to each datastore implementation: if the datastore does not provide a transactional
     * context then every operation is directly performed on the database, and calling this method will not have any
     * effect. On the contrary, full transactional datastores require to explicitly call this method to apply the
     * modifications to the underlying data.
     *
     * @throws LifeCycleException if the underlying database is not able to commit the modifications
     */
    public abstract void commit() throws LifeCycleException;

    /**
     * Check all the {@link som.umltonosql.core.constraint.Constraint}s associated to the generated code.
     * <p>
     * This method provides a convenient way to check all the model constraints, and is equivalent to {@code
     * ConstraintManager.getInstance().checkConstraints()}.
     *
     * @return an {@link Iterable} of {@link ConstraintResult} representing the result of each computed
     * {@link som.umltonosql.core.constraint.Constraint}
     * @see ConstraintManager
     * @see som.umltonosql.core.constraint.Constraint
     * @see ConstraintResult
     */
    public Iterable<ConstraintResult> checkConstraints() {
        return ConstraintManager.getInstance().checkConstraints();
    }
}
