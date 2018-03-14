package som.umltonosql.core.datastore.query.processor;

import fr.inria.atlanmod.commons.log.Log;
import oadd.org.apache.drill.exec.util.JsonStringHashMap;
import oadd.org.apache.drill.exec.util.Text;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.datastore.query.DrillQuery;
import som.umltonosql.core.datastore.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrillQueryProcessor extends QueryProcessor<DrillQuery> {

    private static String DRILL_CONNECTION = "jdbc:drill:drillbit=localhost";

    private Connection connection;

    public DrillQueryProcessor(Middleware middleware) {
        super(middleware);
        try {
            connection = DriverManager.getConnection(DRILL_CONNECTION);
            /*
             * Needed to cast MongoDB IDs as Strings.
             * TODO: this may be an issue if it is execute on top of Drill installations that does not have a MongoDB
             * connector
             */
            connection.createStatement().execute("alter session set store.mongo.bson.record.reader = false");
        } catch(SQLException e) {
            throw new RuntimeException("Drill Driver error", e);
        }
    }

    @Override
    Iterable<String> doQuery(DrillQuery query) {
        List<String> idResults = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rSet = statement.executeQuery(query.getRawQuery());
            while (rSet.next()) {
                Object id = rSet.getObject(1);
                if(id instanceof String) {
                    idResults.add((String) id);
                } else {
                    // need a elseif
                    // why do we have JsonStringHashMap?
                    JsonStringHashMap r = (JsonStringHashMap)id;
                    idResults.add(((Text) r.get("$oid")).toString());
                }
            }
            statement.close();
        } catch(SQLException e) {
            throw new RuntimeException("Drill Driver error", e);
        }
        return idResults;
    }

    @Override
    public boolean accepts(Class<? extends Query> queryClazz) {
        if(queryClazz.equals(DrillQuery.class)) {
            return true;
        }
        return false;
    }
}
