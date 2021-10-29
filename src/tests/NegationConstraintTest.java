package tests;

import constraints.Activity;
import constraints.Constraint;
import constraints.NegationConstraint;
import constraints.PrecedenceConstraint;

import java.util.HashMap;
import java.util.Map;

public class NegationConstraintTest {
    public static void test() {
        Activity uneActiv1 = new Activity("Lire un livre", 3);
        Activity uneActiv2 = new Activity("Jouer", 20);

        Constraint uneContrainte = new PrecedenceConstraint(uneActiv1, uneActiv2);
        Constraint uneContrainteMaisInversee = new NegationConstraint(uneContrainte);
        Map<Activity, Integer> unEdtAssezCourt = new HashMap<>();
        unEdtAssezCourt.put(uneActiv1, 0);
        unEdtAssezCourt.put(uneActiv2, 4);

        if (uneContrainte.isSatisfied(unEdtAssezCourt) == uneContrainteMaisInversee.isSatisfied(unEdtAssezCourt)) {
            System.out.println("PROOOOOOOOOOOOOOOOOOOOOOBLEME");
        } else {
            System.out.println("C'est tout bon");
        }
    }
}
