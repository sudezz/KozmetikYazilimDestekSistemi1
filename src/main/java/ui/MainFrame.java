package ui;

import org.example.model.User;
import org.example.dao.UserDAO; // DAO'yu ekledik
import ui.panels.KullaniciEklePanel;
import ui.panels.DashboardPanel;
import controller.LoginController; // Controller import edildi

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    private User aktifKullanici;
    private JPanel centerPanel;
    private CardLayout cardLayout;

    private final Color SIDEBAR_COLOR = new Color(65, 34, 52);
    private final Color HOVER_COLOR = new Color(100, 50, 80);
    private final Color BG_COLOR = new Color(245, 245, 250);

    public MainFrame(User kullanici) {
        this.aktifKullanici = kullanici;

        setTitle("Kozmetik Destek Sistemi - Yönetim Paneli");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createSidebarPanel(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        centerPanel.setBackground(BG_COLOR);

        centerPanel.add(new DashboardPanel(aktifKullanici), "AnaSayfa");

        if (aktifKullanici.getRoleType() != null && aktifKullanici.getRoleType().equalsIgnoreCase("IT_UZMANI")) {
            centerPanel.add(new KullaniciEklePanel(), "KullaniciEkle");
            centerPanel.add(createDummyPanel("📋 Tüm Talepler (IT Ekibi İçin) - [Arkadaşın Burayı Kodlayacak]"), "TumTalepler");
            centerPanel.add(createDummyPanel("📊 Sistem Raporları - [Arkadaşın Burayı Kodlayacak]"), "Raporlar");
        } else {
            centerPanel.add(createDummyPanel("📝 Yeni Talep Aç - [Arkadaşın Burayı Kodlayacak]"), "YeniTalep");
            centerPanel.add(createDummyPanel("📂 Sadece Benim Taleplerim - [Arkadaşın Burayı Kodlayacak]"), "Taleplerim");
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 60));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        JLabel lblLogo = new JLabel("  ✨ Kozmetik Destek Masası");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblLogo.setForeground(SIDEBAR_COLOR);

        JLabel lblKullanici = new JLabel("Aktif Kullanıcı: " + aktifKullanici.getFullName() + " | Departman: " + aktifKullanici.getDepartmentName() + "  ");
        lblKullanici.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        header.add(lblLogo, BorderLayout.WEST);
        header.add(lblKullanici, BorderLayout.EAST);
        return header;
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton btnAnaSayfa = createMenuButton("🏠 Ana Sayfa");
        btnAnaSayfa.addActionListener(e -> cardLayout.show(centerPanel, "AnaSayfa"));
        sidebar.add(btnAnaSayfa);

        if (aktifKullanici.getRoleType() != null && aktifKullanici.getRoleType().equalsIgnoreCase("IT_UZMANI")) {
            JButton btnKullaniciEkle = createMenuButton("👥 Yeni Personel Ekle");
            btnKullaniciEkle.addActionListener(e -> cardLayout.show(centerPanel, "KullaniciEkle"));
            sidebar.add(btnKullaniciEkle);

            JButton btnTumTalepler = createMenuButton("📋 Tüm Talepleri Yönet");
            btnTumTalepler.addActionListener(e -> cardLayout.show(centerPanel, "TumTalepler"));
            sidebar.add(btnTumTalepler);

            JButton btnRaporlar = createMenuButton("📊 Sistem Raporları");
            btnRaporlar.addActionListener(e -> cardLayout.show(centerPanel, "Raporlar"));
            sidebar.add(btnRaporlar);
        } else {
            JButton btnYeniTalep = createMenuButton("📝 Yeni Talep Aç");
            btnYeniTalep.addActionListener(e -> cardLayout.show(centerPanel, "YeniTalep"));
            sidebar.add(btnYeniTalep);

            JButton btnTaleplerim = createMenuButton("📂 Taleplerim");
            btnTaleplerim.addActionListener(e -> cardLayout.show(centerPanel, "Taleplerim"));
            sidebar.add(btnTaleplerim);
        }

        sidebar.add(Box.createVerticalGlue());

        JButton btnCikis = createMenuButton("🚪 Sistemden Çıkış");
        btnCikis.addActionListener(e -> {
            this.dispose(); // Mevcut ana ekranı kapat

            // Eğer Controller bize bir çıkış görevi tanımladıysa onu çalıştır:
            if (logoutListener != null) {
                logoutListener.run();
            }
        });
        sidebar.add(btnCikis);

        return sidebar;
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_COLOR);
        btn.setMaximumSize(new Dimension(240, 45));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(HOVER_COLOR); }
            public void mouseExited(MouseEvent e) { btn.setBackground(SIDEBAR_COLOR); }
        });
        return btn;
    }

    private JPanel createDummyPanel(String mesaj) {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(BG_COLOR);
        JLabel lbl = new JLabel(mesaj);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(new Color(150, 150, 150));
        pnl.add(lbl);
        return pnl;
    }
    // 1. Önce değişkeni sınıfın en üstüne (field olarak) ekle:
    private Runnable logoutListener;

    // 2. Sonra bu değişkeni dışarıdan (Controller'dan) set edebilmek için metodu ekle:
    public void setLogoutListener(Runnable listener) {
        this.logoutListener = listener;
    }
}