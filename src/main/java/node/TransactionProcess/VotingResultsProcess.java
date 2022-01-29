package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

public class VotingResultsProcess implements TransactionProcess{
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        logic.Transactions.ConcreteTransactions.VotingResults votingResults = (logic.Transactions.ConcreteTransactions.VotingResults) transaction;
        company.addVoting(votingResults.getVoting());
    }
}
