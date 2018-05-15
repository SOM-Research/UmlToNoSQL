package demo.client;

import demo.business.Order;
import demo.core.DemoBootstrap;
import demo.core.DemoMiddleware;
import demo.economy.Bank;
import demo.economy.Company;

import java.util.Date;

public class CreateSampleModel {

    public static void main(String[] args) {
        DemoBootstrap demoBootsrap = new DemoBootstrap();
        demoBootsrap.init();
        demoBootsrap.getLcManager().startServers();
        DemoMiddleware middleware = DemoMiddleware.getInstance();

        // Create a valid order
        Order validOrder = middleware.createOrder();
        validOrder.setReference("A valid order");
        validOrder.setShipmentDate(new Date(System.currentTimeMillis() - (3600 * 1000)));
        validOrder.setDeliveryDate(new Date());
        validOrder.setPaid(true);

        // Create the banks
        Bank bank1 = middleware.createBank();
        bank1.setName("Bank1");

        Bank bank2 = middleware.createBank();
        bank2.setName("Bank2");

        // Create a company
        Company company = middleware.createCompany();
        company.setName("My Company");
        company.addBanks(bank1);
        company.addBanks(bank2);

        // Create a valid client
        Client client = middleware.createClient();
        client.setName("John Doe");
        client.setAddress("Barcelona");
        client.addOrder(validOrder);
        client.setCompany(company);

        middleware.commit();

        demoBootsrap.getLcManager().stopServers();

        System.out.println("Done");
    }

}
