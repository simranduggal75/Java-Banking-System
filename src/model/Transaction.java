package model;

import java.sql.Timestamp;

public class Transaction {
    private int txnId;
    private double amount;
    private String txnType;
    private Timestamp txnDate;

    public Transaction(int txnId, double amount, String txnType, Timestamp txnDate) {
        this.txnId = txnId;
        this.amount = amount;
        this.txnType = txnType;
        this.txnDate = txnDate;
    }

    public int getTxnId() {
        return txnId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTxnType() {
        return txnType;
    }

    public Timestamp getTxnDate() {
        return txnDate;
    }
}
