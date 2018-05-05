package som.umltonosql.demo.gremlin.bean;

import som.umltonosql.core.bean.GremlinBean;
import som.umltonosql.core.datastore.store.GremlinDatastore;
import som.umltonosql.core.exceptions.ConsistencyException;
import som.umltonosql.demo.core.generated.DemoMiddleware;
import som.umltonosql.demo.postgres.bean.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company extends GremlinBean {

    public Company(String id, GremlinDatastore gremlinDatastore) {
        super(id, gremlinDatastore);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String newName) {
        setAttribute("name", newName);
    }

    public Client getCompanyClient() {
        Iterable<String> companyClientIds = getAssociation("companyClient", Client.class);
        if(companyClientIds.iterator().hasNext()) {
            return DemoMiddleware.getInstance().getClient(companyClientIds.iterator().next());
        }
        return null;
    }

    public void setCompanyClient(Client newCompanyClient) {
        if(newCompanyClient == null) {
            throw new ConsistencyException("Cannot set null newCompanyClient, the association cardinality is 1");
        }
        addAssociation("companyClient", newCompanyClient);
    }

    public Iterable<Bank> getBanks() {
        Iterable<String> banksIds = getAssociation("banks", Bank.class);
        if(banksIds.iterator().hasNext()) {
            List<Bank> banks = new ArrayList<>();
            for(String banksId : banksIds) {
                banks.add(DemoMiddleware.getInstance().getBank(banksId));
            }
            return Collections.unmodifiableList(banks);
        }
        return Collections.emptyList();
    }

    public void addBank(Bank newBank) {
        addAssociation("bank", newBank);
    }
}
