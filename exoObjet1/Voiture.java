package exoObjet1;

public class Voiture {
    private String marque;
    private String modele;
    private String couleur;

    public String[] colorAuthorized = {"rouge", "bleu", "vert", "jaune", "noir", "blanc"};
    
    public Voiture(String marque, String modele, String couleur) {
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
    }
    
    
    public void accelerer() {
        System.out.println("La voiture accelere.");
    }
    
    public void freiner() {
        System.out.println("La voiture freine.");
    }
    
    public void demarrer() {
        System.out.println("La voiture demarre.");
    }
    
    public String getCouleur(){
        return this.couleur;
    }

    public String getMarque(){
        return this.marque;
    }

    public String getModele(){
        return this.modele;
    }


}
