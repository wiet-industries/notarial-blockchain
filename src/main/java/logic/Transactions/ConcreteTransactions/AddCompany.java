package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;
import java.util.Map;

public class AddCompany extends AbstractTransaction {
    private String companyName;
    private int companyValue;
    private int companyAccount = 0;
    private int shareValue;
    private Map<String, Integer> distributedShares;

    public AddCompany(Date transactionDate, int companyID, String transactionAuthor,
                      TransactionType transactionType, String status, int priority, String companyName,
                      int companyValue, int companyAccount, int shareValue, Map<String, Integer> distributedShares, String hash) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority, hash);
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

    public Map<String, Integer> getDistributedShares() {
        return distributedShares;
    }

    public void setDistributedShares(Map<String, Integer> distributedShares) {
        this.distributedShares = distributedShares;
    }


    @Override
    public String toString() {
        return super.toString() + "AddCompany{" +
                "companyName='" + companyName + '\'' +
                ", companyValue=" + companyValue +
                ", companyAccount=" + companyAccount +
                ", shareValue=" + shareValue +
                ", distributedShares=" + distributedShares +
                '}';
    }
}
