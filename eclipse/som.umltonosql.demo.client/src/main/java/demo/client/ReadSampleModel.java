package demo.client;

import demo.business.Order;
import demo.core.DemoBootstrap;
import demo.core.DemoMiddleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.ConsistencyException;

public class ReadSampleModel {

    public static void main(String[] args) {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();

        try {
            Iterable<Order> orders = middleware.getAllInstances(Order.class);
            for (Order o : orders) {
                System.out.println(o.getReference());
            }
        } catch(ConsistencyException e) {
            e.printStackTrace();
        }

        try {
            Iterable<Client> clients = middleware.getAllInstances(Client.class);
            for (Client c : clients) {
                System.out.println(c.getName());
                System.out.println(c.getOrders().iterator().next().getReference());
            }
        } catch(ConsistencyException e) {
            e.printStackTrace();
        }
    }
}
