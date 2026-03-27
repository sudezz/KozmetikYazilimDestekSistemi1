package ui.panels;

import org.example.dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KullaniciEklePanel extends JPanel {

    public KullaniciEklePanel() {
        // Formu ekranın tam ortasında, derli toplu tutmak için GridBagLayout kullanıyoruz
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 250)); // Ana ekranla uyumlu açık gri arka plan

        UserDAO dao = new UserDAO();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Elemanlar arası boşluklar (Nefes alma payı)

        // --- 1. BAŞLIK (En üstte, ortalanmış ve 2 sütunu kaplıyor) ---
        JLabel lblBaslik = new JLabel("Yeni Personel Kaydı");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBaslik.setForeground(new Color(65, 34, 52)); // Mürdüm rengi başlık
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; // Başlık 2 sütun genişliğinde olsun
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblBaslik, gbc);

        // --- AYARLAR: Bundan sonraki elemanlar tek sütun olacak ---
        gbc.gridwidth = 1;
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        int fieldWidth = 15; // Kutucukların standart ve kibar boyutu

        // --- 2. AD SOYAD ---
        gbc.gridy = 1; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST; // Yazı sağa dayalı
        JLabel lblAd = new JLabel("Ad Soyad:"); lblAd.setFont(labelFont);
        add(lblAd, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; // Kutu sola dayalı
        JTextField txtAd = new JTextField(fieldWidth);
        add(txtAd, gbc);

        // --- 3. E-POSTA ---
        gbc.gridy = 2; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel lblEmail = new JLabel("E-Posta Adresi:"); lblEmail.setFont(labelFont);
        add(lblEmail, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JTextField txtEmail = new JTextField(fieldWidth);
        add(txtEmail, gbc);

        // --- 4. ÇALIŞAN NUMARASI (Otomatik ve Gri) ---
        gbc.gridy = 3; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel lblNo = new JLabel("Çalışan Numarası:"); lblNo.setFont(labelFont);
        add(lblNo, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JTextField txtNo = new JTextField(fieldWidth);
        txtNo.setText(dao.getYeniCalisanNo());
        txtNo.setEditable(false);
        txtNo.setBackground(new Color(225, 225, 225)); // Kilitli efekti
        add(txtNo, gbc);

        // --- 5. ŞİFRE ---
        gbc.gridy = 4; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel lblSifre = new JLabel("Şifre:"); lblSifre.setFont(labelFont);
        add(lblSifre, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPasswordField txtSifre = new JPasswordField(fieldWidth);
        add(txtSifre, gbc);

        // --- 6. ROL SEÇİMİ ---
        gbc.gridy = 5; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel lblRol = new JLabel("Sistem Yetkisi:"); lblRol.setFont(labelFont);
        add(lblRol, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JComboBox<String> cbRol = new JComboBox<>(new String[]{"STANDARD", "IT_UZMANI"});
        cbRol.setPreferredSize(txtAd.getPreferredSize()); // Kutularla aynı boyda olsun
        cbRol.setBackground(Color.WHITE);
        add(cbRol, gbc);

        // --- 7. DEPARTMAN SEÇİMİ ---
        gbc.gridy = 6; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        JLabel lblDep = new JLabel("Departmanı:"); lblDep.setFont(labelFont);
        add(lblDep, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        List<String> depler = dao.getTumDepartmanlar();
        JComboBox<String> cbDepartman = new JComboBox<>(depler.toArray(new String[0]));
        cbDepartman.setPreferredSize(txtAd.getPreferredSize());
        cbDepartman.setBackground(Color.WHITE);
        add(cbDepartman, gbc);

        // Akıllı Form Mantığı (IT Uzmanı seçilince departmanı kilitler)
        cbRol.addActionListener(e -> {
            if ("IT_UZMANI".equals(cbRol.getSelectedItem())) {
                for (int i = 0; i < cbDepartman.getItemCount(); i++) {
                    if (cbDepartman.getItemAt(i).toUpperCase().contains("YAZILIM") ||
                            cbDepartman.getItemAt(i).toUpperCase().contains("IT")) {
                        cbDepartman.setSelectedIndex(i);
                        break;
                    }
                }
                cbDepartman.setEnabled(false);
            } else {
                cbDepartman.setEnabled(true);
            }
        });

        // --- 8. KAYDET BUTONU ---
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2; // Yine 2 sütunu kaplasın
        gbc.anchor = GridBagConstraints.CENTER; // Ortada dursun
        gbc.insets = new Insets(25, 0, 0, 0); // Üstten biraz daha boşluk bırakalım

        JButton btnKaydet = new JButton("Kullanıcıyı Kaydet");
        btnKaydet.setBackground(new Color(70, 130, 180));
        btnKaydet.setForeground(Color.WHITE);
        btnKaydet.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnKaydet.setPreferredSize(new Dimension(200, 40));
        btnKaydet.setFocusPainted(false);
        btnKaydet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnKaydet, gbc);

        // Buton İşlevi
        btnKaydet.addActionListener(e -> {
            String ad = txtAd.getText();
            String email = txtEmail.getText();
            String no = txtNo.getText();
            String sifre = new String(txtSifre.getPassword());
            String rol = (String) cbRol.getSelectedItem();
            int depId = Integer.parseInt(((String) cbDepartman.getSelectedItem()).split(" - ")[0]);

            if (ad.isEmpty() || email.isEmpty() || no.isEmpty() || sifre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean basarili = dao.kullaniciEkle(ad, email, no, sifre, rol, depId);

            if (basarili) {
                JOptionPane.showMessageDialog(this, "Kullanıcı başarıyla eklendi!\nAtanan No: " + no);
                txtAd.setText(""); txtEmail.setText(""); txtSifre.setText("");
                cbRol.setSelectedIndex(0);
                txtNo.setText(dao.getYeniCalisanNo()); // Sıradaki numarayı al
            } else {
                JOptionPane.showMessageDialog(this, "Kayıt sırasında hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}