package constraints;

import java.util.Map;
import java.util.Set;

public class MaxSpanConstraint implements Constraint {
    protected Set<Activity> activities;
    protected int maxDelay;

    public MaxSpanConstraint(Set<Activity> activities, int maxDelay) {
        this.activities = activities;
        this.maxDelay = maxDelay;
    }

    @Override
    public Set<Activity> getActivities() {
        return this.activities;
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> activEtDates) {
        Integer datePremiereActiv = null;
        Integer dateFinActivFinitDerniere = null;

        if (this.getActivities().isEmpty()) {
            return true;
        } else {
            for (Activity activ : this.getActivities()) {
                int dateActiv = activEtDates.get(activ);
                int dateFinActiv = dateActiv + activ.getDuration();

                if ((datePremiereActiv == null) || (activEtDates.get(activ) <= datePremiereActiv)) {
                    datePremiereActiv = dateActiv;
                }

                if ((dateFinActivFinitDerniere == null) || (dateFinActiv > dateFinActivFinitDerniere)) {
                    dateFinActivFinitDerniere = dateFinActiv;
                }
            }
            return (dateFinActivFinitDerniere - datePremiereActiv) <= this.maxDelay;
        }
    }

    @Override
    public String toString() {
        return "MaxSpanConstraint:\n-maxDelay= " + this.maxDelay + "\n-Acts=" + this.getActivities();
    }
}
