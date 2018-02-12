package som.umltonosql.mongojson.example.core.generated;

import som.umltonosql.core.region.ModelRegionManager;
import som.umltonosql.core.region.Region;
import som.umltonosql.core.Bootstrap;
import som.umltonosql.core.ConstraintManager;
import som.umltonosql.core.LifeCycleManager;
import som.umltonosql.core.datastore.handler.DrillHandler;
import som.umltonosql.core.datastore.handler.MongoHandler;
import som.umltonosql.core.datastore.store.JsonDatastore;
import som.umltonosql.core.datastore.store.MongoDatastore;

import java.util.Arrays;

public class ExampleBootstrap extends Bootstrap {

    public void init() {
        lcManager = new LifeCycleManager(Arrays.asList(
                new MongoHandler("\"C:\\Program Files\\MongoDB\\Server\\3.0\\bin\\"),
                new DrillHandler("C:\\Users\\gwend\\Documents\\bin\\apache-drill-1.12.0\\bin"))
        );
        // Creates the Datastores
        JsonDatastore stateDatastore = new JsonDatastore
                ("/Users/gwend/Documents/Databases/resources/json/state_pop_updates.json");
        MongoDatastore zipDatastore = new MongoDatastore("example.tuned_zips");

        // Creates the Regions
        Region stateRegion = new Region("states", stateDatastore);
        Region zipRegion = new Region("zips", zipDatastore);
        ModelRegionManager regionManager = new ModelRegionManager();
        regionManager.addRegion(stateRegion);
        regionManager.addRegion(zipRegion);

        middleware = new ExampleMiddleware(stateDatastore, zipDatastore);

        ConstraintManager.getInstance().getConstraints().put("ConsistentPop",
                "select zip.city " +
                        "from " + regionManager.getRegion("zips").getDatastore().getDrillDriver() + "." +
                        regionManager.getRegion
                        ("zips").getDatastore().getPath() + " as zip join " +
                        regionManager.getRegion("states").getDatastore().getDrillDriver() + ".`" + regionManager
                        .getRegion("states").getDatastore().getPath() + "` as state on " +
                        "cast(zip.state_id as INT) = cast(state.state_id as INT) " +
                        "and zip.pop > state.pop"

        );
        ConstraintManager.getInstance().getConstraints().put("ConsistentPopState",
                "select state.name, sum(zip.pop), state.pop " +
                        "from " + regionManager.getRegion("zips").getDatastore().getDrillDriver() + "." +
                        regionManager.getRegion("zips").getDatastore().getPath() + "as zip join " +
                        regionManager.getRegion("states").getDatastore().getDrillDriver() + ".`" + regionManager
                        .getRegion("states").getDatastore().getPath() + "` as state on" +
                        "cast(zip.state_id as INT) = cast(state.state_id as INT)" +
                        "group by (state.name, state.pop) " +
                        "having " +
                        "sum(zip.pop) > state.pop"
        );
    }
}
