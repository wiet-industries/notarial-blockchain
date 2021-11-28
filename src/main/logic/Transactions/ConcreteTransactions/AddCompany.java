package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;
import main.logic.Transactions.Utilities.DistributedShares;
import main.logic.Transactions.Utilities.Voting;

import java.util.LinkedList;
import java.util.List;

public class AddCompany extends AbstractTransaction {
    private String companyName;
    private int newCompanyID;
    private int companyValue;
    private int companyAccount = 0;
    private int shareValue;
    private List<DistributedShares> distributedShares = new LinkedList<>();
    private List<Voting> votings = new LinkedList<>();

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNewCompanyID() {
        return newCompanyID;
    }

    public void setNewCompanyID(int newCompanyID) {
        this.newCompanyID = newCompanyID;
    }

    public int getCompanyValue() {
        return companyValue;
    }

    public void setCompanyValue(int companyValue) {
        this.companyValue = companyValue;
    }

    public int getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(int companyAccount) {
        this.companyAccount = companyAccount;
    }

    public int getShareValue() {
        return shareValue;
    }

    public void setShareValue(int shareValue) {
        this.shareValue = shareValue;
    }

    public List<DistributedShares> getDistributedShares() {
        return distributedShares;
    }

    public void setDistributedShares(List<DistributedShares> distributedShares) {
        this.distributedShares = distributedShares;
    }

    public List<Voting> getVotings() {
        return votings;
    }

    public void setVotings(List<Voting> votings) {
        this.votings = votings;
    }

    @Override
    public String toString() {
        return "AddCompany{" +
                "companyName='" + companyName + '\'' +
                ", newCompanyID=" + newCompanyID +
                ", companyValue=" + companyValue +
                ", companyAccount=" + companyAccount +
                ", shareValue=" + shareValue +
                ", distributedShares=" + distributedShares +
                ", votings=" + votings +
                '}';
    }
}
