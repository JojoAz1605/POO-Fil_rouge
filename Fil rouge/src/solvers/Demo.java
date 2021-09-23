package solvers;

import constraints.Activity;
import constraints.PrecedenceConstraint;

import java.util.ArrayList;
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

        HashMap<Activity, Integer> calendrier = solver1.schedule(activites, contraintes);
    }
}
