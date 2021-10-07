package constraints;

public class PrecedenceConstraintWithGap extends PrecedenceConstraint {
    Activity firstActivity;
    Activity secondActivity;
    int maxDelay;
    int minDelay;

    public PrecedenceConstraintWithGap(Activity firstActivity, Activity secondActivity, int minDelay, int maxDelay) {
        super(firstActivity, secondActivity);
        this.firstActivity = firstActivity;
        this.secondActivity = secondActivity;
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

}
