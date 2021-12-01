package logic.Transactions.ConcreteTransactions;


import logic.Transactions.Utilities.Priority;
import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class NewSharesEmission extends AbstractTransaction {
    private String owner;
    private int numberOfEmittedShares;

    public NewSharesEmission(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                             TransactionType transactionType, String status, Priority priority, String owner, int numberOfEmittedShares) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
        this.owner = owner;
        this.numberOfEmittedShares = numberOfEmittedShares;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumberOfEmittedShares() {
        return numberOfEmittedShares;
    }

    public void setNumberOfEmittedShares(int numberOfEmittedShares) {
        this.numberOfEmittedShares = numberOfEmittedShares;
    }

    @Override
    public String toString() {
        return "NewSharesEmission{" +
                "owner='" + owner + '\'' +
                ", numberOfEmitedShares=" + numberOfEmittedShares +
                '}';
    }
}
