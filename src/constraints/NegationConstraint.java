package constraints;

import java.util.Map;
import java.util.Set;

public class NegationConstraint implements Constraint {
    Constraint contrainte;

    public NegationConstraint(Constraint contrainte) {
        this.contrainte = contrainte;
    }

    @Override
    public Set<Activity> getActivities() {
        return this.contrainte.getActivities();
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> activDates) {
        return !this.contrainte.isSatisfied(activDates);
    }
}
