package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.DividendsPayment;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class DividendsPaymentBuilder implements TransactionBuilder {
    private final DividendsPayment transaction = new DividendsPayment();

    @Override
    public DividendsPaymentBuilder setTransactionHash(long hash) {
        this.transaction.setTransactionHash(hash);
        return this;
    }

    @Override
    public DividendsPaymentBuilder setTransactionDate(Date date) {
        this.transaction.setTransactionDate(date);
        return this;
    }

    @Override
    public DividendsPaymentBuilder setCompanyID(int companyID) {
        this.transaction.setCompanyID(companyID);
        return this;
    }

    @Override
    public DividendsPaymentBuilder setTransactionAuthor(String author) {
        this.transaction.setTransactionAuthor(author);
        return this;
    }

    @Override
    public DividendsPaymentBuilder setTransactionType(TransactionType transactionType) {
        this.transaction.setTransactionType(transactionType);
        return this;
    }

    @Override
    public DividendsPaymentBuilder setPriority(Priority priority) {
        this.transaction.setPriority(priority);
        return this;
    }

    public DividendsPaymentBuilder setNumberOfMoneyPayed(int n) {
        this.transaction.setNumberOfMoneyPayed(n);
        return this;
    }

    public DividendsPaymentBuilder setReceiver(String receiver) {
        this.transaction.setReceiver(receiver);
        return this;
    }

    @Override
    public DividendsPayment getTransaction() {
        return transaction;
    }
}
