package som.umltonosql.core.constraint;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.text.MessageFormat;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Represents the result of evaluating a {@link Constraint} over the stored model.
 * <p>
 * {@link ConstraintResult} status can be checked by calling {@link #isViolated()} that returns {@code true} if at
 * least one instance in the stored model violates the {@link Constraint}.
 * <p>
 * Note that all the instances violating the {@link Constraint} are retrieved from the underlying model, and can be
 * accessed using the {@link #getViolatingInstances()} method.
 * <p>
 * Instances of this class are typically constructed by {@link Constraint#checkConstraint()} and
 * {@link ConstraintManager#checkConstraints()}.
 *
 * @see Constraint
 * @see ConstraintManager
 */
public class ConstraintResult {

    /**
     * The evaluated {@link Constraint}.
     */
    private Constraint constraint;

    /**
     * An {@link Iterable} containing all the {@link Bean} elements that violate the {@link Constraint}.
     */
    private Iterable<Bean> violatingInstances;

    /**
     * Constructs a new {@link ConstraintResult} from the provided {@code constraint} and {@code violatingInstances}.
     *
     * @param constraint         the evaluated {@link Constraint}
     * @param violatingInstances an {@link Iterable} containing all elements that violate the provided {@code
     *                           constraint}
     */
    public ConstraintResult(Constraint constraint, Iterable<Bean> violatingInstances) {
        this.constraint = constraint;
        this.violatingInstances = violatingInstances;
    }

    /**
     * Returns {@code true} if the {@link Constraint} is violated, {@code false} otherwise.
     * <p>
     * Note that a {@link Constraint} is considered <i>violated</i> if at least one element does not satisfy it.
     *
     * @return {@code true} if the {@link Constraint} is violated, {@code false} otherwise
     */
    public boolean isViolated() {
        return nonNull(violatingInstances) && violatingInstances.iterator().hasNext();
    }

    /**
     * Returns an {@link Iterable} containing all the {@link Bean} elements that violate the {@link Constraint}.
     *
     * @return an {@link Iterable} containing all the {@link Bean} elements that violate the {@link Constraint}
     */
    public Iterable<Bean> getViolatingInstances() {
        return violatingInstances;
    }

    /**
     * Returns a textual representation of this {@link ConstraintResult}.
     *
     * @return a textual representation of this {@link ConstraintResult}
     * @see Bean#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (isViolated()) {
            sb.append(MessageFormat.format("Constraint {0} violated for instances ", this.constraint.getName()));
            sb.append('[');
            for (Bean bean : violatingInstances) {
                sb.append(bean.toString());
                sb.append(',');
            }
            if (sb.length() > 1) {
                sb.replace(sb.length() - 1, sb.length(), "]");
            } else {
                sb.append(']');
            }
        } else {
            sb.append(MessageFormat.format("Constraint {0} validated", this.constraint.getName()));
        }
        return sb.toString();
    }
}
