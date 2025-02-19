package com.monprojet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionUtilisateurs {

    public static void insererUtilisateur(String email, String nom) {
        String sql = "INSERT INTO utilisateurs (email, nom) VALUES (?, ?)";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, nom);
            pstmt.executeUpdate();
            System.out.println("Utilisateur inséré avec succès.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerUtilisateur(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierUtilisateur(int id, String email, String nom) {
        String sql = "UPDATE utilisateurs SET email = ?, nom = ? WHERE id = ?";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, nom);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Utilisateur modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afficherUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        try (Connection conn = Connexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
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
