package constraints;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DisjunctionConstraint implements Constraint {
    Constraint contrainte1, contrainte2;

    public DisjunctionConstraint(Constraint contrainte1, Constraint contrainte2) {
        this.contrainte1 = contrainte1;
        this.contrainte2 = contrainte2;
    }

    @Override
    public Set<Activity> getActivities() {
        Set<Activity> lesActiv = new HashSet<>();
        lesActiv.addAll(this.contrainte1.getActivities());
        lesActiv.addAll(this.contrainte2.getActivities());
        return lesActiv;
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> activDates) {
        return this.contrainte1.isSatisfied(activDates) || this.contrainte2.isSatisfied(activDates);
    }
}
