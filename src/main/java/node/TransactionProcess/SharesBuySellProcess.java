package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.SellBuyShares;

public class SharesBuySellProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        SellBuyShares sellBuyShares = (SellBuyShares) transaction;
        company.transferShareBetween(sellBuyShares.getSeller(), sellBuyShares.getBuyer(), sellBuyShares.getNumberOfShares());
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        SellBuyShares sellBuyShares = (SellBuyShares) transaction;
        return company != null &&
                sellBuyShares.getNumberOfShares() > 0 &&
                checkIfShareHolderExist(company, sellBuyShares.getSeller()) &&
                checkIfShareHolderHasEnoughShares(company, sellBuyShares.getSeller(), sellBuyShares.getNumberOfShares());

    }

    private boolean checkIfShareHolderExist(Company company, String shareHolder) {
        return company.getShares().containsKey(shareHolder);
    }

    private boolean checkIfShareHolderHasEnoughShares(Company company, String shareHolder, int shares) {
        return company.getShares().get(shareHolder) >= shares;
    }
}
