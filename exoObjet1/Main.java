package exoObjet1;

public class Main {
    public static void main(String[] args) {
        Voiture maVoiture = new Voiture("Toyota", "picnic", "rouge");
        System.out.println(maVoiture.getMarque() + " " + maVoiture.getModele() + " " + maVoiture.getCouleur());

    }
}


