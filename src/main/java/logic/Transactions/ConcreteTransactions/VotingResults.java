package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;
import logic.Transactions.Utilities.Voting;

import java.util.Date;

public class VotingResults extends AbstractTransaction {
    private Voting voting;

    public VotingResults(Date transactionDate, int companyID, String transactionAuthor,
                         TransactionType transactionType, String status, int priority, Voting voting, String hash, String verification) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority, hash, verification);
        this.voting = voting;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    @Override
    public String toString() {
        return "VotingResults{" +
                "voting=" + voting +
                '}';
    }
}
