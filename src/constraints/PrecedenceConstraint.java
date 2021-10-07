package constraints;

public class PrecedenceConstraint extends BinaryConstraint {
	public PrecedenceConstraint(Activity firstActivity, Activity secondActivity) {
		super(firstActivity, secondActivity);
	}

	@Override
	public boolean isSatisfied(int date1, int date2) {
		int dateFinActivite1 = date1 + this.firstActivity.getDuration();
		return dateFinActivite1 <= date2;
	}
}