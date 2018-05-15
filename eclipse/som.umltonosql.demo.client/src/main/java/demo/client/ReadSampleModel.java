package demo.client;

import demo.business.Order;
import demo.core.DemoBootstrap;
import demo.core.DemoMiddleware;
import demo.economy.Bank;
import demo.economy.Company;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.ConsistencyException;

public class ReadSampleModel {

    public static void main(String[] args) {
        DemoBootstrap demoBootstrap = new DemoBootstrap();
        demoBootstrap.init();
        demoBootstrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();

        System.out.println("AllInstances(Order)");
        try {
            Iterable<Order> orders = middleware.getAllInstances(Order.class);
            for (Order o : orders) {
                System.out.println("Order.reference: " + o.getReference());
                // Returns null in the demo, see #15
                System.out.println("Order.client: " + o.getClient());
            }
        } catch(ConsistencyException e) {
            e.printStackTrace();
        }

        System.out.println("AllInstances(Client)");
        try {
            Iterable<Client> clients = middleware.getAllInstances(Client.class);
            for (Client c : clients) {
                System.out.println("Client.name: " + c.getName());
                System.out.println("Client.orders->first(): " + c.getOrders().iterator().next().getReference());
                System.out.println("Client.company: " + c.getCompany());
                System.out.println("Client.company.banks");
                for(Bank b : c.getCompany().getBanks()) {
                    System.out.println("\t" + b.getName());
                }
            }
        } catch(ConsistencyException e) {
            e.printStackTrace();
        }


    }
}
