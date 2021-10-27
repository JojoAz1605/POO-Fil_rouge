package solvers;

import constraints.Activity;
import constraints.Constraint;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomScheduler {
    Random instanceRandom;

    public RandomScheduler(Random instanceRandom) {
        this.instanceRandom = instanceRandom;
    }

    public Map<Activity, Integer> generateOneSchedule(Set<Activity> activitySet, int dateMin, int dateMax) {
        Map<Activity, Integer> edt = new HashMap<>();

        // System.out.printf("min: %d | max: %d, taille d'activitySet: %d\n", dateMin, dateMax, activitySet.size());

        for (Activity activ : activitySet) {
            int date;

            date = this.instanceRandom.nextInt((dateMax - dateMin) + 1) + dateMin;

            // System.out.printf("La date générée pour %s est: %d\n", activ.getDescription(), date);
            edt.put(activ, date);  // ajoute l'activité et une date aléatoire
        }

        return edt;
    }

    public Map<Activity, Integer> generateSchedule(Set<Activity> activities, Set<Constraint> constraints, int dateMin, int dateMax, int nbTirages) {
        Map<Activity, Integer> leMeilleur = null;  // le meilleur edt
        Verifier leVerificateur = new Verifier(constraints);  // initialise le vérificateur

        for (int i = 0; i < nbTirages; i++) {
            Map<Activity, Integer> unNouvelEdt = this.generateOneSchedule(activities, dateMin, dateMax);  // génération d'un edt
            int tailleContraintesNonSatisNouvelEdt = leVerificateur.unsatisfied(unNouvelEdt).size();  // la taille de la liste des contraintes non satisfaites

            if (leMeilleur == null) {
                leMeilleur = unNouvelEdt;  // si c'est null, initialise la variable
            } else if (tailleContraintesNonSatisNouvelEdt == 0) {
                leMeilleur = unNouvelEdt;  // si la nb de contraintes non satisfaites est "mieux", remplace le meilleur edt
            }
        }
        return leMeilleur;  // retourne le meilleur edt
    }
}
