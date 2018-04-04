package som.umltonosql.core.constraint;

import som.umltonosql.core.exceptions.ConsistencyException;

import java.util.*;

/**
 * A singleton manager for {@link Constraint}s.
 * <p>
 * This class is used in generated code to initialize and register the system's constraints at a single, shared
 * location.
 *
 * @see Constraint
 */
public class ConstraintManager {

    /**
     * The internal collection of constraints, sorted by their name.
     */
    private Map<String, Constraint> constraints;

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    public static ConstraintManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Constructs an empty {@link ConstraintManager}.
     * <p>
     * <b>Note:</b> this constructor is private, use {@link #getInstance()} to get a valid {@link ConstraintManager}
     * instance.
     */
    private ConstraintManager() {
        constraints = new HashMap<>();
    }

    /**
     * Adds the provided {@code constraint} to this constraint manager.
     *
     * @param constraint the {@link Constraint} to add
     */
    public void addConstraint(Constraint constraint) {
        this.constraints.put(constraint.getName(), constraint);
    }

    /**
     * Returns an immutable {@link Map} containing the {@link Constraint}s stored in this {@link ConstraintManager}.
     *
     * @return an immutable {@link Map} containing the {@link Constraint}s stored in this {@link ConstraintManager}
     */
    public Map<String, Constraint> getConstraints() {
        return Collections.unmodifiableMap(constraints);
    }

    /**
     * Evaluates all the manager's {@link Constraint}s over the stored model.
     * <p>
     * This method gathers all the valid and invalid constraints, and returns an {@link Iterable} of
     * {@link ConstraintResult} representing the result of each computed {@link Constraint}.
     *
     * @return an {@link Iterable} of {@link ConstraintResult} representing the result of each computed
     * {@link Constraint}
     */
    public Iterable<ConstraintResult> checkConstraints() {
        List<ConstraintResult> constraintResults = new ArrayList<>();
        for (String constraintName : constraints.keySet()) {
            Constraint constraint = constraints.get(constraintName);
            constraintResults.add(constraint.checkConstraint());
        }
        return constraintResults;
    }

    /**
     * A static holder for this singleton class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final ConstraintManager INSTANCE = new ConstraintManager();
    }
}
