package UI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.TransactionDAO;
import model.Transaction;

import java.awt.*;
import java.util.List;

public class TransactionHistoryForm extends JFrame {

    public TransactionHistoryForm(int accountId) {
        setTitle("Transaction History");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Table setup
        String[] columns = {"Transaction ID", "Amount", "Type", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        // Fetch and populate transactions
        TransactionDAO dao = new TransactionDAO();
        List<Transaction> txns = dao.getTransactionsByAccountId(accountId);

        for (Transaction txn : txns) {
        	tableModel.addRow(new Object[]{
        		    txn.getTxnId(),
        		    "₹" + txn.getAmount(),
        		    txn.getTxnType(),
        		    txn.getTxnDate()
        		});

        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
