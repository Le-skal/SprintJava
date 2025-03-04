package com.monprojet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateurs {
    private List<Utilisateur> listUser;
    private Connexion link;

    public GestionUtilisateurs(Connexion connexion) {
        this.listUser = new ArrayList<>();
        this.link = connexion;
    }

    public void insererUtilisateur(String nom, String email) {
        String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Utilisateur inséré avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierUtilisateur(int id, String nom, String email) {
        String sql = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        try (PreparedStatement pstmt = link.connexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Email: " + rs.getString("email") +
                        ", Nom: " + rs.getString("nom") +
                        ", Créé le: " + rs.getTimestamp("created_at") +
                        ", Mis à jour le: " + rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}