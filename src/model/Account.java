package model;

public class Account {
    private String name;
    private String email;
    private double balance;
    private String password;
    private int accountId; // NEW

    // Constructor for creating account (without ID)
    public Account(String name, String email, double balance, String password) {
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.password = password;
    }

    // Constructor for login/fetch (with ID)
    public Account(String name, String email, double balance, int accountId) {
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.accountId = accountId;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getBalance() { return balance; }
    public String getPassword() { return password; }
    public int getAccountId() { return accountId; }
}
