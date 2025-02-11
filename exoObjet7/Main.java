package exoObjet7;

public class Main {
    static String[] tableau = {"10", "20", "30", "40", "50"};

    public static String getElement(int index) throws ArrayIndexOutOfBoundsException {
        if (tableau.length < index){
            throw new ArrayIndexOutOfBoundsException("Vous checkez un element qui est hors du tableau!");
        }

        return tableau[index];
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getElement(10)); 
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
 
    }
}

