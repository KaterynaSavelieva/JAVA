package LE_09_03.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String name;
    private final List<Branch> branches = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }


    public List<Branch> getBranches() {
        return branches;
    }
}
