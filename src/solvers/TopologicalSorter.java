package solvers;

// ----- imports -----
import constraints.Activity;
import constraints.PrecedenceConstraint;

import java.util.*;

public class TopologicalSorter {
    public TopologicalSorter() {  // constructeur
    }

    public ArrayList<Activity> bruteForceSort(HashSet<Activity> activites, HashSet<PrecedenceConstraint> contraintesPreced) {
        HashSet<Activity> activitesCopy = new HashSet<>(activites);  // une copie pour mieux gérer
        ArrayList<Activity> res = new ArrayList<>();  // le résultat

        HashSet<Activity> avecContraintes = new HashSet<>();  // stocke les activités avec des contraintes
        for (PrecedenceConstraint contrainte: contraintesPreced) {  // trouve les activités avec des contraintes
            avecContraintes.add(contrainte.getFirst());
            avecContraintes.add(contrainte.getSecond());
        }


        // ----- affichage des paramètres -----
        System.out.print("activités: ");
        System.out.println(activites);
        System.out.print("Contraintes: ");
        System.out.println(contraintesPreced);

        while (!activitesCopy.isEmpty()) {  // tant que la copie d'activite n'est pas vide
            boolean continuer = false;  // on définit continuer à 0
            for (Activity activite: activitesCopy) {  // pour toutes les activités
                System.out.print("Début du test pour: ");
                System.out.println(activite.getDescription());
                boolean ok = true;  // ok à true
                for (PrecedenceConstraint contrainte: contraintesPreced) {  // pour toutes les contraintes
                    PrecedenceConstraint caDoitEtreCommeCa = new PrecedenceConstraint(activite, contrainte.getSecond());  // sert à vérifier que contrainte est de la bonne forme
                    boolean isSameFirst = Objects.equals(contrainte.getFirst(), caDoitEtreCommeCa.getFirst());  // vérifie que les premières activités sont les mêmes
                    boolean isSameSecond = Objects.equals(contrainte.getSecond(), caDoitEtreCommeCa.getSecond());  // vérifie que les secondes activité sont les mêmes
                    boolean areSameContraintes = isSameFirst && isSameSecond;  // vérifie que contrainte est de la bonne forme

                    if (areSameContraintes && !res.contains(contrainte.getSecond())) { // voit si l'activité est mentionnée comme seconde contrainte
                        ok = false;  // l'activité est mentionnée comme seconde contrainte, ça pose problème :sort de la boucle
                        break;
                    }
                }
                if (ok) {  // si c'est ok(que l'activité n'est pas mentionnée comme seconde contrainte)
                    System.out.print("Une activité est ajoutée au résultat: ");
                    System.out.println(activite.getDescription());
                    res.add(activite); // l'ajoute au résultat
                    activitesCopy.remove(activite);  // et retire l'activité de la copie de la liste d'activités(pour éviter qu'elle soit prise en compte plus tard)
                    continuer = true;  // on peut donc continuer
                    break;  // sort de la boucle
                }
            }
            if (!continuer) {  // aucune solution ne peut-être trouvée
                System.out.println("Pas de résultat, null est retourné...");
                return null;
            }
        }
        for (Activity activite: activites) {  // ajoute les éléments non soumis à des contraintes à la fin
            if (!avecContraintes.contains(activite)) {
                System.out.print("Ajout d'une activité non soumis à une contrainte: ");
                System.out.println(activite.getDescription());
                res.add(activite);
            }
        }
        System.out.print("Un résultat est retourné: ");
        System.out.println(res);
        Collections.reverse(res);  // inverse la liste
        return res;  // et la retourne
    }

    public HashMap<Activity, Integer> schedule(HashSet<Activity> activites, HashSet<PrecedenceConstraint> contraintesPreced) {
        // ArrayList<Activity> ordreActivites = bruteForceSort(activites, contraintesPreced);
        ArrayList<Activity> ordreActivites = linearTimeSort(activites, contraintesPreced);

        if (ordreActivites == null) {  // s'il n'y a pas de solutions, retourne null
            return null;
        }

        HashMap<Activity, Integer> res = new HashMap<>();  // initialise la liste pour stocker le résultat

        Activity derniereActiv = null;  // stocke la dernière activité traitée
        for (Activity ordreActivite : ordreActivites) {
            int derniereDate;  // stocke la date du dernier élément de res
            int dureeDerniereActiv;  // stocke la durée de la dernière activité traitée
            if (res.isEmpty()) {  // si res est vide
                derniereDate = 0;  // la dernière durée n'existe pas, elle vaut donc 0
                res.put(ordreActivite, derniereDate);  // la première activité se fait en date 0
                System.out.printf("%s -> %d\n", ordreActivite.getDescription(), derniereDate);
            } else {
                dureeDerniereActiv = derniereActiv.getDuration();  // prend la durée de la dernière activité traitée
                derniereDate = res.get(derniereActiv);  // prend la date de la dernière activité traitée
                res.put(ordreActivite, derniereDate + dureeDerniereActiv);  // la date de la dernière activité + la durée de la suivante
                System.out.printf("%s -> %d\n", ordreActivite.getDescription(), derniereDate + dureeDerniereActiv);
            }
            derniereActiv = ordreActivite;  // stocke l'activité qui vient d'être traitée
        }
        return res;  // retourne un "calendrier" des activités
    }
    public ArrayList<Activity> linearTimeSort(HashSet<Activity> activites, HashSet<PrecedenceConstraint> contraintesPreced) {
        HashMap<Activity, Integer> nbPredecesseurs = new HashMap<>();  // créé le dico des prédecesseurs
        HashMap<Activity, ArrayList<Activity>> successeurs = new HashMap<>();  // créé le dico des successeurs

        for (Activity activite: activites) {
            nbPredecesseurs.put(activite, 0);  // initialise chaque activité à 0
            successeurs.put(activite, new ArrayList<>());  // initialise chaque activité à une liste vide
        }

        for (PrecedenceConstraint contrainte: contraintesPreced) {  // parcours les contraintes
            Activity firstActiv = contrainte.getFirst();  // prend la première activité
            Activity secondActiv = contrainte.getSecond();  // prend la deuxième activité

            nbPredecesseurs.replace(secondActiv, nbPredecesseurs.get(secondActiv) + 1);  // incrémente le nombre de prédécesseurs de la seconde activité
            ArrayList<Activity> listeSuccesFirstActiv = successeurs.get(firstActiv);  // créé la liste des successeurs de firstActiv
            listeSuccesFirstActiv.add(secondActiv);  // ajoute la seconde activité à la liste des successeurs de firstActiv
            successeurs.replace(firstActiv, listeSuccesFirstActiv);  // ajoute la première activité aux succeseurs
        }

        ArrayList<Activity> L = new ArrayList<>();  // créé la liste L
        ArrayList<Activity> res = new ArrayList<>();  // pour stocker le résultat
        for (Activity activite: activites) {  // pour toutes les activités
            int leNombreDePredecesseurs = nbPredecesseurs.get(activite);  // prend le nombre de prédeceseurs de l'activité
            if (leNombreDePredecesseurs == 0) {  // si il n'y en a pas
                L.add(activite);  // ajoute l'activité à L
            }
        }
        while (!L.isEmpty()) {  // tant que L n'est pas vide
            Activity activ = L.get(0);  // prend la première activité de L
            res.add(activ);  // ajoute cette activité au résultat
            L.remove(activ);  // retire l'activité de L
            for (Activity activite: successeurs.get(activ)) {  // pour toutes les activités qui succèdent à l'activité
                nbPredecesseurs.replace(activite, nbPredecesseurs.get(activite) - 1);  // décrémente son nombre de prédecesseurs
                if (nbPredecesseurs.get(activite) == 0) {  // si le nombre de prédecesseurs de l'activité est égal à 0
                    L.add(activite);  // rajoute l'activité à L
                }
            }
        }
        if (res.size() == activites.size()) {  // si res à autant d'éléments que activites
            System.out.println("Un résultat est trouvé!\nLe voici: ");
            System.out.println(res);
            return res;  // retourne le résultat
        } else {  // sinon pas de résultat
            System.out.println("Pas de résultat, null est retourné");
            return null;  // retourne null
        }
    }

}
