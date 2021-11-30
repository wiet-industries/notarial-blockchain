package main.logic.Transactions.ConcreteTransactions;


import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class SharesLiquidation extends AbstractTransaction {

    private int numberOfSharesToLiquidate;
    //Consider using Owner
    private String owner;

    public int getNumberOfSharesToLiquidate() {
        return numberOfSharesToLiquidate;
    }

    public void setNumberOfSharesToLiquidate(int numberOfSharesToLiquidate) {
        this.numberOfSharesToLiquidate = numberOfSharesToLiquidate;
    }

    public SharesLiquidation(long transactionHash, Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String status, Priority priority, int numberOfSharesToLiquidate, String owner) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
        this.numberOfSharesToLiquidate = numberOfSharesToLiquidate;
        this.owner = owner;
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
