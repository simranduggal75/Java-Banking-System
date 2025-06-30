package UI;

import javax.swing.*;
import java.awt.*;
import model.Account;

public class MainUi extends JFrame {

    private JButton btnCreateAccount, btnLogin, btnDeposit, btnWithdraw, btnViewTxns, btnLogout;
    private Account loggedInAccount;

    public MainUi() {
        setTitle("Banking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        // Initialize buttons
        btnCreateAccount = new JButton("Create Account");
        btnLogin = new JButton("Login");
        btnDeposit = new JButton("Deposit");
        btnWithdraw = new JButton("Withdraw");
        btnViewTxns = new JButton("View Transactions");
        btnLogout = new JButton("Logout");

        // Initially add only Create and Login
        add(btnCreateAccount);
        add(btnLogin);

        // Actions
        btnCreateAccount.addActionListener(e -> new CreateAccountForm());

        btnLogin.addActionListener(e -> {
            new LoginForm(this);  // pass MainUi reference to LoginForm
        });

        btnDeposit.addActionListener(e -> {
            new DepositWithdrawForm(loggedInAccount.getAccountId(), "deposit");
        });

        btnWithdraw.addActionListener(e -> {
            new DepositWithdrawForm(loggedInAccount.getAccountId(), "withdraw");
        });

        btnViewTxns.addActionListener(e -> {
            new TransactionHistoryForm(loggedInAccount.getAccountId());
        });

        btnLogout.addActionListener(e -> {
            resetUI();
        });

        setVisible(true);
    }

    // After successful login
    public void showLoggedInOptions(Account acc) {
        this.loggedInAccount = acc;

        remove(btnCreateAccount);
        remove(btnLogin);

        add(btnDeposit);
        add(btnWithdraw);
        add(btnViewTxns);
        add(btnLogout);

        revalidate();
        repaint();
    }

    // After logout
    public void resetUI() {
        remove(btnDeposit);
        remove(btnWithdraw);
        remove(btnViewTxns);
        remove(btnLogout);

        add(btnCreateAccount);
        add(btnLogin);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new MainUi();
    }
}
