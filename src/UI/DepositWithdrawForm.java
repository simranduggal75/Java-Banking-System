package UI;

import javax.swing.*;
import dao.AccountDAO;
import java.awt.*;
import java.awt.event.*;

public class DepositWithdrawForm extends JFrame {

    private int accountId;
    private String actionType;

    public DepositWithdrawForm(int accountId, String actionType) {
        this.accountId = accountId;
        this.actionType = actionType;

        setTitle(actionType.equals("deposit") ? "Deposit Money" : "Withdraw Money");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Amount:"));
        JTextField amountField = new JTextField();
        add(amountField);

        JButton actionBtn = new JButton(actionType.equals("deposit") ? "Deposit" : "Withdraw");
        add(actionBtn);

        add(new JLabel()); // spacer

        actionBtn.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            try {
                double amount = Double.parseDouble(amountText);
                AccountDAO dao = new AccountDAO();
                boolean success = false;

                if (actionType.equals("deposit")) {
                    success = dao.deposit(accountId, amount);
                } else {
                    success = dao.withdraw(accountId, amount);
                }

                if (success) {
                    JOptionPane.showMessageDialog(null, "Transaction successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Transaction failed!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid amount.");
            }
        });

        setVisible(true);
    }
}
