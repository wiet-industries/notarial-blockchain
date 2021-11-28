package main.logic.Transactions.Builders;
import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class SharesLiquidationBuilder implements TransactionBuilder {
    private final SharesLiquidation sharesLiquidation = new SharesLiquidation();

    @Override
    public SharesLiquidationBuilder setTransactionHash(long hash) {
        this.sharesLiquidation.setTransactionHash(hash);
        return this;
    }

    @Override
    public SharesLiquidationBuilder setTransactionDate(Date date) {
        this.sharesLiquidation.setTransactionDate(date);
        return this;
    }

    @Override
    public SharesLiquidationBuilder setCompanyID(int companyID) {
        this.sharesLiquidation.setCompanyID(companyID);
        return this;
    }

    @Override
    public SharesLiquidationBuilder setTransactionAuthor(String transactionAuthor) {
        this.sharesLiquidation.setTransactionAuthor(transactionAuthor);
        return this;
    }
    @Override
    public SharesLiquidationBuilder setTransactionType(TransactionType transactionType) {
        this.sharesLiquidation.setTransactionType(transactionType);
        return this;
    }
    @Override
    public SharesLiquidationBuilder setPriority(Priority priority) {
        this.sharesLiquidation.setPriority(priority);
        return this;
    }

    public SharesLiquidationBuilder setNumberOfSharesToLiquidate(int n) {
        this.sharesLiquidation.setNumberOfSharesToLiquidate(n);
        return this;
    }

    public SharesLiquidationBuilder setOwner(String owner) {
        this.sharesLiquidation.setOwner(owner);
        return this;
    }

    @Override
    public SharesLiquidation getTransaction() {
        return this.sharesLiquidation;
    }
}
