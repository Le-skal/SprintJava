package com.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class App extends Application {

    private Stage primaryStage; // Store a reference to the main stage

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage; // Initialize the reference to the main stage

        Connexion link = new Connexion();
        GestionUtilisateur gu = new GestionUtilisateur(link);

        VBox root = FXMLLoader.load(getClass().getResource("/layouts/App.fxml"));

        Label messageLabel = (Label) root.lookup("#messageLabel");
        Button button_1 = (Button) root.lookup("#button_1");
        Button button_2 = (Button) root.lookup("#button_2");
        Button button_3 = (Button) root.lookup("#button_3");
        Button button_4 = (Button) root.lookup("#button_4");
        Button button_5 = (Button) root.lookup("#button_5");
        Button button_6 = (Button) root.lookup("#button_6");
        Button button_0 = (Button) root.lookup("#button_0");

        button_1.setOnAction(event -> {
            try {
                VBox Root_list = FXMLLoader.load(getClass().getResource("/layouts/list.fxml"));

                Label list_messageLabel = (Label) Root_list.lookup("#list_messageLabel");
                Button list_button_0 = (Button) Root_list.lookup("#list_button_0");
                
                TableView<Utilisateur> userTableView = (TableView<Utilisateur>) Root_list.lookup("#userTableView");

                gu.createTableView(userTableView);
                
                  
  
  
                list_button_0.setOnAction(event2 -> {
                    Stage currentStage = (Stage) list_button_0.getScene().getWindow();
                    currentStage.close();

                    primaryStage.show();
                });

                Scene Scene_list = new Scene(Root_list, 400, 300);
                Stage newStage = new Stage();
                newStage.setTitle("User List");
                newStage.setScene(Scene_list);
                newStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        button_2.setOnAction(event -> {
            try {
                VBox Root_ajouter = FXMLLoader.load(getClass().getResource("/layouts/Ajouter.fxml"));
                
                Label ajouter_messageLabel = (Label) Root_ajouter.lookup("#ajouter_messageLabel");
                Button ajouter_button_0 = (Button) Root_ajouter.lookup("#ajouter_button_0");
                TextField nameField = (TextField) Root_ajouter.lookup("#nameField");
                TextField emailField = (TextField) Root_ajouter.lookup("#emailField");
                Button ajouter_button_1 = (Button) Root_ajouter.lookup("#ajouter_button_1");

                ajouter_button_0.setOnAction(event2 -> {
                    Stage currentStage = (Stage) ajouter_button_0.getScene().getWindow();
                    currentStage.close();

                    primaryStage.show();
                });

                ajouter_button_1.setOnAction(event2 -> {
                    String nom = nameField.getText();
                    String email = emailField.getText();

                    if (nom.isEmpty() || email.isEmpty()) {
                        ajouter_messageLabel.setText("Veuillez remplir tous les champs !");
                        return;
                    }

                    try {
                        Utilisateur utilisateur = new Utilisateur(0, nom, email); // Assuming 0 is a placeholder ID
                        gu.addUtilisateurs(utilisateur);
                        ajouter_messageLabel.setText("Utilisateur ajouté avec succès !");
        
                    } catch (IllegalArgumentException e) {
                        ajouter_messageLabel.setText(e.getMessage());
                    }
                });

                Scene Scene_ajouter = new Scene(Root_ajouter, 400, 300);
                Stage newStage = new Stage();
                newStage.setTitle("User ajouter");
                newStage.setScene(Scene_ajouter);
                newStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });




        button_3.setOnAction(event -> {
            try {
                VBox Root_supprimer = FXMLLoader.load(getClass().getResource("/layouts/Supprimer.fxml"));
                
                Label supprimer_messageLabel = (Label) Root_supprimer.lookup("#supprimer_messageLabel");
                Button supprimer_button_0 = (Button) Root_supprimer.lookup("#supprimer_button_0");
                TextField idField = (TextField) Root_supprimer.lookup("#idField");
                Button supprimer_button_1 = (Button) Root_supprimer.lookup("#supprimer_button_1");

                supprimer_button_0.setOnAction(event2 -> {
                    Stage currentStage = (Stage) supprimer_button_0.getScene().getWindow();
                    currentStage.close();

                    primaryStage.show();
                });

                supprimer_button_1.setOnAction(event2 -> {
                    String idText = idField.getText();

                    if (idText.isEmpty()) {
                        supprimer_messageLabel.setText("Veuillez remplir tous les champs !");
                        return;
                    }

                    try {
                        int id = Integer.parseInt(idText);
                        gu.deleteUtilisateur(id);
                        supprimer_messageLabel.setText("Utilisateur supprimer avec succès !");
        
                    } catch (IllegalArgumentException e) {
                        supprimer_messageLabel.setText(e.getMessage());
                    }
                });

                Scene Scene_supprimer = new Scene(Root_supprimer, 400, 300);
                Stage newStage = new Stage();
                newStage.setTitle("User supprimer");
                newStage.setScene(Scene_supprimer);
                newStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }


        });




















        button_6.setOnAction(event -> {
            gu.saveUtilisateursToCSV();
            messageLabel.setText("🐼-WE LOVE PANDAS!!!!!-🐼");

        });

        button_0.setOnAction(event -> {
            link.close();
            primaryStage.close();
        });


        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Ma Première Application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

    

 // ALL THE CODE BELOW IS GOING TO BE REMOVED, just USING IT TO REMEMBER   
    
//  package com.example;

//  import java.util.Scanner;
 
//  public class App 
//  {
//      public static void main( String[] args )
//      {
//          /* On clear la console */
//          System.out.print("\033[H\033[2J");   
//          System.out.flush();
 
//          System.out.println( "Hello World!" );
//          Connexion link = new Connexion();
//          GestionUtilisateur gu = new GestionUtilisateur(link);
 
//          /* On demande à l'utilisateur ce qu'il veut faire */
//          Scanner sc = new Scanner(System.in);
//          int choice = 0;
 
//          do { 
//              System.out.println("Que voulez vosu faire ?");
//              System.out.println("1 - Lister les utilisateurs");
//              System.out.println("2 - Ajouter un utilisateur");
//              System.out.println("3: Supprimer un utilisateur");
//              System.out.println("4: Modifier un utilisateur");
//              System.out.println("5: Rechercher un utilisateur par nom ou email");
//              System.out.println("6: Sauver tous les utilisateurs dans un csv");
//              System.out.println("0 - Quitter");
//              choice = sc.nextInt();
             
//              System.out.print("\033[H\033[2J");   
//              System.out.flush(); 
             
//              switch (choice) {
//                  case 1:
//                      gu.listUtilisateurs();
//                      System.out.println("---------------------");
//                      break;
 
//                  case 2:
//                      System.out.print("Nom de l'utilisateur: ");
//                      sc.nextLine();
//                      String nom = sc.nextLine();
 
//                      System.out.print("Email de l'utilisateur: ");
//                      String email = sc.nextLine();
 
//                      try {
//                          Utilisateur utilisateur = new Utilisateur(0, nom, email);
//                          gu.addUtilisateurs(utilisateur);
//                      } catch (IllegalArgumentException e) {
//                          System.out.println(e.getMessage());
//                      }
                     
//                      System.out.println("---------------------");
//                      break;
//                  case 3:
//                      System.out.print("ID de l'utilisateur: ");
//                      int idDelete = sc.nextInt();
//                      gu.deleteUtilisateur(idDelete);
//                      System.out.println("---------------------");
//                      break;
                 
//                  case 4:
//                      System.out.print("ID de l'utilisateur: ");
//                      int idUpdate = sc.nextInt();
 
//                      System.out.print("Nouveau Nom de l'utilisateur: ");
//                      sc.nextLine();
//                      String nom_new = sc.nextLine();
 
//                      System.out.print("Nouveau Email de l'utilisateur: ");
//                      String email_new = sc.nextLine();
 
//                      try {
//                          Utilisateur utilisateur = new Utilisateur(idUpdate, nom_new, email_new);
//                          gu.updateUtilisateur(utilisateur);
//                      } catch (IllegalArgumentException e) {
//                          System.out.println(e.getMessage());
//                      }
 
//                      System.out.println("---------------------");
//                      break;
                 
//                  case 5:
//                      System.out.print("Entrez un nom ou un email à rechercher: ");
//                      sc.nextLine();
//                      String searchTerm = sc.nextLine();
//                      gu.searchUtilisateur(searchTerm);
//                      System.out.println("---------------------");
//                      break;
 
//                  case 6:
//                      gu.saveUtilisateursToCSV();
//                      System.out.println("---------------------");
//                      break;
             
//                  default:
//                      System.out.println("Pas d'action pour ce choix !");
//                      break;
//              }
//          } while(choice != 0);
 
//          link.close();
//          sc.close();
//      }
//  }