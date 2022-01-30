package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.SharesLiquidation;

public class SharesLiquidationProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        SharesLiquidation sharesLiquidation = (SharesLiquidation) transaction;
        company.updateShares(sharesLiquidation.getOwner(), (-1) * sharesLiquidation.getNumberOfSharesToLiquidate());
        company.updateShareValue();
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        return true;
    }
}
