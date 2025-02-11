package exoObjet5;

public class Main {
    public static void main(String[] args) {
        Transport voiture = new Voiture();
        Transport avion = new Avion();

        voiture.deplacer();
        avion.deplacer();
    }
}

