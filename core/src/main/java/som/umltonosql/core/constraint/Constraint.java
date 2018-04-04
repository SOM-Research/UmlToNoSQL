package som.umltonosql.core.constraint;

import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.exceptions.ConsistencyException;

/**
 * A named constraint the can be processed on top of the stored model.
 */
public class Constraint {

    /**
     * The name of the constraint.
     */
    private String name;

    /**
     * The internal {@link Query} to evaluate to compute the constraint.
     */
    private Query query;

    /**
     * The {@link QueryProcessor} to use to compute the {@link Query} representing the constraint.
     *
     * @see Query
     */
    private QueryProcessor processor;

    /**
     * Constructs a new {@link Constraint} with the given {@code name}, {@code query}, and {@code processor}.
     *
     * @param name      the name of the constraint
     * @param query     the {@link Query} to evaluate to compute the constraint
     * @param processor the {@link QueryProcessor} to use to compute the {@link Query} representing the constraint
     * @see Query
     * @see QueryProcessor
     */
    public Constraint(String name, Query query, QueryProcessor processor) {
        this.name = name;
        this.query = query;
        this.processor = processor;
    }

    /**
     * Returns the name of the constraint.
     *
     * @return the name of the constraint
     */
    public String getName() {
        return this.name;
    }

    /**
     * Evaluate the constraint over the stored model
     *
     * @return a {@link ConstraintResult} instance
     */
    public ConstraintResult checkConstraint() {
        QueryResult result = this.processor.query(query);
        try {
            return new ConstraintResult(this, result.getResults());
        } catch (ConsistencyException e) {
            throw new RuntimeException("An error occured when checking the constraints", e);
        }
    }
}
