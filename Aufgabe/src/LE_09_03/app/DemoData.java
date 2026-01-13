package LE_09_03.app;

import LE_09_03.management.AccountManagement;
import LE_09_03.model.Branch;
import LE_09_03.model.Company;

public class DemoData {
    public static Company createCompanyWithBranches() {
        Company company = new Company("MyCompany");
        company.addBranch(new Branch("B01", "Zeltweg"));
        company.addBranch(new Branch("B02", "Judenburg"));
        return company;
    }

    public static void initDemoData(AccountManagement manager) {
        manager.createAccount("B01", "1001", 500.00);
        manager.createAccount("B01", "1002", 1200.50);
        manager.createAccount("B02", "2001", 300.00);
        manager.createAccount("B02", "2002", 950.75);
    }
}
