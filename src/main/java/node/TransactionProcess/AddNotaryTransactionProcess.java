package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.CompanyValueUpdate;

public class AddNotaryTransactionProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        return;
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        return true;
    }
}
