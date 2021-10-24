package solvers;

import constraints.Activity;
import constraints.Constraint;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Verifier {
    Set<Constraint> constraints;

    public Verifier(Set<Constraint> constraints) {
        this.constraints = constraints;
    }

    public Set<Constraint> unsatisfied(Map<Activity, Integer> activEtDates) {
        HashSet<Constraint> pasSatisfait = new HashSet<>();

        for (Constraint contrainte : this.constraints) {
            for (Activity activity : activEtDates.keySet()) {
                if ((contrainte.getActivities().contains(activity)) && !(contrainte.isSatisfied(activEtDates))) {
                    pasSatisfait.add(contrainte);
                }
            }
        }
        return pasSatisfait;
    }
}
