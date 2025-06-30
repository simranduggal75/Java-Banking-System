package UI;

import javax.swing.*;
import dao.AccountDAO;
import model.Account;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private MainUi mainUi;

    public LoginForm(MainUi mainUi) {
        this.mainUi = mainUi;

        setTitle("Account Login");
        setSize(300, 180);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        add(loginBtn);
        add(new JLabel()); // spacer

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                AccountDAO dao = new AccountDAO();
                Account acc = dao.login(email, password);

                if (acc != null) {
                    JOptionPane.showMessageDialog(null,
                            "Welcome, " + acc.getName() + "!\nAccount ID: " + acc.getAccountId() +
                            "\nBalance: ₹" + acc.getBalance());

                    mainUi.showLoggedInOptions(acc);  // ✅ show dashboard buttons
                    dispose();  // close login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });

        setVisible(true);
    }
}
