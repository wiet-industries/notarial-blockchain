package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.DividendsPayment;

public class DividendsPaymentProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        DividendsPayment dividendsPayment = (DividendsPayment) transaction;
        company.updateDividend(dividendsPayment.getReceiver(), dividendsPayment.getNumberOfMoneyPayed());
        company.updateEarnings((-1) * dividendsPayment.getNumberOfMoneyPayed());
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        return true;
    }
}
