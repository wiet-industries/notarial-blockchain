package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class SharesLiquidation extends AbstractTransaction {

    private int numberOfSharesToLiquidate;
    //Consider using Owner
    private String owner;

    public SharesLiquidation(Date transactionDate, int companyID, String transactionAuthor,
                             TransactionType transactionType, String status, int priority, int numberOfSharesToLiquidate,
                             String owner, String hash, String verification) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority, hash, verification);
        this.numberOfSharesToLiquidate = numberOfSharesToLiquidate;
        this.owner = owner;
    }

    public int getNumberOfSharesToLiquidate() {
        return numberOfSharesToLiquidate;
    }

    public void setNumberOfSharesToLiquidate(int numberOfSharesToLiquidate) {
        this.numberOfSharesToLiquidate = numberOfSharesToLiquidate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return super.toString() + "SharesLiquidation{" +
                "numberOfSharesToLiquidate=" + numberOfSharesToLiquidate +
                ", owner='" + owner + '\'' +
                '}';
    }

}
