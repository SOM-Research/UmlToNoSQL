package som.umltonosql.core;

/**
 * An abstract class representing the bootstrap mechanism of generated code.
 * <p>
 * This class intializes the {@link Middleware} instance that should be manipulated by client code to create and
 * retrieve model elements. A typical bootstrapped client code would look like:
 * <pre>
 * {@code
 * DemoBootstrap demoBootstrap = new DemoBootstrap();
 * demoBootstrap.init();
 * demoBootstrap.getLcManager().startServers();
 * DemoMiddleware middleware = DemoMiddleware.getInstance();
 * }
 * </pre>
 */
public abstract class Bootstrap {

    /**
     * The {@link LifeCycleManager} used to manage the data store and query servers of the generated code.
     */
    protected LifeCycleManager lcManager;

    /**
     * The {@link Middleware} used to create and retrieve elements from the underlying data stores.
     */
    protected Middleware middleware;

    /**
     * Initializes the {@link LifeCycleManager} and {@link Middleware} of the generated code.
     * <p>
     * This method should be called before any model manipulation, to ensure that all the data stores and query
     * frameworks have been successfully started.
     */
    public abstract void init();

    /**
     * Returns the {@link LifeCycleManager} associated to this class.
     *
     * @return the {@link LifeCycleManager} associated to this class
     */
    public LifeCycleManager getLcManager() {
        return lcManager;
    }

    /**
     * Returns the {@link Middleware} associated to this class.
     *
     * @return the {@link Middleware} associated to this class
     */
    public Middleware getMiddleware() {
        return middleware;
    }
}
