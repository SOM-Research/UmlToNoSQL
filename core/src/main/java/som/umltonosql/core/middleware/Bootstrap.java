package som.umltonosql.core.middleware;

public abstract class Bootstrap {

    protected LifeCycleManager lcManager;

    protected Middleware middleware;

    public abstract void init();

    public LifeCycleManager getLcManager() {
        return lcManager;
    }

    public Middleware getMiddleware() {
        return middleware;
    }
}
