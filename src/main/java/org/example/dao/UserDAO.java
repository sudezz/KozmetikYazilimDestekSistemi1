package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.model.ItUzmaniBuilder;
import org.example.model.StandardEmployeeBuilder;
import org.example.model.User;
import org.example.model.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // giriş işlemi
    public User authenticate(String email, String employeeNo, String password) {
        String query = "SELECT u.*, d.name AS dep_name " +
                "FROM users u " +
                "JOIN departments d ON u.department_id = d.id " +
                "WHERE u.email = ? AND u.employee_no = ? AND u.password = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, employeeNo);
                pstmt.setString(3, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role_type");
                        String depName = rs.getString("dep_name");

                        UserBuilder builder;
                        if ("IT_UZMANI".equals(role)) {
                            builder = new ItUzmaniBuilder();
                        } else {
                            builder = new StandardEmployeeBuilder();
                        }

                        return builder.setId(rs.getInt("id"))
                                .setFullName(rs.getString("full_name"))
                                .setEmployeeNo(rs.getString("employee_no"))
                                .setEmail(rs.getString("email"))
                                .setPassword(rs.getString("password"))
                                .setDepartmentId(rs.getInt("department_id"))
                                .setDepartmentName(depName)
                                .build();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Giriş hatası: " + e.getMessage());
        }
        return null;
    }

    // yeni kullanıcı eklem (sadece IT_UZMANI) rolünde olanlar
    public boolean kullaniciEkle(String tamAd, String email, String employeeNo, String sifre, String rol, int departmanId) {

        String query = "INSERT INTO users (full_name, email, employee_no, password, role_type, department_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getInstance();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, tamAd);
                pstmt.setString(2, email);
                pstmt.setString(3, employeeNo);
                pstmt.setString(4, sifre);
                pstmt.setString(5, rol);
                pstmt.setInt(6, departmanId);

                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Kullanıcı eklenirken hata: " + e.getMessage());
            return false;
        }
    }

    // boxı doldurmak için departmanlar çekiliyor
    public List<String> getTumDepartmanlar() {
        List<String> departmanlar = new ArrayList<>();
        String query = "SELECT id, name FROM departments";

        try {
            Connection conn = DatabaseConnection.getInstance();
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    // örn: "1 - Yazılım"
                    departmanlar.add(rs.getInt("id") + " - " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Departman çekme hatası: " + e.getMessage());
        }
        return departmanlar;
    }

    public String getYeniCalisanNo() {
        String sonNo = "KOZ-000"; // eğer veritabanı boşsa buradan başlanır
        String query = "SELECT employee_no FROM users ORDER BY id DESC LIMIT 1";

        try {
            Connection conn = DatabaseConnection.getInstance();
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sonNo = rs.getString("employee_no");
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }

        try {
            // "KOZ-002" metnini parçala, "002" kısmını al, sayıya çevir ve 1 ekle işlemini burda yaptım
            int numara = Integer.parseInt(sonNo.split("-")[1]);
            numara++;
            return String.format("KOZ-%03d", numara); // Yeniden "KOZ-003" formatına getir
        } catch (Exception e) {
            return "KOZ-001"; // Hata olursa yedek plan
        }
    }
}