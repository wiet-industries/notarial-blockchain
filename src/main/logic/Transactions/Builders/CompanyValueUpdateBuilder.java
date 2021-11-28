package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.CompanyValueUpdate;
import main.logic.Transactions.Transaction;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class CompanyValueUpdateBuilder implements TransactionBuilder{
    private final CompanyValueUpdate transaction = new CompanyValueUpdate();

    public CompanyValueUpdateBuilder setValueToAdd(int value){
        this.transaction.setValueToAdd(value);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setTransactionHash(long hash) {
        this.transaction.setTransactionHash(hash);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setTransactionDate(Date date) {
        this.transaction.setTransactionDate(date);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setCompanyID(int companyID) {
        this.transaction.setCompanyID(companyID);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setTransactionAuthor(String author) {
        this.transaction.setTransactionAuthor(author);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setTransactionType(TransactionType transactionType) {
        this.transaction.setTransactionType(transactionType);
        return this;
    }

    @Override
    public CompanyValueUpdateBuilder setPriority(Priority priority) {
        this.transaction.setPriority(priority);
        return this;
    }

    @Override
    public CompanyValueUpdate getTransaction() {
        return transaction;
    }
}
