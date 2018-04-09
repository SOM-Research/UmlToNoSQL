package som.umltonosql.core.exceptions;

/**
 * An exception used to represent model-to-datastore consistency errors.
 * <p>
 * This exception is typically thrown by a {@link som.umltonosql.core.constraint.ConstraintManager} instance, or
 * {@link som.umltonosql.core.bean.Bean} instances when navigating cross-datastore associations.
 *
 * @see som.umltonosql.core.constraint.ConstraintManager
 */
public class ConsistencyException extends UmlToNoSQLException {

    /**
     * Constructs a new {@link ConsistencyException}.
     */
    public ConsistencyException() {
        super();
    }

    /**
     * Constructs a new {@link ConsistencyException} with the provided {@code message}.
     *
     * @param message the exception's message
     */
    public ConsistencyException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link ConsistencyException} with the provided {@code message} and {@code cause}.
     *
     * @param message the exception's message
     * @param cause   the exception's cause
     */
    public ConsistencyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link ConsistencyException} with the provided {@code cause}.
     *
     * @param cause the exception's cause
     */
    public ConsistencyException(Throwable cause) {
        super(cause);
    }

}
