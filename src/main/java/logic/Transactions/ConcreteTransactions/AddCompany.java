package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.DistributedShares;
import logic.Transactions.Utilities.TransactionType;

import java.util.Date;
import java.util.List;

public class AddCompany extends AbstractTransaction {
    private String companyName;
    private int companyValue;
    private int companyAccount = 0;
    private int shareValue;
    private List<DistributedShares> distributedShares;

    public AddCompany(Date transactionDate, int companyID, String transactionAuthor,
                      TransactionType transactionType, String status, int priority, String companyName,
                      int companyValue, int companyAccount, int shareValue, List<DistributedShares> distributedShares) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority);
        this.companyName = companyName;
        this.companyValue = companyValue;
        this.companyAccount = companyAccount;
        this.shareValue = shareValue;
        this.distributedShares = distributedShares;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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


    @Override
    public String toString() {
        return "AddCompany{" +
                "companyName='" + companyName + '\'' +
                ", companyValue=" + companyValue +
                ", companyAccount=" + companyAccount +
                ", shareValue=" + shareValue +
                ", distributedShares=" + distributedShares +
                '}';
    }
}
