package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.SellBuyShares;
import main.logic.Transactions.ConcreteTransactions.VotingResults;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;
import main.logic.Transactions.Utilities.Voting;

import java.util.Date;

public class VotingResultsBuilder implements TransactionBuilder{
    private final VotingResults transaction = new VotingResults();

    @Override
    public VotingResultsBuilder setTransactionHash(long hash) {
        this.transaction.setTransactionHash(hash);
        return this;
    }

    @Override
    public VotingResultsBuilder setTransactionDate(Date date) {
        this.transaction.setTransactionDate(date);
        return this;
    }

    @Override
    public VotingResultsBuilder setCompanyID(int companyID) {
        this.transaction.setCompanyID(companyID);
        return this;
    }

    @Override
    public VotingResultsBuilder setTransactionAuthor(String author) {
        this.transaction.setTransactionAuthor(author);
        return this;
    }

    @Override
    public VotingResultsBuilder setTransactionType(TransactionType transactionType) {
        this.transaction.setTransactionType(transactionType);
        return this;
    }

    @Override
    public VotingResultsBuilder setPriority(Priority priority) {
        this.transaction.setPriority(priority);
        return this;
    }

    public VotingResultsBuilder setVoting(Voting voting) {
        this.transaction.setVoting(voting);
        return this;
    }


    @Override
    public VotingResults getTransaction() {
        return transaction;
    }
}
