package controller;

import org.example.dao.UserDAO;
import ui.LoginFrame;
import ui.MainFrame; // MainFrame'i import ediyoruz
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginFrame loginView;
    private UserDAO userModel;

    public LoginController(LoginFrame view, UserDAO model) {
        this.loginView = view;
        this.userModel = model;

        // Ekrana diyoruz ki: "Giriş butonuna basılırsa bana haber ver!"
        this.loginView.addGirisListener(new GirisListener());
    }

    class GirisListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmail();
            String calisanNo = loginView.getCalisanNo();
            String sifre = loginView.getSifre();

            if (email.isEmpty() || calisanNo.isEmpty() || sifre.isEmpty()) {
                loginView.mesajGoster("Lütfen tüm alanları (E-posta, Çalışan No, Şifre) doldurun!");
                return;
            }

            org.example.model.User aktifKullanici = userModel.authenticate(email, calisanNo, sifre);

            if (aktifKullanici != null) {
                loginView.dispose();

                // ANA EKRANI AÇIYORUZ
                MainFrame anaEkran = new MainFrame(aktifKullanici);

                // --- KRİTİK DÜZELTME: Çıkış yapıldığında Controller'ı yeniden başlat ---
                // Bu kısım MainFrame içindeki çıkış butonuna taptaze bir Controller bağlar.
                anaEkran.setLogoutListener(() -> {
                    LoginFrame yeniLogin = new LoginFrame();
                    new LoginController(yeniLogin, new UserDAO());
                    yeniLogin.setVisible(true);
                });

                anaEkran.setVisible(true);
            } else {
                loginView.mesajGoster("Hatalı giriş! E-posta, numara veya şifre yanlış.");
            }
        }
    }
}