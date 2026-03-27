package service;

import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    // 1. Senaryo: Geçersiz (içinde @ olmayan) e-posta girilirse hata fırlatmalı
    @Test
    public void testLoginWithInvalidEmail() {
        UserService userService = new UserService();

        // assertThrows: İçerideki kodun bir "Exception" (Hata) fırlatmasını BEKLİYORUZ demek.
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            userService.login("ahmetkozmetik.com", "12345"); // Bilerek @ işareti koymadık
        });

        // Fırlatılan hatanın mesajı, bizim UserService'te yazdığımız kural mesajıyla aynı mı?
        Assertions.assertEquals("Lütfen geçerli bir e-posta adresi giriniz!", exception.getMessage());
    }

    // 2. Senaryo: Şifre boş bırakılırsa hata fırlatmalı
    @Test
    public void testLoginWithEmptyPassword() {
        UserService userService = new UserService();

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            userService.login("ahmet@kozmetik.com", "   "); // Şifre sadece boşluklardan oluşuyor
        });

        Assertions.assertEquals("Şifre boş bırakılamaz!", exception.getMessage());
    }
}