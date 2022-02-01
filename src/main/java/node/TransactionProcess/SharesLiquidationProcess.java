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
        SharesLiquidation sharesLiquidation = (SharesLiquidation) transaction;
        return company != null &&
                sharesLiquidation.getNumberOfSharesToLiquidate() > 0 &&
                checkIfShareHolderExist(company, sharesLiquidation.getOwner()) &&
                checkIfShareHolderHasEnoughShares(company, sharesLiquidation.getOwner(), sharesLiquidation.getNumberOfSharesToLiquidate());
    }

    private boolean checkIfShareHolderExist(Company company, String shareHolder) {
        return company.getShares().containsKey(shareHolder);
    }

    private boolean checkIfShareHolderHasEnoughShares(Company company, String shareHolder, int shares) {
        return company.getShares().get(shareHolder) >= shares;
    }
}
