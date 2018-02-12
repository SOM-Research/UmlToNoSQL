package som.umltonosql.core.middleware.exceptions;

public class ConsistencyException extends UmlToNoSQLException {

    public ConsistencyException() {
        super();
    }

    public ConsistencyException(String message) {
        super(message);
    }

    public ConsistencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsistencyException(Throwable cause) {
        super(cause);
    }

}
