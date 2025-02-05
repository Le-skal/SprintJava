package initiation;
import java.util.Scanner;

public class Ask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez votre Prenom:");
        String nom = sc.nextLine();

        System.out.println("Bonjour, " + nom + "!");
        int age = -1;

        System.out.println("Entrez votre age:");
        if (sc.hasNextInt()) {
            age = sc.nextInt();
        }else{
            System.out.println("Age non correcte !");
            System.exit(0);
        }

        if (age >= 130){
            System.out.println("Toujours vivant?");
        }else if (age < 0){
            System.out.println("Deja avec un ecrans dans les mains??");
        }else if (age > 18){
            System.out.println("Vous etes un adult");
        }else if (age == 18){
            System.out.println("Bienvenue a la vie adulte");
        }else{
            System.out.println("Vous etes un enfant");
        }

        sc.close();
    }
}
