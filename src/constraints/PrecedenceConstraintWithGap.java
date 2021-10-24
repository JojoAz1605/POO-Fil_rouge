package constraints;

import java.util.Map;

public class PrecedenceConstraintWithGap extends PrecedenceConstraint implements Constraint {
    int maxDelay;
    int minDelay;

    public PrecedenceConstraintWithGap(Activity firstActivity, Activity secondActivity, int minDelay, int maxDelay) {
        super(firstActivity, secondActivity);
        this.maxDelay = maxDelay;
        this.minDelay = minDelay;
    }

    @Override
    public boolean isSatisfied(int date1, int date2) {
        int firstActivityDuration = this.firstActivity.getDuration();
        int dateFinActiv1 = date1 + firstActivityDuration;
        if (!(dateFinActiv1 <= date2) || (this.minDelay > this.maxDelay)) {
            return false;
        } else {
            boolean minDelaySatisfied;
            boolean maxDelaySatisfied;
            if (this.minDelay == 0) {
                minDelaySatisfied = true;
            } else {
                minDelaySatisfied = date2 >= dateFinActiv1 + this.minDelay;
            }
            if (this.maxDelay == 0) {
                maxDelaySatisfied = dateFinActiv1 == date2;
            } else {
                maxDelaySatisfied = date2 <= dateFinActiv1 + this.maxDelay;
            }
            return minDelaySatisfied && maxDelaySatisfied;
        }
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> activEtDates) {
        if (activEtDates.containsKey(this.getFirst()) && activEtDates.containsKey(this.secondActivity)) {
            int dateFinActiv1 = activEtDates.get(this.getFirst()) + this.firstActivity.getDuration();
            int date2 = activEtDates.get(this.getSecond());
            if (!(dateFinActiv1 <= date2) || (this.minDelay > this.maxDelay)) {
                return false;
            } else {
                boolean minDelaySatisfied;
                boolean maxDelaySatisfied;
                if (this.minDelay == 0) {
                    minDelaySatisfied = true;
                } else {
                    minDelaySatisfied = date2 >= dateFinActiv1 + this.minDelay;
                }
                if (this.maxDelay == 0) {
                    maxDelaySatisfied = dateFinActiv1 == date2;
                } else {
                    maxDelaySatisfied = date2 <= dateFinActiv1 + this.maxDelay;
                }
                return minDelaySatisfied && maxDelaySatisfied;
            }
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "PrecedenceConstraintWithGap:\n-minDelay= " + this.minDelay + "\n-maxDelay= " + this.maxDelay + "\n-Act1= " + this.getFirst() + "\n-Act2= " + this.getSecond();
    }
}
