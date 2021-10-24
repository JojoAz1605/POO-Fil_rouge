package constraints.executables;

// ----- importations -----

import constraints.Activity;
import constraints.MaxSpanConstraint;
import constraints.PrecedenceConstraint;
import constraints.PrecedenceConstraintWithGap;

import java.util.HashMap;
import java.util.Map;

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

        System.out.println("-----------------------------------------------");
        Activity bruh1 = new Activity("bruh1", 1);
        Activity bruh2 = new Activity("bruh2", 1);
        Activity bruh3 = new Activity("bruh3", 10);
        Activity bruh4 = new Activity("bruh4", 10);

        Map<Activity, Integer> unEdtBruh = new HashMap<>();
        unEdtBruh.put(bruh1, 0);
        unEdtBruh.put(bruh2, 0);
        unEdtBruh.put(bruh3, 10);
        unEdtBruh.put(bruh4, 10);

        MaxSpanConstraint contrinteAvecBokuTruks = new MaxSpanConstraint(unEdtBruh.keySet(), 1);
        System.out.println(contrinteAvecBokuTruks.isSatisfied(unEdtBruh));

        System.out.println(contrinteAvecBokuTruks);
    }
}