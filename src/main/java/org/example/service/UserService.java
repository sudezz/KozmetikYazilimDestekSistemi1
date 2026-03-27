package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

public class UserService {

    // Service katmanı, veri çekmek için DAO katmanını kullanır
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Sisteme giriş yapmaya çalışan kullanıcıyı kontrol eden ana metot (ARTIK 3 PARAMETRE ALIYOR)
    public User login(String email, String employeeNo, String password) throws Exception {

        // 1. İŞ KURALLARI (Business Logic) KONTROLÜ
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("E-posta adresi boş bırakılamaz!");
        }
        if (!email.contains("@")) {
            throw new Exception("Lütfen geçerli bir e-posta adresi giriniz!");
        }

        // YENİ KURAL: Çalışan Numarası Kontrolü
        if (employeeNo == null || employeeNo.trim().isEmpty()) {
            throw new Exception("Çalışan numarası boş bırakılamaz!");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Şifre boş bırakılamaz!");
        }

        // 2. DAO'YA (VERİTABANINA) SORGULAMA
        // Kuralları geçtiyse veritabanına 3 bilgiyi birden yollayarak "Böyle biri var mı?" diye soruyoruz
        User loggedInUser = userDAO.authenticate(email, employeeNo, password);

        // 3. SONUÇ KONTROLÜ
        if (loggedInUser == null) {
            // DAO null döndürdüyse bilgilerden biri veya birkaçı yanlıştır
            throw new Exception("Hatalı giriş! E-posta, Çalışan No veya Şifre yanlış. Lütfen tekrar deneyiniz.");
        }

        // Başarılı giriş logu
        System.out.println("[LOG] Başarılı Giriş: " + loggedInUser.getFullName() + " | Rol: " + loggedInUser.getRoleType());

        return loggedInUser;
    }
}