package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class DeleteNotary extends AbstractTransaction {
    private final String notaryIdToDelete;

    public DeleteNotary(Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String notarialID, int priority, String hash, String verification, String notaryIdToDelete) {
        super(transactionDate, companyID, transactionAuthor, transactionType, notarialID, priority, hash, verification);
        this.notaryIdToDelete = notaryIdToDelete;
    }

    public String getNotaryIdToDelete() {
        return notaryIdToDelete;
    }
}
