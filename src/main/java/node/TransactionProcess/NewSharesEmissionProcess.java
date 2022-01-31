package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.NewSharesEmission;

public class NewSharesEmissionProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        NewSharesEmission newSharesEmission = (NewSharesEmission) transaction;
        company.updateShares(newSharesEmission.getOwner(), newSharesEmission.getNumberOfEmittedShares());
        company.updateShareValue();
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        NewSharesEmission newSharesEmission = (NewSharesEmission) transaction;
        return company != null && newSharesEmission.getNumberOfEmittedShares() > 0;
    }
}
