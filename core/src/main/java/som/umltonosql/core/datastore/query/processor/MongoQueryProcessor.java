package som.umltonosql.core.datastore.query.processor;

import fr.inria.atlanmod.commons.log.Log;
import org.bson.Document;
import org.bson.types.ObjectId;
import som.umltonosql.core.Middleware;
import som.umltonosql.core.datastore.query.MongoQuery;
import som.umltonosql.core.datastore.store.MongoDatastore;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MongoQueryProcessor extends  QueryProcessor<MongoQuery> {

    private MongoDatastore mongoDatastore;

    private static String NATIVE_QUERY_TEMPLATE = "function() '{'" +
            "var cursor = {0};" +
            "return cursor.toArray();" +
            "'}'";

    public MongoQueryProcessor(Middleware middleware, MongoDatastore datastore) {
        super(middleware);
        this.mongoDatastore = datastore;
    }

    @Override
    Iterable<Long> doquery(MongoQuery query) {
        System.out.println(query.getRawQuery());
        Document result = mongoDatastore.getDatabase().runCommand(new Document("eval", MessageFormat.format
                (NATIVE_QUERY_TEMPLATE, query.getRawQuery())));
        List<Document> results = (List<Document>) result.get("retval");
        List<Long> idResults = new ArrayList<>();
        for(Document res : results) {
            ObjectId id = res.getObjectId("_id");
            idResults.add(id.getDate().getTime());
        }
        return idResults;
    }
}
