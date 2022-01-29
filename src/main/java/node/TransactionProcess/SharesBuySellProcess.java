package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.SellBuyShares;

public class SharesBuySellProcess implements TransactionProcess{
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        SellBuyShares sellBuyShares = (SellBuyShares) transaction;
        company.transferShareBetween(sellBuyShares.getSeller(), sellBuyShares.getBuyer(), sellBuyShares.getNumberOfShares());
    }
}
