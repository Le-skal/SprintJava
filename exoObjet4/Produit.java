package exoObjet4;

// Classe parent Produit
public class Produit {
    protected String nom;
    protected double prixHT;

    public Produit(String nom, double prixHT) {
        this.nom = nom;
        this.prixHT = prixHT;
    }

    public double calculerPrix() {
        return prixHT; // Prix par défaut sans TVA
    }
}

// Classe Livre héritant de Produit
class Livre extends Produit {
    private static final double TVA_LIVRE = 0.05; // 5%

    public Livre(String nom, double prixHT) {
        super(nom, prixHT);
    }

    @Override
    public double calculerPrix() {
        return prixHT * (1 + TVA_LIVRE);
    }
}

// Classe DVD héritant de Produit
class DVD extends Produit {
    private static final double TVA_DVD = 0.20; // 20%

    public DVD(String nom, double prixHT) {
        super(nom, prixHT);
    }

    @Override
    public double calculerPrix() {
        return prixHT * (1 + TVA_DVD);
    }
}


