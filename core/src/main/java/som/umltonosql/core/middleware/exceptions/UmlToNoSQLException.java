package som.umltonosql.core.middleware.exceptions;

// base class for UmlToNoSQL exceptions (allows abstract catch blocks)
public class UmlToNoSQLException extends Exception {

    public UmlToNoSQLException() {
        super();
    }

    public UmlToNoSQLException(String message) {
        super(message);
    }

    public UmlToNoSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public UmlToNoSQLException(Throwable cause) {
        super(cause);
    }

}
