package som.umltonosql.core.datastore.query.processor;

import fr.inria.atlanmod.commons.log.Log;
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
                ObjectId oId = new ObjectId((byte[])id);
                idResults.add(oId.toString());
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
