package som.umltonosql.core.datastore.store;

import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.bean.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;

public class PostgresDatastore extends Datastore {

    private Connection connection;

    // used to check that the id exists in the table (should be optimized)
    private static String GET_ELEMENT_TEMPLATE = "select id from {0} where id = ''{1}''";

    private static String SELECT_COLUMN_TEMPLATE = "select {0} from {1} where id = ''{2}''";

    // {2} only works with string for now
    private static String UPDATE_COLUMN_TEMPLATE = "update {0} set {1} = ''{2}'' where id = ''{3}'';";

    public PostgresDatastore(String path) {
        super(path);
        // can we provide the user and password in the path?
        try {
            this.connection = DriverManager.getConnection(path, "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot start the PostgresDatastore", e);
        }
    }

    @Override
    public Connection getDatabase() {
        return connection;
    }

    @Override
    public Bean createElement(Class<? extends Bean> clazz) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Bean getElement(String id, Class<? extends Bean> clazz) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rSet = statement.executeQuery(MessageFormat.format(GET_ELEMENT_TEMPLATE, getTableNameFromBeanClass
                    (clazz), id));
            String objectId = null;
            if (rSet.next()) {
                // The element exists in the database
                objectId = rSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot get the instance of {0} with the provided id " +
                    "{1}", clazz.getSimpleName(), id), e);
        }
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, PostgresDatastore.class);
            return (Bean) constructor.newInstance(id, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    private String getTableNameFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    private String formatColumnName(String columnName) {
        return columnName.toLowerCase();
    }

    public void updateValue(String id, Class<? extends Bean> clazz, String columnName, Object value) {
        try {
            Statement statement = connection.createStatement();
            boolean updated = statement.execute(MessageFormat.format(UPDATE_COLUMN_TEMPLATE, getTableNameFromBeanClass
                    (clazz), columnName, value, id));
            Log.info("Updated {0}", updated);
        } catch(SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot udpate the provided field ({0})", columnName), e);
        }
    }

    public Object getValue(String id, Class<? extends Bean> clazz, String columnName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rSet = statement.executeQuery(MessageFormat.format(SELECT_COLUMN_TEMPLATE, formatColumnName
                    (columnName), getTableNameFromBeanClass(clazz), id));
            if (rSet.next()) {
                Object value = rSet.getObject(1);
                return value;
            }
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot get the value of the column {0} for Bean {1}",
                    columnName, clazz.getSimpleName()), e);
        }
        return null;
    }

    @Override
    public void commit() {

    }

    @Override
    public String getDrillDriver() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
