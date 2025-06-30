package UI;

import javax.swing.*;
import dao.AccountDAO;
import model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField balanceField;
    private JPasswordField passwordField;

    public CreateAccountForm() {
        setTitle("Create New Account");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));  // 5 rows now

        // UI Components
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Initial Balance:"));
        balanceField = new JTextField();
        add(balanceField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton createBtn = new JButton("Create Account");
        add(createBtn);
        add(new JLabel()); // spacer

        // Button Click Action
        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String balanceText = balanceField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (name.isEmpty() || email.isEmpty() || balanceText.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                try {
                    double balance = Double.parseDouble(balanceText);
                    Account acc = new Account(name, email, balance, password);
                    AccountDAO dao = new AccountDAO();

                    if (dao.createAccount(acc)) {
                        JOptionPane.showMessageDialog(null, "Account created successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error creating account.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Balance must be a valid number.");
                }
            }
        });

        setVisible(true);
    }
}
