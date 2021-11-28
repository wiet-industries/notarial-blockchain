package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;
import main.logic.Transactions.Utilities.Voting;

public class VotingResults extends AbstractTransaction {
    private Voting voting;

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
