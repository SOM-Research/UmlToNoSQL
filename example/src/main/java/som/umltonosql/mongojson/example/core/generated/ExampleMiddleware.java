package som.umltonosql.mongojson.example.core.generated;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.middleware.ConstraintManager;
import som.umltonosql.core.middleware.Middleware;
import som.umltonosql.core.middleware.datastore.store.JsonDatastore;
import som.umltonosql.core.middleware.datastore.store.MongoDatastore;
import som.umltonosql.core.middleware.exceptions.ConsistencyException;
import som.umltonosql.core.middleware.exceptions.LifeCycleException;
import som.umltonosql.mongojson.example.json.beans.State;
import som.umltonosql.mongojson.example.mongodb.beans.Zip;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;

import static java.util.Objects.nonNull;

public class ExampleMiddleware extends Middleware {

    private JsonDatastore jsonDatastore;

    private MongoDatastore mongoDatastore;

    private static ExampleMiddleware INSTANCE;

    public static ExampleMiddleware getInstance() {
        return INSTANCE;
    }

    public ExampleMiddleware(JsonDatastore jsonDatastore, MongoDatastore mongoDatastore) {
        this.jsonDatastore = jsonDatastore;
        this.mongoDatastore = mongoDatastore;
        if (nonNull(INSTANCE)) {
            Log.warn("Multiple instances of ExampleMiddleware have been created");
        }
        INSTANCE = this;
    }

    public State getState(int state_id) throws IOException {
        return (State) jsonDatastore.getElement(state_id, State.class);
    }

    public Zip getZip(int zipId) {
        return (Zip) mongoDatastore.getElement(zipId, Zip.class);
    }

    @Override
    public void commit() throws LifeCycleException {
        try {
            jsonDatastore.commit();
            mongoDatastore.commit();
        } catch (Exception e) {
            throw new LifeCycleException("An error occured during the committing operations", e);
        }
    }

    public void checkConstraints() throws ConsistencyException, RuntimeException {
        // Drill
        try {
            Connection connection = DriverManager.getConnection("jdbc:drill:drillbit=localhost");
            Statement s = connection.createStatement();
            for (String constraint : ConstraintManager.getInstance().getConstraints().keySet()) {
                ResultSet rSet = s.executeQuery(ConstraintManager.getInstance().getConstraints().get(constraint));
                if (rSet.next() == true) {
                    String element = rSet.getString(1);
                    Log.warn("Constraint {0} violated for element {1}", constraint, element);
                    Log.warn("\tRaw data:");
                    printRow(rSet);
                    throw new ConsistencyException(MessageFormat.format("Constraint {0} violated for element {1}",
                            constraint, element));
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Cannot check the constraints, see attached exception", e);
        }
    }

    private void printRow(ResultSet rSet) throws SQLException {
        StringBuffer buffer = new StringBuffer();
        int columnCount = rSet.getMetaData().getColumnCount();
        for (int i = 1; i < columnCount + 1; i++) {
            if (i > 1) {
                buffer.append(" | ");
            }
            buffer.append(rSet.getString(i));
        }
        buffer.append("\n");
        Log.warn(buffer.toString());
    }
}
