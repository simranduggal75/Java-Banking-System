package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import utils.DBConnection;

public class TransactionDAO {

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY txn_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction txn = new Transaction(
                        rs.getInt("txn_id"),
                        rs.getDouble("amount"),
                        rs.getString("txn_type"),
                        rs.getTimestamp("txn_date")
                );
                transactions.add(txn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}

