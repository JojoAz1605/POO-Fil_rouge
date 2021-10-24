package constraints;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BinaryConstraint implements Constraint {
    protected Activity firstActivity;
    protected Activity secondActivity;

    public BinaryConstraint(Activity firstActiv, Activity secondActiv) {
        this.firstActivity = firstActiv;
        this.secondActivity = secondActiv;
    }

    public Activity getFirst() {
        return firstActivity;
    }

    public Activity getSecond() {
        return secondActivity;
    }

    public abstract boolean isSatisfied(int date1, int date2);

    @Override
    public Set<Activity> getActivities() {
        Set<Activity> lesActivites = new HashSet<>();
        lesActivites.add(this.firstActivity);
        lesActivites.add(this.secondActivity);

        return lesActivites;
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> activEtDates) {
        if (activEtDates.containsKey(this.getFirst()) && activEtDates.containsKey(this.secondActivity)) {
            int date1 = activEtDates.get(this.getFirst());
            int date2 = activEtDates.get(this.getSecond());
            return this.isSatisfied(date1, date2);
        } else {
            return false;
        }
    }
}
