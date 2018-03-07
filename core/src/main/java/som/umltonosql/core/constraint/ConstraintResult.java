package som.umltonosql.core.constraint;

import som.umltonosql.core.bean.Bean;
import som.umltonosql.core.exceptions.ConsistencyException;

import java.text.MessageFormat;
import java.util.List;

import static java.util.Objects.nonNull;

public class ConstraintResult {

    private Constraint constraint;

    private Iterable<Bean> violatingInstances;

    public ConstraintResult(Constraint constraint, Iterable<Bean> violatingInstances) {
        this.constraint = constraint;
        this.violatingInstances = violatingInstances;
    }

    public boolean isViolated() {
        return nonNull(violatingInstances) && violatingInstances.iterator().hasNext();
    }

    public Iterable<Bean> getViolatingInstances() {
        return violatingInstances;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(isViolated()) {
            sb.append(MessageFormat.format("Constraint {0} violated for instances ", this.constraint.getName()));
            sb.append('[');
            for (Bean bean : violatingInstances) {
                sb.append(bean.toString());
                sb.append(',');
            }
            if(sb.length() > 1) {
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
