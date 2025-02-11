package exoObjet6;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private ArrayList<Etudiant> ListE = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Supprimer un étudiant");
            System.out.println("3. Afficher la liste des étudiants");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option: ");
            while (scanner.hasNextInt() == false) {
                scanner.nextLine();
                System.out.println("Option invalide, réessayez.");
            }
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    supprimerEtudiant();
                    break;
                case 3:
                    afficherListe();
                    break;
                case 4:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Option invalide, réessayez.");
            }
        } while (choix != 4);
    }

    public void ajouterEtudiant() {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Classe: ");
        String classe = scanner.nextLine();

        ListE.add(new Etudiant(nom, prenom, classe));
        System.out.println("Étudiant ajouté avec succès.");
    }

    public void supprimerEtudiant() {
        if (ListE.isEmpty()) {
            System.out.println("La liste est vide.");
            return;
        }

        afficherListe();
        System.out.print("Entrez l'index de l'étudiant à supprimer: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        
        if (index >= 0 && index < ListE.size()) {
            ListE.remove(index);
            System.out.println("Étudiant supprimé avec succès.");
        } else {
            System.out.println("Index invalide.");
        }
    }

    public void afficherListe() {
        if (ListE.isEmpty()) {
            System.out.println("Aucun étudiant inscrit.");
        } else {
            System.out.println("Liste des étudiants:");
            for (int i = 0; i < ListE.size(); i++) {
                System.out.println(i + ". " + ListE.get(i));
            }
        }
    }
}
