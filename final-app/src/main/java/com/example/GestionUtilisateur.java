package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            // Check if the nom or email contains the search term (case-insensitive)
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
        }

    }

    public void saveUtilisateursToCSV() {
        String csvFile = "utilisateurs.csv";
        String sql = "SELECT * FROM utilisateurs";

        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             FileWriter writer = new FileWriter(csvFile)) {

            // Écrire l'en-tête du fichier CSV
            writer.append("ID,Nom,Email,Cree le,Mis a jour le\n");

            // Parcourir les résultats et écrire chaque ligne dans le fichier CSV
            while (rs.next()) {
                writer.append(String.valueOf(rs.getInt("id"))).append(",");
                writer.append(rs.getString("nom")).append(",");
                writer.append(rs.getString("email")).append(",");
                writer.append(rs.getTimestamp("created_at").toString()).append(",");
                writer.append(rs.getTimestamp("updated_at").toString()).append("\n");
            }

            System.out.println("Exportation vers CSV terminée. Fichier sauvegardé à : " + csvFile);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}