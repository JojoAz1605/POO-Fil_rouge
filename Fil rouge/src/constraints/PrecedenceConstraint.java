package constraints;

import java.util.ArrayList;
import java.util.HashSet;

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

	public ArrayList<Integer> findSatisfiyingDate() {
		ArrayList<Integer> res = new ArrayList<>();
		int date1 = 0;
		int date2 = 0;
		int max = 1;
		while (true) {
			for (; date1 < max; date1++) {
				for (; date2 < max; date2++) {
					if (isSatisfied(date1, date2)) {
						res.add(date1);
						res.add(date2);
						return res;
					}
				}
			}
			date1 = 0;
			date2 = 0;
			max++;
		}
	}
}