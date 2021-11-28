package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.CompanyAccountUpdate;
import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class CompanyAccountUpdateBuilder implements TransactionBuilder{
    private final CompanyAccountUpdate companyAccountUpdate = new CompanyAccountUpdate();

    @Override
    public CompanyAccountUpdateBuilder setTransactionHash(long hash) {
        this.companyAccountUpdate.setTransactionHash(hash);
        return this;
    }

    @Override
    public CompanyAccountUpdateBuilder setTransactionDate(Date date) {
        this.companyAccountUpdate.setTransactionDate(date);
        return this;
    }

    @Override
    public CompanyAccountUpdateBuilder setCompanyID(int companyID) {
        this.companyAccountUpdate.setCompanyID(companyID);
        return this;
    }

    @Override
    public CompanyAccountUpdateBuilder setTransactionAuthor(String author) {
        this.companyAccountUpdate.setTransactionAuthor(author);
        return this;
    }

    @Override
    public CompanyAccountUpdateBuilder setTransactionType(TransactionType transactionType) {
        this.companyAccountUpdate.setTransactionType(transactionType);
        return this;
    }

    @Override
    public CompanyAccountUpdateBuilder setPriority(Priority priority) {
        this.companyAccountUpdate.setPriority(priority);
        return this;
    }

    public CompanyAccountUpdateBuilder setValueToAdd(int value) {
        this.companyAccountUpdate.setValueToAdd(value);
        return this;
    }

    @Override
    public CompanyAccountUpdate getTransaction() {
        return this.companyAccountUpdate;
    }
}
