package logic;

import logic.Transactions.ConcreteTransactions.AbstractTransaction;

public interface ITransactionAdapter {
    void createTransactionFromJson(String transactionJson) throws Exception;

    AbstractTransaction getTransaction();
}
