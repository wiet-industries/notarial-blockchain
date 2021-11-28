package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.AddCompany;
import main.logic.Transactions.Transaction;
import main.logic.Transactions.Utilities.DistributedShares;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;
import main.logic.Transactions.Utilities.Voting;

import java.util.Date;
import java.util.List;

public class AddCompanyBuilder implements TransactionBuilder{
    private final AddCompany addCompany = new AddCompany();

    @Override
    public AddCompanyBuilder setTransactionHash(long hash) {
        this.addCompany.setTransactionHash(hash);
        return this;
    }

    @Override
    public AddCompanyBuilder setTransactionDate(Date date) {
        this.addCompany.setTransactionDate(date);
        return this;
    }

    @Override
    public AddCompanyBuilder setCompanyID(int companyID) {
        this.addCompany.setCompanyID(companyID);
        return this;
    }

    @Override
    public AddCompanyBuilder setTransactionAuthor(String author) {
        this.addCompany.setTransactionAuthor(author);
        return this;
    }

    @Override
    public AddCompanyBuilder setTransactionType(TransactionType transactionType) {
        this.addCompany.setTransactionType(transactionType);
        return this;
    }

    @Override
    public AddCompanyBuilder setPriority(Priority priority) {
        this.addCompany.setPriority(priority);
        return this;
    }

    public AddCompanyBuilder setCompanyName(String name) {
        this.addCompany.setCompanyName(name);
        return this;
    }

    public AddCompanyBuilder setNewCompanyID(int id) {
        this.addCompany.setNewCompanyID(id);
        return this;
    }

    public AddCompanyBuilder setCompanyValue(int companyValue) {
        this.addCompany.setCompanyValue(companyValue);
        return this;
    }

    public AddCompanyBuilder setCompanyAccount(int companyAccount) {
        this.addCompany.setCompanyAccount(companyAccount);
        return this;
    }

    public AddCompanyBuilder setShareValue(int shareValue) {
        this.addCompany.setShareValue(shareValue);
        return this;
    }

    public AddCompanyBuilder setDistributedShares(List<DistributedShares> list) {
        this.addCompany.setDistributedShares(list);
        return this;
    }

    public AddCompanyBuilder setVoting(List<Voting> list) {
        this.addCompany.setVotings(list);
        return this;
    }

    @Override
    public AddCompany getTransaction() {
        return this.addCompany;
    }
}
