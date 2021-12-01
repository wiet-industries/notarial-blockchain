package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.Priority;
import logic.Transactions.Utilities.TransactionType;
import logic.Transactions.Utilities.Voting;

import java.util.Date;

public class VotingResults extends AbstractTransaction {
    private Voting voting;

    public VotingResults(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                         TransactionType transactionType, String status, Priority priority, Voting voting) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
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
