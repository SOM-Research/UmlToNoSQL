package som.umltonosql.core.constraint;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.Query;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.datastore.query.processor.QueryProcessor;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.text.MessageFormat;

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

    public ConstraintResult checkConstraint() {
        QueryResult result = this.processor.query(query);
        try {
            return new ConstraintResult(this, result.getResults());
        } catch(ConsistencyException e) {
            throw new RuntimeException("An error occured when checking the constraints", e);
        }
    }
}
