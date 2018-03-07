package som.umltonosql.core.constraint;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstraintManager {

    private Map<String, Constraint> constraints;

    public static ConstraintManager getInstance() {
        return Holder.INSTANCE;
    }

    private ConstraintManager() {
        constraints = new HashMap<>();
    }

    public void addConstraint(Constraint constraint) {
        this.constraints.put(constraint.getName(), constraint);
    }

    public Map<String, Constraint> getConstraints() {
        return constraints;
    }

    public Iterable<ConstraintResult> checkConstraints() throws ConsistencyException {
        List<ConstraintResult> constraintResults = new ArrayList<>();
        for(String constraintName : constraints.keySet()) {
            Constraint constraint = constraints.get(constraintName);
            constraintResults.add(constraint.checkConstraint());
        }
        return constraintResults;
    }

    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final ConstraintManager INSTANCE = new ConstraintManager();
    }
}
