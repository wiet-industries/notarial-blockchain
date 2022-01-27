package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

public class TransactionProcessContext {
    private TransactionProcess transactionProcess;

    public TransactionProcessContext() {}

    public void setTransactionProcess(TransactionProcess transactionProcess) { this.transactionProcess = transactionProcess; }

    public void process(AbstractTransaction transaction, Company company) {
        this.transactionProcess.process(transaction, company);
    }
}
