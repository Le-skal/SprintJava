package com.example;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        /* On clear la console */
        System.out.print("\033[H\033[2J");   
        System.out.flush();

        System.out.println( "Hello World!" );
        Connexion link = new Connexion();
        GestionUtilisateur gu = new GestionUtilisateur(link);

        /* On demande à l'utilisateur ce qu'il veut faire */
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do { 
            System.out.println("Que voulez vosu faire ?");
            System.out.println("1 - Lister les utilisateurs");
            System.out.println("2 - Ajouter un utilisateur");
            System.out.println("3: Supprimer un utilisateur");
            System.out.println("4: Modifier un utilisateur");
            System.out.println("5: Rechercher un utilisateur par nom ou email");
            System.out.println("6: Sauver tous les utilisateurs dans un csv");
            System.out.println("0 - Quitter");
            choice = sc.nextInt();
            
            System.out.print("\033[H\033[2J");   
            System.out.flush(); 
            
            switch (choice) {
                case 1:
                    gu.listUtilisateurs();
                    System.out.println("---------------------");
                    break;

                case 2:
                    System.out.print("Nom de l'utilisateur: ");
                    sc.nextLine();
                    String nom = sc.nextLine();

                    System.out.print("Email de l'utilisateur: ");
                    String email = sc.nextLine();

                    try {
                        Utilisateur utilisateur = new Utilisateur(0, nom, email);
                        gu.addUtilisateurs(utilisateur);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    
                    System.out.println("---------------------");
                    break;
                case 3:
                    System.out.print("ID de l'utilisateur: ");
                    int idDelete = sc.nextInt();
                    gu.deleteUtilisateur(idDelete);
                    System.out.println("---------------------");
                    break;
                
                case 4:
                    System.out.print("ID de l'utilisateur: ");
                    int idUpdate = sc.nextInt();

                    System.out.print("Nouveau Nom de l'utilisateur: ");
                    sc.nextLine();
                    String nom_new = sc.nextLine();

                    System.out.print("Nouveau Email de l'utilisateur: ");
                    String email_new = sc.nextLine();

                    try {
                        Utilisateur utilisateur = new Utilisateur(idUpdate, nom_new, email_new);
                        gu.updateUtilisateur(utilisateur);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("---------------------");
                    break;
                
                case 5:
                    System.out.print("Entrez un nom ou un email à rechercher: ");
                    sc.nextLine();
                    String searchTerm = sc.nextLine();
                    gu.searchUtilisateur(searchTerm);
                    System.out.println("---------------------");
                    break;

                case 6:
                    gu.saveUtilisateursToCSV();
                    System.out.println("---------------------");
                    break;
            
                default:
                    System.out.println("Pas d'action pour ce choix !");
                    break;
            }
        } while(choice != 0);

        link.close();
        sc.close();
    }
}