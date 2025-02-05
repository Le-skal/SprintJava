package Cours.exoObjet1;

public class Voiture {
    String marque;
    String modele;
    String couleur;

    void demarrer() {
        System.out.println("La voiture " + marque + " " + modele + " de couleur " + couleur + " démarre.");
    }

    void accelerer() {
        System.out.println("La voiture " + marque + " " + modele + " accélère.");
    }

    void freiner() {
        System.out.println("La voiture " + marque + " " + modele + " freine.");
    }
}