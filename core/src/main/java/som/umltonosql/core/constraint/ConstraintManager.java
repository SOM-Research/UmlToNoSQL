package som.umltonosql.core.constraint;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.datastore.query.QueryResult;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.text.MessageFormat;
import java.util.HashMap;
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

    public void checkConstraints() throws ConsistencyException {
        for(String constraintName : constraints.keySet()) {
            Constraint constraint = constraints.get(constraintName);
            QueryResult result = constraint.checkConstraint();
            if(!result.isEmpty()) {
                StringBuffer sb = new StringBuffer();
                sb.append('[');
                try {
                    for (Bean bean : result.getResults()) {
                        sb.append(bean.getId());
                        sb.append(',');
                    }
                } catch(ConsistencyException e) {
                    throw new RuntimeException("An error occured when checking the constraints", e);
                }
                if(sb.length() > 1) {
                    sb.replace(sb.length() - 1, sb.length(), "]");
                } else {
                    sb.append(']');
                }
                throw new ConsistencyException(MessageFormat.format("Constraint {0} violated for {1}",
                        constraintName, sb.toString()));
            }
        }
    }

    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final ConstraintManager INSTANCE = new ConstraintManager();
    }
}
