package demo.client;

import demo.core.DemoBootstrap;
import demo.core.DemoMiddleware;

public class ReadSampleModel {

    public static void main(String[] args) {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();

        // We need an allInstances() implementation
    }
}
