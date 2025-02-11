package exoObjet7;

public class Main {
    static String[] tableau = {"10", "20", "30", "40", "50"};

    public static String getElement(int index) {
        try {
            return tableau[index];
        } catch (Exception e) {
            return "Erreur: " + index + " hors limites. Taille du tableau: " + tableau.length;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(getElement(0)); 
        System.out.println(getElement(10)); 
    }
}

