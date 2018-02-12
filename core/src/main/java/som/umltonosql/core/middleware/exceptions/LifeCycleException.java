package som.umltonosql.core.middleware.exceptions;

public class LifeCycleException extends UmlToNoSQLException {

    public LifeCycleException() {
        super();
    }

    public LifeCycleException(String message) {
        super(message);
    }

    public LifeCycleException(String message, Throwable cause) {
        super(message, cause);
    }

    public LifeCycleException(Throwable cause) {
        super(cause);
    }
}
