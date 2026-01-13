package LE_09_03.management;

import LE_09_03.model.Account;
import LE_09_03.model.Branch;
import LE_09_03.model.Company;

public class AccountManagement {

    private final Company company;

    public AccountManagement(Company company) {
        this.company = company;
    }

    public Branch findBranchById (String branchID) {
        if (branchID == null) return null;
        for (Branch branch : company.getBranches()) {
            if (branch.getBranchID().equals(branchID)) return branch;
        }
        return null;
    }

    private Account findAccountInBranch (Branch branch, String accountNumber) {
        if (branch == null|| accountNumber==null|| accountNumber.isBlank()) return null;
        for (Account account : branch.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) return account;
        }
        return null;
    }

    private Account findAccountInCompany (String accountNumber) {
        if (accountNumber == null|| accountNumber.isBlank()) return null;

        for (Branch branch : company.getBranches()) {
            Account account = findAccountInBranch(branch, accountNumber);
            if (account != null) return account;
        }
        return null;
    }


    // Create account
    public boolean createAccount(String branchId, String accountNumber, double startBalance){
        Branch branch = findBranchById(branchId);
        if (branch == null) return false;
        if (findAccountInBranch(branch, accountNumber) != null)  {
            throw new  IllegalArgumentException("Account already exists in this branch.");
        }
        branch.addAccount(new Account(accountNumber, startBalance));
        return true;
    }

    // Delete account
    public boolean deleteAccount(String accountNumber){
        if (accountNumber == null) return false;

        for (Branch branch : company.getBranches()) {
            Account account = findAccountInBranch(branch, accountNumber);
            if (account != null) {
                return branch.removeAccount(account);
            }
        }
        return false;
    }


    //Deposit
    public boolean deposit(String accountNumber, double amount){
        Account account = findAccountInCompany(accountNumber);
        if (account == null) return false;
        account.deposit(amount);
        return true;
    }

    //Withdraw
    public boolean withdraw(String accountNumber, double amount){
        Account account = findAccountInCompany(accountNumber);
        if (account == null) return false;
        return account.withdraw(amount);
    }


    //Get balance
    public Double getBalance(String accountNumber){
        Account account = findAccountInCompany(accountNumber);
        if (account == null) return null;
        return account.getBalance();
    }


    // Show one account
    public String showAccount (String accountNumber){
        Account account=findAccountInCompany(accountNumber);
        if(account==null){
            return "Account number " + accountNumber + " does not exist";
        }
        return account.toString();
    }


    //Show all accounts
    public String showAllAccounts(){
        boolean hasAccounts = false;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "| %-10s | %-18s | %-15s | %12s |%n",
                "BranchId", "BranchName", "Account Number", "Balance"));
        sb.append("-".repeat(66)).append("\n");

        for (Branch branch : company.getBranches()) {
            for(Account account: branch.getAccounts()){
                hasAccounts = true;
                sb.append(String.format("| %-10s | %-18s | %-15s | %12.2f |%n",
                        branch.getBranchID(),
                        branch.getBranchName(),
                        account.getAccountNumber(),
                        account.getBalance()));
            }
        }

        if (!hasAccounts) {
            return "No accounts available";
        }

        return sb.toString();
    }

    public String showAllBranches() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Branches ===\n");

        if (company.getBranches().isEmpty()) {
            sb.append("No branches available.\n");
            return sb.toString();
        }

        for (Branch b : company.getBranches()) {
            sb.append("- ").append(b.getBranchID())
                    .append(" | ").append(b.getBranchName())
                    .append(" (accounts: ").append(b.getAccounts().size()).append(")")
                    .append("\n");
        }
        return sb.toString();
    }


    //Show all accounts
    public String showOnlyAccounts(){
        boolean hasAccounts = false;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "| %-10s | %-18s | %-15s | %n",
                "BranchId", "BranchName", "Account Number"));
        sb.append("-".repeat(53)).append("\n");

        for (Branch branch : company.getBranches()) {
            for(Account account: branch.getAccounts()){
                hasAccounts = true;
                sb.append(String.format("| %-10s | %-18s | %-15s | %n",
                        branch.getBranchID(),
                        branch.getBranchName(),
                        account.getAccountNumber()));
            }
        }

        if (!hasAccounts) {
            return "No accounts available";
        }

        return sb.toString();
    }


}
