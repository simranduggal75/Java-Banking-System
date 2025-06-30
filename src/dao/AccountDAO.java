package dao;


import model.Account;
import model.Transaction;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean createAccount(Account acc) {
        String sql = "INSERT INTO accounts (name, email, balance,password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, acc.getName());
            stmt.setString(2, acc.getEmail());
            stmt.setDouble(3, acc.getBalance());
            stmt.setString(4, acc.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deposit(int accountId, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);

            int updated = stmt.executeUpdate();
            if (updated > 0) {
                logTransaction(accountId, "deposit", amount); // ✅ log after success
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean withdraw(int accountId, double amount) {
        String checkSql = "SELECT balance FROM accounts WHERE account_id = ?";
        String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, accountId);
            var rs = checkStmt.executeQuery();

            if (rs.next() && rs.getDouble("balance") >= amount) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setDouble(1, amount);
                    updateStmt.setInt(2, accountId);

                    int updated = updateStmt.executeUpdate();
                    if (updated > 0) {
                        logTransaction(accountId, "withdraw", amount); // ✅ log after success
                        return true;
                    }
                }
            } else {
                System.out.println("Insufficient balance or invalid account.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account login(String email, String password) {
        String sql = "SELECT * FROM accounts WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("balance"),
                    rs.getInt("account_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void logTransaction(int accountId, String txnType, double amount) {
        String sql = "INSERT INTO transactions (account_id, amount, txn_type) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            stmt.setDouble(2, amount);
            stmt.setString(3, txnType); // "deposit" or "withdraw"
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionHistory(int accountId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT txn_id, txn_type, amount, txn_date FROM transactions WHERE account_id = ? ORDER BY txn_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int txnId = rs.getInt("txn_id");
                String type = rs.getString("txn_type");
                double amount = rs.getDouble("amount");
                java.sql.Timestamp date = rs.getTimestamp("txn_date");

                list.add(new Transaction(txnId, amount, type, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

