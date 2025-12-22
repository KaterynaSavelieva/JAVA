package LE_09_03;

import java.util.ArrayList;
import java.util.List;


public class AccountManagement {

    //  Field
    private final List<Account> accounts=new ArrayList<>();


    // Constructor
    public  AccountManagement(){} // empty constructor


    // Helper method
    private Account findAccount(String accountNumber){
        if (accountNumber==null)
            return null;
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        }
        return null;
    }


    // Create account
    public Account createAccount(String accountNumber, String owner, double startBalance){
        if(findAccount (accountNumber)!=null){
            throw new IllegalArgumentException("Account number" +  accountNumber+ " already exists");
        }
        Account account=new Account(accountNumber, owner, startBalance);
        accounts.add(account);
        return account;
    }

    // Delete account
    public boolean deleteAccount(String accountNumber){
        Account account=findAccount(accountNumber);
        if(account==null){
            return false;
        }
        return accounts.remove(account);
    }


    //Deposit
    public boolean deposit(String accountNumber, double amount){
        Account account=findAccount(accountNumber);
        if(account==null){
            return false;
        }
        account.deposit(amount);
        return true;
    }

    //Withdraw
    public boolean withdraw(String accountNumber, double amount){
        Account account=findAccount(accountNumber);
        if(account==null){
            return false;
        }
        return account.withdraw(amount);
    }


    //Get balance
    public Double getBalance(String accountNumber){
        Account account=findAccount(accountNumber);
        if(account==null){
            return null;
        }
        return account.getBalance();
    }


    // Show one account
    public String showAccount (String accountNumber){
        Account account=findAccount(accountNumber);
        if(account==null){
            return "Account number " + accountNumber + " does not exist";
        }
        return account.toString();
    }


    //Show all accounts
    public String showAllAccounts(){
        if (accounts.isEmpty()) {
            return "No accounts available";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Account numbers: ");
        for (Account account : accounts) {
            sb.append(account).append("\n");
        }
        return sb.toString();
    }

}
