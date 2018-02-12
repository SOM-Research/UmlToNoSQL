package som.umltonosql.mongojson.example.app;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.middleware.exceptions.ConsistencyException;
import som.umltonosql.mongojson.example.core.generated.ExampleBootstrap;
import som.umltonosql.mongojson.example.core.generated.ExampleMiddleware;
import som.umltonosql.mongojson.example.json.beans.State;
import som.umltonosql.mongojson.example.mongodb.beans.Zip;

public class App {

    public static void main(String[] args) throws Exception {
        ExampleBootstrap bootstrap = new ExampleBootstrap();
        bootstrap.init();
        bootstrap.getLcManager().startServers();
        ExampleMiddleware runner = ExampleMiddleware.getInstance();
        State s = runner.getState(1);
        s.setPop(123);
        Zip z = runner.getZip(1008);
        State s2 = z.getState();
        System.out.println(s2.getName()); // MA
        System.out.println(z.getCity()); // Blandford
        runner.commit();
        try {
            runner.checkConstraints(); // AK
        } catch (ConsistencyException e) {
            Log.error("Consistency error {0}", e.getMessage());
        }
        bootstrap.getLcManager().stopServers();
    }
}
