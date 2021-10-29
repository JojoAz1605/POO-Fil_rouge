package nouvotrucquiestinterractif;

import constraints.Activity;
import constraints.Constraint;
import constraints.PrecedenceConstraint;
import solvers.Verifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InteractiveScheduling {
    public static void main(String[] args) {
        // TODO aussi faire saisir les activités et les contraintes ._ ._.. . _..
        ArrayList<Activity> desActiv = new ArrayList<>();
        ArrayList<Constraint> desContraintes = new ArrayList<>();
        Map<Activity, Integer> edt = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            desActiv.add(new Activity("#" + i, i));
        }

        for (int i = 0; i < desActiv.size(); i++) {
            if (!((i + 1) == desActiv.size())) {
                Activity uneActiv = desActiv.get(i);
                Activity uneActiv2 = desActiv.get(i + 1);
                desContraintes.add(new PrecedenceConstraint(uneActiv, uneActiv2));
            }
        }

        for (int i = 0; i < desActiv.size(); i++) {
            // TODO à faire avec java.util.scanner (ALED)(oui)
            Integer laDate;
            try {
                InputStreamReader caLitDesTrucsJeCrois = new InputStreamReader(System.in);
                BufferedReader jeSaisPasCeQueCestMaisAparemmentCaMarche = new BufferedReader(caLitDesTrucsJeCrois);

                System.out.print("Une date pour l'activité" + i);
                laDate = Integer.parseInt(jeSaisPasCeQueCestMaisAparemmentCaMarche.readLine());
            } catch (IOException ioe) {
                laDate = null;
                System.out.println("Bah ça marche pas :'(");
            }
            edt.put(desActiv.get(i), laDate);
        }
        Verifier leVerifieur = new Verifier(new HashSet<>(desContraintes));
        if (leVerifieur.unsatisfied(edt).size() == 0) {
            System.out.println("C'est bon");
        } else {
            System.out.println("C'est pas bon, y a des problèmes avec les contraintes suivantes:");
            System.out.println(leVerifieur.unsatisfied(edt));
        }
    }
}
