package som.umltonosql.core.datastore.store;

import fr.inria.atlanmod.commons.log.Log;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.LifeCycleException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

public class GremlinDatastore extends Datastore {

    private Graph graph;

    private static String GREMLIN_GRAPH_OPTION = "gremlin.graph";

    private static String GREMLIN_NEO4J_DIRECTORY_OPTION = "gremlin.neo4j.directory";

    private static String NEO4J_GRAPH_OPTION = "org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph";

    public GremlinDatastore(String dbName) {
        super(dbName);
        File dbFile = new File(dbName);
        Map<String, String> dbConfiguration = new HashMap<>();
        dbConfiguration.put(GREMLIN_GRAPH_OPTION, NEO4J_GRAPH_OPTION);
        dbConfiguration.put(GREMLIN_NEO4J_DIRECTORY_OPTION, dbFile.getAbsolutePath());
        try {
            graph = GraphFactory.open(dbConfiguration);
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Cannot open a GremlinDatastore at the provided location: {0}",
                    dbName);
            Log.error(errorMsg);
            throw new LifeCycleException(errorMsg, e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the internal {@link Graph} instance used to manipulate the database
     */
    @Override
    public Graph getDatabase() {
        return graph;
    }

    public <T extends Bean> T createElement(Class<T> clazz) {
        ObjectId newObjectId = new ObjectId();
        Vertex vertex = graph.addVertex(getVertexLabelFromBeanClass(clazz));
        vertex.property("_id", newObjectId.toString());
        T bean = null;
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, GremlinDatastore.class);
            bean = (T) constructor.newInstance(newObjectId.toString(), this);
        } catch (NoSuchMethodException e) {
            Log.error("Canot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
        return bean;
    }

    @Override
    public <T extends Bean> T getElement(String id, Class<T> clazz) {
        Vertex vertex = null;
        try {
            vertex = graph.traversal().V().hasLabel(getVertexLabelFromBeanClass(clazz)).has("_id", id).next();
        } catch(NoSuchElementException e) {
            /*
             * The database does not contain the element.
             */
            return null;
        }
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, GremlinDatastore.class);
            return (T) constructor.newInstance(id, this);
        } catch(NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e1) {
            Log.error("Cannot invoke the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e1);
        }
    }

    @Override
    public <T extends Bean> Iterable<T> getAllInstances(Class<T> clazz) {
        List<T> beans = new ArrayList<>();
        graph.traversal().V().hasLabel(getVertexLabelFromBeanClass(clazz)).toStream()
                .forEach(v -> beans.add(getElement(v.value("_id"), clazz)));
        return beans;
    }

    @Override
    public void commit() {
        // Nothing to do? (check the documentation)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDrillDriver() {
        // TODO check #30
        return null;
    }

    /**
     * An utility method that returns the {@link Vertex} label associated to the provided {@code clazz}.
     *
     * @param clazz the {@link Class} of the {@link Bean} element to retrieve the {@link Vertex} label of
     * @return the {@link Vertex} label associated to the provided {@code clazz}
     */
    private String getVertexLabelFromBeanClass(Class<? extends Bean> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }
}
