package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class AddNotary extends AbstractTransaction {
    private String notaryIdToAdd;

    public AddNotary(Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String notarialID, int priority, String hash, String verification, String notaryIdToAdd) {
        super(transactionDate, companyID, transactionAuthor, transactionType, notarialID, priority, hash, verification);
        this.notaryIdToAdd = notaryIdToAdd;
    }

    public String getNotaryIdToAdd() {
        return notaryIdToAdd;
    }

    public void setNotaryIdToAdd(String notaryIdToAdd) {
        this.notaryIdToAdd = notaryIdToAdd;
    }
}
