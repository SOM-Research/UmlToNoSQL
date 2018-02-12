package som.umltonosql.core.middleware;

import java.util.HashMap;
import java.util.Map;

public class ConstraintManager {

    private Map<String, String> constraints;

    public static ConstraintManager getInstance() {
        return Holder.INSTANCE;
    }

    private ConstraintManager() {
        constraints = new HashMap<>();
    }

    public void addConstraint(String constraintId, String constraintBody) {
        this.constraints.put(constraintId, constraintBody);
    }

    public Map<String, String> getConstraints() {
        return constraints;
    }

    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final ConstraintManager INSTANCE = new ConstraintManager();
    }
}
