package LE_09_03.model;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String branchID;
    private final String branchName;
    private final List<Account> accounts = new ArrayList<>();

    public Branch(String branchID, String branchName) {
        this.branchID = branchID;
        this.branchName = branchName;
    }

    public String getBranchID() {
        return branchID;
    }
    public String getBranchName() {
        return branchName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Branch{" + "branchID=" + branchID + ", branchName=" + branchName + '}';
    }

}
