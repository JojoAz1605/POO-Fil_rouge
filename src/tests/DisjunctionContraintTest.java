package tests;

import constraints.Activity;
import constraints.Constraint;
import constraints.PrecedenceConstraint;

import java.util.HashMap;
import java.util.Map;

public class DisjunctionContraintTest {
    public static void test() {
        Activity uneActiv1 = new Activity("Lire un livre", 3);
        Activity uneActiv2 = new Activity("Jouer", 20);

        Activity uneActiv3 = new Activity("Manger un donut", 2);
        Activity uneActiv4 = new Activity("Insulter les gens", 40);

        Constraint uneContrainte1 = new PrecedenceConstraint(uneActiv1, uneActiv2);
        Constraint uneContrainte2 = new PrecedenceConstraint(uneActiv3, uneActiv4);
        Map<Activity, Integer> unEdt = new HashMap<>();
        unEdt.put(uneActiv1, 0);
        unEdt.put(uneActiv2, 3);

        unEdt.put(uneActiv3, 50);
        unEdt.put(uneActiv4, 51);

        if (uneContrainte1.isSatisfied(unEdt) && uneContrainte2.isSatisfied(unEdt)) {
            System.out.println("PROOOOOOOOOOOOOOOOOOOOOOBLEME");
        } else {
            System.out.println("C'est tout bon");
        }
    }
}
