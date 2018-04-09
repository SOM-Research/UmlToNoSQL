package som.umltonosql.core.exceptions;

/**
 * An abstract class representing an UmlToNoSQL-specific exception.
 */
public abstract class UmlToNoSQLException extends Exception {

    /**
     * Constructs a new {@link UmlToNoSQLException}.
     */
    public UmlToNoSQLException() {
        super();
    }

    /**
     * Constructs a new {@link UmlToNoSQLException} with the provided {@code message}.
     *
     * @param message the exception's message
     */
    public UmlToNoSQLException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link UmlToNoSQLException} with the provided {@code message} and {@code cause}.
     *
     * @param message the exception's message
     * @param cause   the exception's cause
     */
    public UmlToNoSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link UmlToNoSQLException} with the provided {@code cause}
     *
     * @param cause the exception's cause
     */
    public UmlToNoSQLException(Throwable cause) {
        super(cause);
    }

}
