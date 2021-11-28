package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.NewSharesEmission;
import main.logic.Transactions.ConcreteTransactions.SellBuyShares;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class SellBuySharesBuilder implements TransactionBuilder{
    private final SellBuyShares transaction = new SellBuyShares();

    @Override
    public SellBuySharesBuilder setTransactionHash(long hash) {
        this.transaction.setTransactionHash(hash);
        return this;
    }

    @Override
    public SellBuySharesBuilder setTransactionDate(Date date) {
        this.transaction.setTransactionDate(date);
        return this;
    }

    @Override
    public SellBuySharesBuilder setCompanyID(int companyID) {
        this.transaction.setCompanyID(companyID);
        return this;
    }

    @Override
    public SellBuySharesBuilder setTransactionAuthor(String author) {
        this.transaction.setTransactionAuthor(author);
        return this;
    }

    @Override
    public SellBuySharesBuilder setTransactionType(TransactionType transactionType) {
        this.transaction.setTransactionType(transactionType);
        return this;
    }

    @Override
    public SellBuySharesBuilder setPriority(Priority priority) {
        this.transaction.setPriority(priority);
        return this;
    }

    public SellBuySharesBuilder setSeller(String seller) {
        this.transaction.setSeller(seller);
        return this;
    }

    public SellBuySharesBuilder setBuyer(String buyer) {
        this.transaction.setBuyer(buyer);
        return this;
    }

    public SellBuySharesBuilder setNumberOfShares(int n){
        this.transaction.setNumberOfShares(n);
        return this;
    }

    @Override
    public SellBuyShares getTransaction() {
        return transaction;
    }
}
