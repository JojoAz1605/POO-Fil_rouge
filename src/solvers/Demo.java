package solvers;

import constraints.Activity;
import constraints.PrecedenceConstraint;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.HashSet;

public class Demo {
    public static void main(String[] args) {
        Activity seLever = new Activity("se lever", 1);
        Activity allerAuTravail = new Activity("aller au travail", 15);
        Activity prendreUneDouche = new Activity("prendre une douche", 10);
        Activity brosserLesDents = new Activity("se brosser les dents", 3);
        Activity habiller = new Activity("s'habiller", 2);
        Activity petitDej = new Activity("petit déj", 15);

        HashSet<Activity> activites = new HashSet<>();
        activites.add(seLever);
        activites.add(allerAuTravail);
        activites.add(prendreUneDouche);
        activites.add(brosserLesDents);
        activites.add(habiller);
        activites.add(petitDej);

        PrecedenceConstraint contrainte1 = new PrecedenceConstraint(seLever, petitDej);
        PrecedenceConstraint contrainte2 = new PrecedenceConstraint(seLever, habiller);
        PrecedenceConstraint contrainte3 = new PrecedenceConstraint(petitDej, brosserLesDents);
        PrecedenceConstraint contrainte4 = new PrecedenceConstraint(prendreUneDouche, habiller);
        PrecedenceConstraint contrainte5 = new PrecedenceConstraint(brosserLesDents, allerAuTravail);
        PrecedenceConstraint contrainte6 = new PrecedenceConstraint(habiller, allerAuTravail);
        PrecedenceConstraint contrainte7 = new PrecedenceConstraint(petitDej, allerAuTravail);
        PrecedenceConstraint contrainte8 = new PrecedenceConstraint(seLever, prendreUneDouche);

        HashSet<PrecedenceConstraint> contraintes = new HashSet<>();
        contraintes.add(contrainte1);
        contraintes.add(contrainte2);
        contraintes.add(contrainte3);
        contraintes.add(contrainte4);
        contraintes.add(contrainte5);
        contraintes.add(contrainte6);
        contraintes.add(contrainte7);
        contraintes.add(contrainte8);

        TopologicalSorter solver1 = new TopologicalSorter();

        // HashMap<Activity, Integer> calendrier = solver1.schedule(activites, contraintes);

        ArrayList<Activity> grosseListeActiv = new ArrayList<>();
        ArrayList<PrecedenceConstraint> grosseListeContraintes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 30);  // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
            Activity laNouvelleActiv = new Activity("activ" + i, randomNum);
            grosseListeActiv.add(laNouvelleActiv);
        }
        for (Activity activite: grosseListeActiv) {
            if (!(grosseListeActiv.indexOf(activite) + 1 >= 100)) {
                Activity activ2 = grosseListeActiv.get(grosseListeActiv.indexOf(activite) + 1);
                PrecedenceConstraint laNouvelleContraite = new PrecedenceConstraint(activite, activ2);
                grosseListeContraintes.add(laNouvelleContraite);
            }
        }
        HashSet<Activity> grosSetActiv = new HashSet<>(grosseListeActiv);
        HashSet<PrecedenceConstraint> grosSetContrainte = new HashSet<>(grosseListeContraintes);
        HashMap<Activity, Integer> calendrier2 = solver1.schedule(grosSetActiv, grosSetContrainte);
    }

}
