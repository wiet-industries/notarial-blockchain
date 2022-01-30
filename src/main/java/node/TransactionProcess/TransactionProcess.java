package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

public interface TransactionProcess {
    void process(AbstractTransaction transaction, Company company);

    boolean validate(AbstractTransaction transaction, Company company);
}
