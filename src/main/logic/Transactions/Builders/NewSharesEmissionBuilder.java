package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.DividendsPayment;
import main.logic.Transactions.ConcreteTransactions.NewSharesEmission;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class NewSharesEmissionBuilder implements TransactionBuilder{
    private final NewSharesEmission transaction = new NewSharesEmission();

    @Override
    public NewSharesEmissionBuilder setTransactionHash(long hash) {
        this.transaction.setTransactionHash(hash);
        return this;
    }

    @Override
    public NewSharesEmissionBuilder setTransactionDate(Date date) {
        this.transaction.setTransactionDate(date);
        return this;
    }

    @Override
    public NewSharesEmissionBuilder setCompanyID(int companyID) {
        this.transaction.setCompanyID(companyID);
        return this;
    }

    @Override
    public NewSharesEmissionBuilder setTransactionAuthor(String author) {
        this.transaction.setTransactionAuthor(author);
        return this;
    }

    @Override
    public NewSharesEmissionBuilder setTransactionType(TransactionType transactionType) {
        this.transaction.setTransactionType(transactionType);
        return this;
    }

    @Override
    public NewSharesEmissionBuilder setPriority(Priority priority) {
        this.transaction.setPriority(priority);
        return this;
    }

    public NewSharesEmissionBuilder setNumberOfEmitedShares(int n) {
        this.transaction.setNumberOfEmitedShares(n);
        return this;
    }

    public NewSharesEmissionBuilder setOwner(String owner) {
        this.transaction.setOwner(owner);
        return this;
    }

    @Override
    public NewSharesEmission getTransaction() {
        return transaction;
    }

}
