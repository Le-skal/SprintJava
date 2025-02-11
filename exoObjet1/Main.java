package exoObjet1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Color: ");
        String couleur = sc.nextLine();
        sc.close();

        Voiture maVoiture = new Voiture("Toyota", "picnic", "rouge");
        System.out.println(maVoiture.getMarque() + " " + maVoiture.getModele() + " " + maVoiture.getCouleur());
        
    }
}


