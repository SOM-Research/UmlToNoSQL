package som.umltonosql.core.exceptions;

/**
 * An exception used to represent life-cycle errors.
 * <p>
 * This exception is typically thrown by a {@link som.umltonosql.core.LifeCycleManager} instance, and is used to wrap
 * datastore creation, connection, or system process issues.
 *
 * @see som.umltonosql.core.LifeCycleManager
 */
public class LifeCycleException extends UmlToNoSQLException {

    /**
     * Constructs a new {@link LifeCycleException}.
     */
    public LifeCycleException() {
        super();
    }

    /**
     * Constructs a new {@link LifeCycleException} with the provided {@code message}.
     *
     * @param message the exception's message
     */
    public LifeCycleException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link LifeCycleException} with the provided {@code message} and {@code cause}.
     *
     * @param message the exception's message
     * @param cause   the exception's cause
     */
    public LifeCycleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link LifeCycleException} with the provided {@code cause}.
     *
     * @param cause the exception's cause
     */
    public LifeCycleException(Throwable cause) {
        super(cause);
    }
}
