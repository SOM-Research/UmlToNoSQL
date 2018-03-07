package som.umltonosql.core.datastore.query;

import som.umltonosql.core.Middleware;
import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {

    // Iterable ID
    private Iterable<String> rawResult;

    private Class<? extends Bean> resultType;

    private long executionTime;

    private Middleware middleware;

    public QueryResult(Iterable<String> rawResult, Class<? extends Bean> resultType, long executionTime, Middleware
            middleware) {
        this.rawResult = rawResult;
        this.resultType = resultType;
        this.executionTime = executionTime;
        this.middleware = middleware;
    }

    public Iterable<Bean> getResults() throws ConsistencyException {
        List<Bean> reifiedResults = new ArrayList<>();
        for (String id : rawResult) {
            reifiedResults.add(middleware.getElement(id, resultType));
        }
        return reifiedResults;
    }

    public boolean isEmpty() {
        return !rawResult.iterator().hasNext();
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
