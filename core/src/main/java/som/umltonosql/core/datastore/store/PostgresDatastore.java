package som.umltonosql.core.datastore.store;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.bean.PostgresBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A PostgreSQL {@link Datastore} implementation that maps modeling-level operation to PostgreSQL operations through
 * the JDBC interface.
 * <p>
 * A {@link PostgresDatastore} is associated to a unique PostgreSQL database, and manages its <i>tables</i> and
 * <i>records</i>. Note that models containing multiple regions mapped to PostgreSQL are managed by different
 * instances of this class, that can be retrieved in the application's {@link som.umltonosql.core.Middleware} instance.
 * <p>
 * This class provides utility method that can be called by {@link PostgresBean} instances
 * to retrieve single and multi-valued features, and update the value of their features.
 *
 * @see PostgresBean
 * @see Middleware#getDatastores()
 */
public class PostgresDatastore extends Datastore {

    /**
     * The JDBC {@link Connection} used to manipulate the underlying PostgreSQL database.
     */
    private Connection connection;

    /**
     * The SQL query template used to retrieve an existing record.
     * <p>
     * <b>Note:</b> this query only returns the identifier of the element. To retrieve a specific column value
     * associated to the element, see {@link PostgresDatastore#SELECT_COLUMN_TEMPLATE} and
     * {@link PostgresDatastore#SELECT_MULTI_VALUE}.
     *
     * @see #getElement(String, Class)
     * @see #SELECT_COLUMN_TEMPLATE
     * @see #SELECT_MULTI_VALUE
     */
    private static String GET_ELEMENT_TEMPLATE = "select id from {0} where id = ''{1}''";

    /**
     * The SQL query template used to retrieve the value of a specific column value of an existing record.
     * <p>
     * <b>Note:</b> this query only returns values of single-valued {@link Bean} feature. To retrieve a
     * multi-valued feature, see {@link PostgresDatastore#SELECT_MULTI_VALUE}.
     *
     * @see #getSimpleValue(String, Class, String)
     * @see #SELECT_MULTI_VALUE
     */
    private static String SELECT_COLUMN_TEMPLATE = "select {0} from {1} where id = ''{2}''";

    /**
     * The SQL query template used to retrieve multi-valued feature values of an existing {@link Bean}.
     * <p>
     * Multi-valued feature are stored in specific tables representing the links between elements. Note that the
     * construction of the query itself is delegated to {@link PostgresDatastore#getMultiValue(String, Class, String)}.
     * <b>Note:</b> this query only returns the values of multi-valued {@link Bean} features. To retrieve a
     * single-valued feature, see {@link PostgresDatastore#SELECT_COLUMN_TEMPLATE}.
     *
     * @see #getMultiValue(String, Class, String)
     * @see #SELECT_COLUMN_TEMPLATE
     */
    private static String SELECT_MULTI_VALUE = "select * from {0} where {1} = ''{2}'';";

    /**
     * The SQL query template used to insert a new record in an existing table.
     *
     * @see #createElement(Class)
     */
    private static String INSERT_ELEMENT_TEMPLATE = "insert into {0} values (''{1}'');";

    /**
     * The SQL query template used to insert a new multi-valued feature value associated to an existing record.
     *
     * @see #addMultiValue(String, Class, String, Object)
     */
    private static String INSERT_MULTI_VALUE = "insert into {0} values (''{1}'', ''{2}'');";


    /**
     * The SQL query template used to update a single-valued feature associated to an existing record.
     * <p>
     * TODO: this template only accepts String values as its {2} argument, this should be fixed to support at least
     * all the primitive types.
     *
     * @see #updateSimpleValue(String, Class, String, Object)
     */
    private static String UPDATE_COLUMN_TEMPLATE = "update {0} set {1} = ''{2}'' where id = ''{3}'';";

    /**
     * Constructs a new {@link PostgresDatastore} managing the PostgreSQL database located at the given {@code path}.
     * <p>
     * This constructor manages both local and remote databases, and supports path expressed as {@code
     * "jdbc:postgresql://<host>:<port>/<database_name>"}.
     *
     * @param path the location of the PostgreSQL database to access
     */
    public PostgresDatastore(String path) {
        super(path);
        // can we provide the user and password in the path?
        try {
            this.connection = DriverManager.getConnection(path, "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException("Cannot start the PostgresDatastore", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the internal JDBC {@link Connection} used to manipulate the database
     */
    @Override
    public Connection getDatabase() {
        return connection;
    }

    /**
     * Creates and stores a new {@code clazz} {@link PostgresBean} in the managed PostgreSQL
     * database.
     * <p>
     * This method is part of the UmlToNoSQL generic API, that can be used in generated code to create {@link Bean}'s
     * subclasses instances transparently. Note that the generated code embeds a
     * {@link som.umltonosql.core.Middleware} subclass that should be used by end users to create
     * statically-typed {@link Bean}s.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to create
     * @return the created {@link PostgresBean}
     * @see Middleware
     */
    @Override
    public PostgresBean createElement(Class<? extends Bean> clazz) {
        ObjectId newObjectId = new ObjectId();
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(INSERT_ELEMENT_TEMPLATE, getTableNameFromBeanClass(clazz),
                    newObjectId.toString());
            Log.trace("PostgresDatastore: createElement({0})\n\t{1}", clazz.getName(), sqlStatement);
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot create the database record for the new instance " +
                    "of {0} (id = {1})", clazz.getSimpleName(), newObjectId.toString()), e);
        }
        Bean bean = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, PostgresDatastore.class);
            return (PostgresBean) constructor.newInstance(newObjectId.toString(), this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bean getElement(String id, Class<? extends Bean> clazz) {
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(GET_ELEMENT_TEMPLATE, getTableNameFromBeanClass
                    (clazz), id);
            Log.trace("PostgresDatastore: getElement({0}, {1})\n\t{2}", id, clazz.getName(), sqlStatement);
            ResultSet rSet = statement.executeQuery(sqlStatement);
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

    /**
     * An utility method that returns the name of the table associated to the provided {@code clazz}.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve the table of
     * @return the name of the table associated to the provided {@code clazz}
     */
    private String getTableNameFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    /**
     * An utility method that returns the name of the table holding the values of the multi-valued association {@code
     * associationName}.
     *
     * @param clazz           the {@link Class} of the {@link Bean} to retrieve the table of
     * @param associationName the name of the multi-valued association to retrieve the table of
     * @return the name of the table holding the values of the multi-valued association {@code associationName}
     */
    private String getMultiTableName(Class<? extends Bean> clazz, String associationName) {
        return getTableNameFromBeanClass(clazz) + "_" + associationName;
    }

    /**
     * An utility method that formats the input {@code columnName} to fit the internal PostgreSQL representation.
     *
     * @param columnName the name of the column to format
     * @return the formatted {@code columnName}
     */
    private String formatColumnName(String columnName) {
        return columnName.toLowerCase();
    }

    /**
     * An utility method that returns the identifier column name of the provided {@code typeName} in tables representing
     * multi-valued associations.
     *
     * @param typeName the name of the {@link Bean}'s identifier field to retrieve
     * @return the identifier column name of the provided {@code typeName} in tables representing multi-valued
     * associations
     */
    private String multiValueId(String typeName) {
        return typeName + "_id";
    }

    /**
     * An utility method used in {@link PostgresBean} to update a single-valued feature of an existing
     * record.
     * <p>
     * To update a multi-valued feature of an existing record, see
     * {@link PostgresDatastore#addMultiValue(String, Class, String, Object)}.
     * <p>
     * <b>Note:</b> calling this method directly may result in inconsistent {@link PostgresBean} instances. The
     * operation performed in this class are not reflected at the {@link Bean}-level. To update an existing
     * {@link Bean} see its generated API instead.
     *
     * @param id          the identifier of the record to update
     * @param clazz       the {@link Class} of the {@link PostgresBean} element to update
     * @param featureName the name of the record's column to update
     * @param value       the new value to set for the provided {@code featureName}
     * @see #addMultiValue(String, Class, String, Object)
     */
    public void updateSimpleValue(String id, Class<? extends Bean> clazz, String featureName, Object value) {
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(UPDATE_COLUMN_TEMPLATE, getTableNameFromBeanClass
                    (clazz), featureName, value, id);
            Log.trace("PostgresDatastore: updateSimpleValue({0}, {1}, {2}, {3})\n\t{4}", id, clazz.getName(),
                    featureName, value.toString(), sqlStatement);
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot udpate the provided field ({0})", featureName),
                    e);
        }
    }

    /**
     * An utility method used in {@link PostgresBean} to add a value to a multi-valueed feature of an existing record.
     * <p>
     * To update a single-valued feature of an existing record, see
     * {@link PostgresDatastore#updateSimpleValue(String, Class, String, Object)}.
     * <p>
     * <b>Note:</b> calling this method directly may result in inconsistent {@link PostgresBean} instances. The
     * operation performed in this class are not reflected at the {@link Bean}-level. To update an existing
     * {@link Bean} see its generated API instead.
     *
     * @param id          the identifier of the record to update
     * @param clazz       the {@link Class} of the {@link PostgresBean} element to update
     * @param featureName the name of the multi-valued feature to add a value to
     * @param value       the new value to add to the {@code featureName} feature
     * @see #updateSimpleValue(String, Class, String, Object)
     */
    public void addMultiValue(String id, Class<? extends Bean> clazz, String featureName, Object value) {
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(INSERT_MULTI_VALUE, getMultiTableName(clazz, featureName), id,
                    value);
            Log.trace("PostgresDatastore: addMultiValue({0}, {1}, {2}, {3})\n\t{4}", id, clazz.getName(),
                    featureName, value.toString(), sqlStatement);
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot update the multi valued field {0} from bean {1}",
                    featureName, clazz.getSimpleName()), e);
        }
    }

    /**
     * An utility method used in {@link PostgresBean} to retrieve a single-valued feature value.
     *
     * @param id          the identifier of the {@link PostgresBean} element to retrieve the value from
     * @param clazz       the {@link Class} of the {@link PostgresBean} element to retrieve the value from
     * @param featureName the name of the single-valued feature to retrieve
     * @return the retrieved value
     * @see PostgresBean
     */
    public Object getSimpleValue(String id, Class<? extends Bean> clazz, String featureName) {
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(SELECT_COLUMN_TEMPLATE, formatColumnName
                    (featureName), getTableNameFromBeanClass(clazz), id);
            Log.trace("PostgresDatastore: getSimpleValue({0}, {1}, {2})\n\t{3}", id, clazz.getName(), featureName,
                    sqlStatement);
            ResultSet rSet = statement.executeQuery(sqlStatement);
            if (rSet.next()) {
                Object value = rSet.getObject(1);
                return value;
            }
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot get the value of the column {0} for Bean {1}",
                    featureName, clazz.getSimpleName()), e);
        }
        return null;
    }

    /**
     * An utility method used in {@link PostgresBean} to retrieve a multi-valued feature value.
     *
     * @param id          the identifier of the {@link PostgresBean} element to retrieve the value from
     * @param clazz       the {@link Class} of the {@link PostgresBean} element to retrieve the value from
     * @param featureName the name of the multi-valued feature to retrieve
     * @return a {@link List} containing the retrieved values
     * @see PostgresBean
     */
    public List getMultiValue(String id, Class<? extends Bean> clazz, String featureName) {
        try {
            Statement statement = connection.createStatement();
            String sqlStatement = MessageFormat.format(SELECT_MULTI_VALUE,
                    getMultiTableName(clazz, featureName), multiValueId(getTableNameFromBeanClass(clazz)), id);
            Log.trace("PostgresDatastore: getMultiValue({0}, {1}, {2})\n\t{3}", id, clazz.getName(), featureName,
                    sqlStatement);
            ResultSet rSet = statement.executeQuery(sqlStatement);
            List<Object> result = new ArrayList<>();
            while (rSet.next()) {
                Object o = rSet.getObject(2);
                result.add(o);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(MessageFormat.format("Cannot access the multi-valued field {0} of Bean {1}",
                    featureName, clazz.getSimpleName()), e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Note:</b> the current implementation of the {@link PostgresDatastore} does not provide a transactional
     * environment, and commits each performed operation. As a result, calling this method will not have any impact on
     * the stored data.
     */
    @Override
    public void commit() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDrillDriver() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
