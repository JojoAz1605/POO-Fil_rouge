package constraints;

public abstract class BinaryConstraint {
    protected Activity firstActivity;
    protected Activity secondActivity;

    public BinaryConstraint(Activity firstActiv, Activity secondActiv){
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
}
