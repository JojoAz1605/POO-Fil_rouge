package constraints;

import java.util.ArrayList;

public class PrecedenceConstraint {
	Activity firstActivity;  // une première activité
	Activity secondActivity;  // une deuxième activité

	public PrecedenceConstraint(Activity firstActivity, Activity secondActivity) {
		this.firstActivity = firstActivity;
		this.secondActivity = secondActivity;
	}

	public Activity getFirst() {
		return this.firstActivity;
	}

	public Activity getSecond() {
		return this.secondActivity;
	}

	public boolean isSatisfied(int date1, int date2) {
		int firstActivityDuration = this.firstActivity.getDuration();

		return firstActivityDuration + date1 <= date2;
	}
}