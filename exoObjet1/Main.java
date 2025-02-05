package exoObjet1;

public class Main {
    public static void main(String[] args) {
        Voiture maVoiture = new Voiture();
        
        maVoiture.marque = "Toyota";
        maVoiture.modele = "Picnic";
        maVoiture.couleur = "decapotable";
        
        maVoiture.demarrer();
        maVoiture.accelerer();
        maVoiture.freiner();
    }
}

