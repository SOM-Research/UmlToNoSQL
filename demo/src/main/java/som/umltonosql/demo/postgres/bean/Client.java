package som.umltonosql.demo.postgres.bean;

import som.umltonosql.core.bean.PostgresBean;
import som.umltonosql.core.datastore.store.PostgresDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.mongodb.beans.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client extends PostgresBean {

    public Client(String id, PostgresDatastore datastore) {
        super(id, datastore);
    }

    public String getName() {
        return getSimpleValue("name");
    }

    public void setName(String newName) {
        updateSimpleValue("name", newName);
    }

    public String getAddress() {
        return getSimpleValue("address");
    }

    public void setAddress(String newAddress) {
        updateSimpleValue("address", newAddress);
    }

    public Iterable<Order> getOrders() throws ConsistencyException {
        List<String> ordersIds = getMultiValue("orders");
        if(!ordersIds.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for(String id : ordersIds) {
                orders.add(DemoMiddleware.getInstance().getOrder(id));
            }
            return Collections.unmodifiableList(orders);
        }
        // cardinality *, no need to check this
        return Collections.emptyList();
    }

    public void addOrder(Order newOrder) {
        addMultiValue("orders", newOrder.getId());
    }
}
