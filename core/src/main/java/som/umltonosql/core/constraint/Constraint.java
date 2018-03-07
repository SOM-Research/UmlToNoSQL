package som.umltonosql.core.constraint;

import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;

public class Constraint {

    private String name;

    private Query query;

    private QueryProcessor processor;

    public Constraint(String name, Query query, QueryProcessor processor) {
        this.name = name;
        this.query = query;
        this.processor = processor;
    }

    public String getName() {
        return this.name;
    }

    public QueryResult checkConstraint() {
        return this.processor.query(query);
    }
}
