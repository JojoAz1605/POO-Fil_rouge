package constraints;

public class MeetConstraint extends BinaryConstraint {
    public MeetConstraint(Activity firstActivity, Activity secondActivity) {
        super(firstActivity, secondActivity);
    }

    @Override
    public boolean isSatisfied(int date1, int date2) {
        int dateFinActivite1 = date1 + this.firstActivity.getDuration();
        return dateFinActivite1 == date2;
    }

    @Override
    public String toString() {
        return "MeetConstraint:\n" + "-Act1= " + this.getFirst() + "\n-Act2= " + this.getSecond();
    }
}