package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class GestionUtilisateur {
    ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    Connexion link = null;

    public GestionUtilisateur(Connexion plink) {
        this.link = plink;
    }

    public void listUtilisateurs () {
        this.reloadListUtilisateurs();

        for(Utilisateur user : this.utilisateurs) {
            System.out.println(user);
        }
    }

    private void reloadListUtilisateurs () {
        try {
            this.utilisateurs.clear();
            Statement stmt = this.link.connexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateurs");
            System.out.println("Listes des utilisateurs:");
            while (rs.next()) {
                try {
                    Utilisateur user = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("email"));
                    this.utilisateurs.add(user);
                } catch (IllegalArgumentException e) {
                    // System.out.println("Utilisateur ignoré");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public boolean utilisateurExiste(int id) {        
        try {
            String sql = "SELECT COUNT(*) FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmt = this.link.connexion.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public void addUtilisateurs(Utilisateur utilisateur) {
        try {
            String sqlInsert = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
            PreparedStatement pstmtInsert = this.link.connexion.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, utilisateur.getNom());
            pstmtInsert.setString(2, utilisateur.getEmail());
            pstmtInsert.executeUpdate();
            System.out.println("Insertion réussie.");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void deleteUtilisateur(int id) {
        try {
            String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmtDelete = this.link.connexion.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, id);
            System.out.println("Utilisateur supprimé avec succès !");
            pstmtDelete.executeUpdate();
            System.out.println("Delete réussie.");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        try {
            String sqlUpdate = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmtUpdate = this.link.connexion.prepareStatement(sqlUpdate);
            pstmtUpdate.setString(1, utilisateur.getNom());
            pstmtUpdate.setString(2, utilisateur.getEmail());
            pstmtUpdate.setInt(3, utilisateur.getId());
            pstmtUpdate.executeUpdate();
            System.out.println("mis a jours réussie.");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void searchUtilisateur(String searchTerm) {
        this.reloadListUtilisateurs();
        ArrayList<Utilisateur> matchingUsers = new ArrayList<>();
            
        for (Utilisateur user : this.utilisateurs) {
            if (user.getNom().toLowerCase().contains(searchTerm.toLowerCase()) ||
                user.getEmail().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingUsers.add(user);
            }
        }
        if (matchingUsers.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé pour le terme de recherche : " + searchTerm);
        } else {
            System.out.println("Utilisateurs trouvés pour le terme de recherche '" + searchTerm + "':");
            for (Utilisateur user : matchingUsers) {
                System.out.println(user);
            }
            utilisateurs = matchingUsers;
        }

    }

    public void saveUtilisateursToCSV() {
        this.reloadListUtilisateurs();
        String csvFile = "src\\main\\resources\\docs\\utilisateurs.csv";
    
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("ID,Nom,Email\n");
    
            for (Utilisateur user : this.utilisateurs) {
                writer.append(String.valueOf(user.getId())).append(",");
                writer.append(user.getNom()).append(",");
                writer.append(user.getEmail()).append("\n");
            }
            System.out.println("Exportation vers CSV terminée. Fichier sauvegardé à : " + csvFile);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTableView(TableView<Utilisateur> tableView, String searchTerm) {
        // Reload the list of users to ensure it is up to date
        this.reloadListUtilisateurs();
        if (searchTerm != null) {
            searchUtilisateur(searchTerm);
        }
        

        // Get the columns from the TableView (defined in FXML)
        @SuppressWarnings("unchecked")
        TableColumn<Utilisateur, Integer> idColumn = (TableColumn<Utilisateur, Integer>) tableView.getColumns().get(0);
        @SuppressWarnings("unchecked")
        TableColumn<Utilisateur, String> nomColumn = (TableColumn<Utilisateur, String>) tableView.getColumns().get(1);
        @SuppressWarnings("unchecked")
        TableColumn<Utilisateur, String> emailColumn = (TableColumn<Utilisateur, String>) tableView.getColumns().get(2);

        // Set the cell value factories for the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Create an ObservableList from the ArrayList of utilisateurs
        ObservableList<Utilisateur> data = FXCollections.observableArrayList(this.utilisateurs);

        // Set the data to the TableView
        tableView.setItems(data);
    }
}