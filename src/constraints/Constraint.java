package constraints;

import java.util.Map;
import java.util.Set;

public interface Constraint {
    Set<Activity> getActivities();

    boolean isSatisfied(Map<Activity, Integer> activDates);
}
