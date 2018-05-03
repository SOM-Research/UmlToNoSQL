package som.umltonosql.core.datastore.store;

import com.google.common.collect.Iterables;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.bean.GremlinBean;
import som.umltonosql.test.beans.GremlinElement;

import java.io.File;
import java.text.MessageFormat;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class GremlinDatastoreTest {

    private GremlinDatastore datastore;

    private static String DATASTORE_LOCATION = "/tmp/test-gremlin-datastore";

    @Before
    public void setUp() {
        this.datastore = new GremlinDatastore(DATASTORE_LOCATION);
    }

    @After
    public void tearDown() throws Exception {
        File dbFile = new File(DATASTORE_LOCATION);
        if (nonNull(datastore) && dbFile.exists()) {
            try {
                this.datastore.getDatabase().close();
            } finally {
                FileUtils.forceDelete(new File(DATASTORE_LOCATION));
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void createDatastoreNullDbName() {
        this.datastore = new GremlinDatastore(null);
    }

    @Test
    public void createDatastoreValidDbName() {
        // Do nothing, done in setUp
    }

    @Test
    public void createElement() {
        GremlinBean element = this.datastore.createElement(GremlinBean.class);
        assert nonNull(element.getId()) : "The created element's ID is null";
    }

    @Test
    public void getExistingElement() {
        GremlinBean element = this.datastore.createElement(GremlinBean.class);
        GremlinBean retrievedElement = this.datastore.getElement(element.getId(), GremlinBean.class);
        assert nonNull(retrievedElement) : "The retrieved element is null";
        assert retrievedElement.getId().equals(element.getId()) : MessageFormat.format("The retrieved element's ID is" +
                " not valid (expected: {0}, found {1})", element.getId(), retrievedElement.getId());
    }

    @Test
    public void getNotExistingElement() {
        GremlinBean retrievedElement = this.datastore.getElement("abc", GremlinBean.class);
        assert isNull(retrievedElement) : MessageFormat.format("The returned element is not null ({0})",
                retrievedElement);
    }

    @Test
    public void getAllInstancesNoInstances() {
        Iterable<GremlinBean> instances = this.datastore.getAllInstances(GremlinBean.class);
        assert !instances.iterator().hasNext() : "The returned Iterable is not empty";
    }

    @Test
    public void getAllInstancesOnlyOneType() {
        this.datastore.createElement(GremlinBean.class);
        this.datastore.createElement(GremlinBean.class);
        Iterable<GremlinBean> instances = this.datastore.getAllInstances(GremlinBean.class);
        assert instances.iterator().hasNext() : "The returned Iterable is empty";
        assert Iterables.size(instances) == 2 : MessageFormat.format("The returned Iterable is not valid (expected " +
                "size: {0}, found: {1})", 2, Iterables.size(instances));
    }

    @Test
    public void getAllInstancesMultiTypes() {
        this.datastore.createElement(GremlinBean.class);
        this.datastore.createElement(GremlinElement.class);
        Iterable<GremlinBean> instances = this.datastore.getAllInstances(GremlinBean.class);
        assert instances.iterator().hasNext() : "The returned Iterable is empty";
        assert Iterables.size(instances) == 1 : MessageFormat.format("The returned Iterable is not valid (expected " +
                "size: {0}, found: {1})", 1, Iterables.size(instances));
    }
}
