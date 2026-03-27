package ui;

import org.example.model.Category;
import org.example.model.SupportRequest;
import org.example.model.User;
import org.example.service.SupportRequestService;

import javax.swing.*;
import java.awt.*;

public class TicketCreateUI extends JFrame {

    private JTextField subjectField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryBox;
    private JButton createButton;
    private JTextArea ticketListArea;

    private SupportRequestService service;

    public TicketCreateUI() {
        service = new SupportRequestService();

        setTitle("IT Ticket Sistemi");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        subjectField = new JTextField();
        descriptionArea = new JTextArea(4, 20);
        categoryBox = new JComboBox<>(new String[]{"Yazılım", "Donanım", "Ağ"});
        createButton = new JButton("Ticket Oluştur");

        formPanel.add(new JLabel("Konu:"));
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Kategori:"));
        formPanel.add(categoryBox);

        formPanel.add(new JLabel("Açıklama:"));
        formPanel.add(new JScrollPane(descriptionArea));

        formPanel.add(new JLabel(""));
        formPanel.add(createButton);

        add(formPanel, BorderLayout.NORTH);

        ticketListArea = new JTextArea();
        ticketListArea.setEditable(false);
        add(new JScrollPane(ticketListArea), BorderLayout.CENTER);

        createButton.addActionListener(e -> createTicket());

        setVisible(true);
    }

    private void createTicket() {
        String subject = subjectField.getText().trim();
        String description = descriptionArea.getText().trim();
        String selectedCategory = (String) categoryBox.getSelectedItem();

        if (subject.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Boş alan bırakmayın");
            return;
        }

        User user = new User(1, "Test Kullanıcı", "test@test.com") {};
        Category category = new Category(1, selectedCategory);

        SupportRequest request = service.createRequest(subject, description, user, category);

        ticketListArea.append(
                "ID: " + request.getId() +
                        " | " + request.getSubject() +
                        " | " + request.getCategory().getName() +
                        " | " + request.getStatus().getName() + "\n"
        );

        subjectField.setText("");
        descriptionArea.setText("");
    }
}