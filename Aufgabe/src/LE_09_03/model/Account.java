package LE_09_03.model;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double startBalance) {
        if (accountNumber == null || accountNumber.isBlank()) { // isBlank() checks whether a string is empty or contains only whitespace characters.
            throw new IllegalArgumentException("Account number must not be empty.");
        }

        if (startBalance < 0) {
            throw new IllegalArgumentException("Start balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = startBalance;
    }

    // ===== Business methods =====

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        /* Логіка:
            неправильні дані → exception
            недостатньо грошей → false
            успіх → true
         */
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than 0.");
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }


    // ===== Getters =====
    public double getBalance() {return balance;}
    public String getAccountNumber() {return accountNumber;}


    @Override
    public String toString() {
        return String.format("Account number: %s, balance: %.2f", accountNumber, balance);
    }



}
