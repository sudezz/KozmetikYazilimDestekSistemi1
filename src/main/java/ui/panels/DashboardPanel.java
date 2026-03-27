package ui.panels;

import org.example.model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private User aktifKullanici;

    public DashboardPanel(User kullanici) {
        this.aktifKullanici = kullanici;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 250));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        // 1. ÜST KISIM: Karşılama Mesajı
        add(createWelcomePanel(), BorderLayout.NORTH);

        // 2. ORTA KISIM: Dinamik İstatistik Kartları
        add(createCardsPanel(), BorderLayout.CENTER);

        // 3. ALT KISIM: Bilgilendirme
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JPanel createWelcomePanel() {
        JPanel pnl = new JPanel(new GridLayout(2, 1, 5, 5));
        pnl.setBackground(new Color(245, 245, 250));

        JLabel lblMerhaba = new JLabel("Merhaba, " + aktifKullanici.getFullName() + " 👋");
        lblMerhaba.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblMerhaba.setForeground(new Color(65, 34, 52)); // Mürdüm rengi

        // Rol kontrolü ile alt metni değiştiriyoruz
        String altMetin = "IT_UZMANI".equals(aktifKullanici.getRoleType())
                ? "Kozmetik Destek Sistemine hoş geldin. İşte tüm sistemin bugünkü özeti:"
                : "Hoş geldin. Sadece sana (" + aktifKullanici.getEmployeeNo() + ") ait destek taleplerinin durumu aşağıdadır:";

        JLabel lblAltMetin = new JLabel(altMetin);
        lblAltMetin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAltMetin.setForeground(Color.DARK_GRAY);

        pnl.add(lblMerhaba);
        pnl.add(lblAltMetin);
        return pnl;
    }

    private JPanel createCardsPanel() {
        JPanel pnlCards = new JPanel(new GridLayout(1, 3, 20, 0));
        pnlCards.setBackground(new Color(245, 245, 250));

        // DİKKAT: Buradaki sayılar şimdilik tasarımsal (Dummy).
        // İleride arkadaşların DAO'dan "SELECT COUNT(*) WHERE no=..." ile çekip buraya bağlayabilirler.
        if ("IT_UZMANI".equals(aktifKullanici.getRoleType())) {
            // ADMIN EKRANI (Tüm Sistemi Görür)
            pnlCards.add(createSingleCard("Tüm Açık Talepler", "0", new Color(255, 99, 71)));
            pnlCards.add(createSingleCard("Çözülen Talepler", "0", new Color(46, 139, 87)));
            pnlCards.add(createSingleCard("Aktif Personel", "0", new Color(70, 130, 180)));
        } else {
            // STANDART ÇALIŞAN EKRANI (Sadece Kendi Taleplerini Görür)
            pnlCards.add(createSingleCard("Bekleyen Taleplerim", "0", new Color(255, 165, 0)));
            pnlCards.add(createSingleCard("Çözülen Taleplerim", "0", new Color(46, 139, 87)));
            pnlCards.add(createSingleCard("Sistem Duyuruları", "0", new Color(147, 112, 219)));
        }

        return pnlCards;
    }

    private JPanel createSingleCard(String baslik, String sayi, Color renk) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 1, 1, 1, renk),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblSayi = new JLabel(sayi);
        lblSayi.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblSayi.setForeground(renk);
        lblSayi.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblBaslik = new JLabel(baslik);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBaslik.setForeground(Color.DARK_GRAY);
        lblBaslik.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(lblSayi);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblBaslik);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private JPanel createFooterPanel() {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl.setBackground(new Color(245, 245, 250));

        String ipucu = "IT_UZMANI".equals(aktifKullanici.getRoleType())
                ? "💡 İpucu: Yeni personelleri 'Yeni Personel Ekle' menüsünden sisteme dahil edebilirsiniz."
                : "💡 İpucu: Yeni bir donanım/yazılım sorunu bildirmek için sol menüden 'Yeni Talep Aç' butonunu kullanabilirsiniz.";

        JLabel lblInfo = new JLabel(ipucu);
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblInfo.setForeground(Color.GRAY);

        pnl.add(lblInfo);
        return pnl;
    }
}