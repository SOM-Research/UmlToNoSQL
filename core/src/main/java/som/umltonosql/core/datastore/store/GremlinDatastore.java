package som.umltonosql.core.datastore.store;

import fr.inria.atlanmod.commons.log.Log;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.bson.types.ObjectId;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.bean.GremlinBean;
import som.umltonosql.core.exceptions.ConsistencyException;
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

    @Override
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
            vertex = getVertex(id, clazz);
        } catch (NoSuchElementException e) {
            /*
             * The database does not contain a Vertex with the provided id, there is no Bean instance to construct.
             */
            return null;
        }
        try {
            Constructor<?> constructor = clazz.getConstructor(String.class, GremlinDatastore.class);
            return (T) constructor.newInstance(id, this);
        } catch (NoSuchMethodException e) {
            Log.error("Cannot find the constructor for the provided bean {0}", clazz.getName());
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e1) {
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

    public void setValue(String id, Class<? extends Bean> clazz, String field, Object value) {
        Vertex v = null;
        try {
            v = getVertex(id, clazz);
        } catch(NoSuchElementException e) {
            throw new ConsistencyException(MessageFormat.format("Cannot retrieve the vertex with the provided ID: " +
                    "{0}", id), e);
        }
        v.property(field, value);
    }

    public Object getValue(String id, Class<? extends Bean> clazz, String field) {
        Vertex v = null;
        try {
            v = getVertex(id, clazz);
        } catch (NoSuchElementException e) {
            throw new ConsistencyException(MessageFormat.format("Cannot retrieve the vertex with the provided ID: " +
                    "{0}", id), e);
        }
        return v.property(field);
    }

    public Iterable<String> getAssociation(String id, Class<? extends Bean> clazz, String field, Class<? extends
            Bean> fieldClazz) {
        // TODO handle exception
        Vertex v = null;
        try {
            getVertex(id, clazz);
        } catch (NoSuchElementException e) {
            throw new ConsistencyException(MessageFormat.format("Cannot retrieve the vertex with the provided ID: " +
                    "{0}", id), e);
        }
        if (GremlinBean.class.isAssignableFrom(fieldClazz)) {
            /*
             * The value is stored in this datastore, we can retrieve it by navigating the outgoing edges.
             */
            List<String> idList = new ArrayList<>();
            Iterator<Vertex> vertices = v.vertices(Direction.IN, field);
            while(vertices.hasNext()) {
                idList.add(v.value("_id"));
            }
            return idList;
        } else {
            /*
             * The value is stored in another datastore, we can retrieve its IDs in the vertex's properties.
             */
            return () -> v.value(field);
        }
    }

    public void addAssociation(String id, Class<? extends Bean> clazz, String field, Bean value) {
        Vertex fromVertex = null;
        try {
            fromVertex = getVertex(id, clazz);
        } catch(NoSuchElementException e) {
            throw new ConsistencyException(MessageFormat.format("Cannot retrieve the input vertex of the " +
                    "association {0} with the provided ID: {1}", field, id),e);
        }
        if (value instanceof GremlinBean) {
            /*
             * The value to add is stored in this datastore, we can retrieve the corresponding vertex and create an
             * edge representing the association.
             */
            Vertex toVertex = null;
            try {
                toVertex = getVertex(value.getId(), value.getClass());
            } catch(NoSuchElementException e) {
                throw new ConsistencyException(MessageFormat.format("Cannot retrieve the output vertex of the " +
                        "association {0} with the provided ID: {0}", field, id),e);
            }
            fromVertex.addEdge(field, toVertex);
        } else {
            /*
             * The value to add is not stored in this datastore, we add its ID to the vertex's properties.
             */
            VertexProperty<List<String>> toIdsProperty = fromVertex.property(field);
            if(toIdsProperty.isPresent()) {
                toIdsProperty.value().add(value.getId());
            } else {
                List<String> toIds = new ArrayList<>();
                toIds.add(value.getId());
                fromVertex.property(field, toIds);
            }
        }
    }

    /**
     * Returns the database {@link Vertex} representing an instance of {@code clazz} with the provided {@code id}.
     *
     * @param id    the unique identifier of the {@link Vertex} to retrieve
     * @param clazz the {@link Class} of the {@link GremlinBean} to retrieve an instance of
     * @return the database {@link Vertex} representing an instance of {@code clazz} with the provided {@code id}
     * @throws NoSuchElementException if the database does not contain a {@link Vertex} with the provided {@code id}
     */
    private Vertex getVertex(String id, Class<? extends Bean> clazz) throws NoSuchElementException {
        return graph.traversal().V().hasLabel(getVertexLabelFromBeanClass(clazz)).has("_id", id).next();
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
