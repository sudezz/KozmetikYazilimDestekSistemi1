package ui;

import controller.LoginController;
import org.example.dao.UserDAO;

public class Main {
    public static void main(String[] args) {

        // 1. Model'i (Veritabanı işlemleri) oluştur
        UserDAO model = new UserDAO();

        // 2. View'ı (Görsel Ekran) oluştur
        LoginFrame view = new LoginFrame();

        // 3. Controller'ı oluştur ve Model ile View'ı ona teslim et!
        LoginController controller = new LoginController(view, model);

        // Ekranı görünür yap
        view.setVisible(true);
    }
}