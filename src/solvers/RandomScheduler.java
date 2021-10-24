package solvers;

import constraints.Activity;
import constraints.Constraint;

import java.util.*;

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
            if (dateMax - dateMin == 0) {
                date = dateMin;
            } else {
                date = instanceRandom.ints(dateMin, dateMax + 1).findFirst().getAsInt(); // autre version, erreur différente, mais même cause
                // date = instanceRandom.nextInt(dateMax - dateMin) + dateMin;
            }
            // System.out.printf("La date générée pour %s est: %d\n", activ.getDescription(), date);
            edt.put(activ, date);  // ajoute l'activité et une date aléatoire
        }

        return edt;
    }

    public Map<Activity, Integer> generateSchedule(Set<Activity> activities, Set<Constraint> constraints, int dateMin, int dateMax, int nbTirages) {
        Map<Activity, Integer> edt;  // un edt
        ArrayList<Map<Activity, Integer>> lesEdt = new ArrayList<>();  // stocke les edt générés
        Verifier leVerificateur = new Verifier(constraints);  // initialise le vérificateur

        // créé autant d'edt qu'il y a de tirages
        for (int i = 0; i < nbTirages; i++) {
            lesEdt.add(this.generateOneSchedule(activities, dateMin, dateMax));
        }

        edt = lesEdt.get(0);  // prend le premier edt dans la liste

        for (Map<Activity, Integer> unEdt : lesEdt) {
            if (leVerificateur.unsatisfied(unEdt).size() <= edt.size()) {  // vérifie si la taille est "mieux" que l'ancienne
                edt = unEdt;  // si oui, remplace le meilleur edt par celui-là
            }
        }
        System.out.println(edt);
        return edt;
    }
}
