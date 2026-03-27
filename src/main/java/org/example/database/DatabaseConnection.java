package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. Singleton referansı (instance)
    private static Connection instance;

    // MySQL Bağlantı Bilgileri (Buraya kendi MySQL şifreni yazmayı unutma!)
    private static final String URL = "jdbc:mysql://localhost:3306/kozmetik_helpdesk";
    private static final String USER = "root";
    private static final String PASSWORD = "12345"; // KENDİ ŞİFRENİ YAZ

    // 2. Private Constructor (Dışarıdan new ile üretilmesini engeller)
    private DatabaseConnection() {
    }

    // 3. Global Erişim Noktası (Sadece tek bir bağlantı açılmasını garanti eder)
    public static Connection getInstance() {
        try {
            // Eğer bağlantı yoksa veya kapandıysa yeni bir tane aç
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("[LOG] Singleton: Veritabanı bağlantısı başarıyla oluşturuldu.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Veritabanı bağlantı hatası: " + e.getMessage());
        }
        return instance; // Var olan bağlantıyı geri döndür
    }
}