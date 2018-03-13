package som.umltonosql.core.datastore.store;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PostgresDatastore extends Datastore {

    private Connection connection;

    // used to check that the id exists in the table (should be optimized)
    private static String GET_ELEMENT_TEMPLATE = "select id from {0} where id = ''{1}''";

    private static String SELECT_COLUMN_TEMPLATE = "select {0} from {1} where id = ''{2}''";

    private static String INSERT_ELEMENT_TEMPLATE = "insert into {0} values (''{1}'');";

    private static String INSERT_MULTI_VALUE = "insert into {0} values (''{1}'', ''{2}'');";

    private static String SELECT_MULTI_VALUE = "select * from {0} where {1} = ''{2}'';";

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
        ObjectId newObjectId = new ObjectId();
        try {
            Statement statement = connection.createStatement();
            statement.execute(MessageFormat.format(INSERT_ELEMENT_TEMPLATE, getTableNameFromBeanClass(clazz),
                    newObjectId.toString()));
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot create the database record for the new instance " +
                    "of {0} (id = {1})", clazz.getSimpleName(), newObjectId.toString()), e);
        }
        Bean bean = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, PostgresDatastore.class);
            return (Bean) constructor.newInstance(newObjectId.toString(), this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
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
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    private String getTableNameFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    private String getMultiTableName(Class<? extends Bean> clazz, String columnName) {
        return getTableNameFromBeanClass(clazz) + "_" + columnName;
    }

    private String formatColumnName(String columnName) {
        return columnName.toLowerCase();
    }

    private String multiValueId(String typeName) {
        return typeName + "_id";
    }

    public void updateSimpleValue(String id, Class<? extends Bean> clazz, String columnName, Object value) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(MessageFormat.format(UPDATE_COLUMN_TEMPLATE, getTableNameFromBeanClass
                    (clazz), columnName, value, id));
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot udpate the provided field ({0})", columnName), e);
        }
    }

    public void addMultiValue(String id, Class<? extends Bean> clazz, String columnName, Object value) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(MessageFormat.format(INSERT_MULTI_VALUE, getMultiTableName(clazz, columnName), id,
                    value));
        } catch(SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot update the multi valued field {0} from bean {1}",
                    columnName, clazz.getSimpleName()), e);
        }
    }

    public Object getSimpleValue(String id, Class<? extends Bean> clazz, String columnName) {
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

    public List getMultiValue(String id, Class<? extends Bean> clazz, String columnName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rSet = statement.executeQuery(MessageFormat.format(SELECT_MULTI_VALUE,
                    getMultiTableName(clazz, columnName), multiValueId(getTableNameFromBeanClass(clazz)), id));
            List<Object> result = new ArrayList<>();
            while (rSet.next()) {
                Object o = rSet.getObject(2);
                result.add(o);
            }
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot access the multi-valued field {0} of Bean {1}",
                    columnName, clazz.getSimpleName()), e);
        }
    }

    @Override
    public void commit() {

    }

    @Override
    public String getDrillDriver() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
