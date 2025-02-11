package exoObjet8;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoisissez une option:");
            System.out.println("1 - Lire la liste des produits");
            System.out.println("2 - Ajouter un produit");
            System.out.println("3 - Supprimer un produit");
            System.out.println("4 - Quitter");
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    lireProduits();
                    break;
                case 2:
                    System.out.print("Entrez le nom du produit à ajouter: ");
                    String produitAjouter = scanner.nextLine();
                    ajouterProduit(produitAjouter);
                    break;
                case 3:
                    System.out.print("Entrez le nom du produit à supprimer: ");
                    String produitSupprimer = scanner.nextLine();
                    supprimerProduit(produitSupprimer);
                    break;
                case 4:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void lireProduits() {
        try{
            File fichier = new File("exoObjet8/data.txt");
            Scanner sc = new Scanner(fichier);

            while (sc.hasNextLine()){
                String ligne = sc.nextLine();
                System.out.println(ligne);
            }
            sc.close();
        } catch (FileNotFoundException e){
            System.out.println("Le fichier n'a pas été trouvé");
        }
    }

    private static void ajouterProduit(String produit) {
        try {
            FileWriter ecrivain = new FileWriter("exoObjet8/data.txt", true);
            ecrivain.write("\n" + produit);
            ecrivain.close();
            System.out.println("ecriture termine");
        } catch (IOException e) {
            System.out.println("Erreur d'écriture");
        }
    }

    private static void supprimerProduit(String produit) {
        ArrayList<String> lignes = new ArrayList<>();
    
        // Étape 1 : Lire le fichier et stocker les lignes dans une ArrayList
        try (BufferedReader lecteur = new BufferedReader(new FileReader("exoObjet8/data.txt"))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                lignes.add(ligne); 
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return;
        }
    
        // Étape 2 : Parcourir le tableau et supprimer le produit s'il est trouvé
        boolean produitTrouve = false;
        for (int i = 0; i < lignes.size(); i++) {
            if (lignes.get(i).equals(produit)) {
                lignes.remove(i); 
                produitTrouve = true;
                break;
            }
        }
    
        if (!produitTrouve) {
            System.out.println("Produit non trouvé.");
            return;
        }
    
        // Étape 3 : Réécrire le fichier avec les lignes mises à jour
        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter("exoObjet8/data.txt"))) {
            for (String ligne : lignes) {
                ecrivain.write(ligne);
                ecrivain.newLine(); 
            }
            System.out.println("Produit supprimé avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
}
