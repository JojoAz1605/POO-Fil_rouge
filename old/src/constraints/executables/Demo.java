package constraints.executables;

// ----- importations -----
import constraints.Activity;
import constraints.PrecedenceConstraint;
import constraints.PrecedenceConstraintWithGap;

public class Demo {
	public static void main(String[] args) {
		Activity uneActivite = new Activity("rule", 34);  // instancie une activité
		System.out.println(uneActivite.getDescription());  // affiche sa description
		System.out.println(uneActivite.getDuration());  // affiche sa durée

		// ----- PrecedenceConstraint -----
		Activity act1 = new Activity("activ1", 1);
		Activity act2 = new Activity("activ2", 1);

		PrecedenceConstraint unTruc = new PrecedenceConstraint(act1, act2);
		System.out.print("Avec 5 et 20: ");
		System.out.println(unTruc.isSatisfied(5, 20));
		System.out.print("Avec 5 et 18: ");
		System.out.println(unTruc.isSatisfied(5, 18));

		System.out.println("-----------------------------------------------");

		int premiereDate = 0;
		int deuxiemeDate = 1;
		PrecedenceConstraint uneContrainte = new PrecedenceConstraint(act1, act2);
		PrecedenceConstraintWithGap uneContrainteAvecEcart = new PrecedenceConstraintWithGap(act1, act2, 1, 0);
		System.out.println(uneContrainte.isSatisfied(premiereDate, deuxiemeDate));
		System.out.println(uneContrainteAvecEcart.isSatisfied(premiereDate, deuxiemeDate));
	}
}