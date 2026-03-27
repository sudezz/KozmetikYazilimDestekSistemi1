package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JTextField txtCalisanNo;
    private JPasswordField txtSifre;
    private JButton btnGiris;

    // Renk Paletimiz (MainFrame ile uyumlu Kozmetik Teması)
    private final Color PRIMARY_COLOR = new Color(65, 34, 52); // Koyu Mürdüm
    private final Color HOVER_COLOR = new Color(100, 50, 80);  // Fare üzerine gelince

    public LoginFrame() {
        setTitle("Kozmetik Destek Sistemi - Kullanıcı Girişi");
        setSize(420, 520); // Biraz daha ferah bir boyut
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // --- 1. ÜST KISIM (Header/Logo Alanı) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(420, 100));
        headerPanel.setLayout(new GridBagLayout());

        // Buraya bir ikon da koyabilirdik ama şimdilik şık bir emoji ve başlık
        JLabel lblBaslik = new JLabel("✨ KOZMETİK DESTEK");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblBaslik.setForeground(Color.WHITE);
        headerPanel.add(lblBaslik);

        // --- 2. ORTA KISIM (Form Alanı) ---
        // GridBagLayout ile elemanları nizamlı bir şekilde dizeceğiz
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(30, 50, 30, 50)); // Kenarlardan ferah boşluk

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Kutular yatayda uzasın
        gbc.insets = new Insets(8, 0, 8, 0); // Elemanlar arası boşluk

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        int fieldWidth = 20; // Kibar bir kutu genişliği

        // -- Alt Başlık --
        JLabel lblAltBaslik = new JLabel("Hesabınıza Giriş Yapın");
        lblAltBaslik.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblAltBaslik.setForeground(PRIMARY_COLOR);
        lblAltBaslik.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; // Ortala
        formPanel.add(lblAltBaslik, gbc);

        // -- E-Posta --
        gbc.gridwidth = 1; gbc.gridy++; // Bir alt satıra geç
        JLabel lblEmail = new JLabel("E-Posta Adresi:");
        lblEmail.setFont(labelFont);
        formPanel.add(lblEmail, gbc);

        gbc.gridy++; // Kutu bir alt satırda
        txtEmail = new JTextField(fieldWidth);
        txtEmail.setPreferredSize(new Dimension(0, 35)); // Kibar bir yükseklik
        formPanel.add(txtEmail, gbc);

        // -- Çalışan Numarası --
        gbc.gridy++;
        JLabel lblCalisanNo = new JLabel("Çalışan Numarası (Örn: KOZ-001):");
        lblCalisanNo.setFont(labelFont);
        formPanel.add(lblCalisanNo, gbc);

        gbc.gridy++;
        txtCalisanNo = new JTextField(fieldWidth);
        txtCalisanNo.setPreferredSize(new Dimension(0, 35));
        formPanel.add(txtCalisanNo, gbc);

        // -- Şifre --
        gbc.gridy++;
        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setFont(labelFont);
        formPanel.add(lblSifre, gbc);

        gbc.gridy++;
        txtSifre = new JPasswordField(fieldWidth);
        txtSifre.setPreferredSize(new Dimension(0, 35));
        formPanel.add(txtSifre, gbc);

        // --- 3. ALT KISIM (Buton Alanı) ---
        btnGiris = new JButton("GİRİŞ YAP");
        btnGiris.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGiris.setBackground(PRIMARY_COLOR);
        btnGiris.setForeground(Color.WHITE);
        btnGiris.setPreferredSize(new Dimension(280, 50)); // Kocaman, şık bir buton
        btnGiris.setFocusPainted(false);
        btnGiris.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover Efekti (Fare üzerine gelince parlasın)
        btnGiris.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGiris.setBackground(HOVER_COLOR); }
            public void mouseExited(MouseEvent e) { btnGiris.setBackground(PRIMARY_COLOR); }
        });

        // Butonu formPanel'e ekle (Biraz daha boşlukla)
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 10, 0);
        formPanel.add(btnGiris, gbc);

        // Panelleri Pencereye Ekleme
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    // Controller buradaki isimlerle veri çekiyor (Artık her şey uyumlu)
    public String getEmail() { return txtEmail.getText(); }
    public String getCalisanNo() { return txtCalisanNo.getText(); }
    public String getSifre() { return new String(txtSifre.getPassword()); }

    public void addGirisListener(ActionListener listenForGirisButton) { btnGiris.addActionListener(listenForGirisButton); }
    public void mesajGoster(String mesaj) { JOptionPane.showMessageDialog(this, mesaj); }
}