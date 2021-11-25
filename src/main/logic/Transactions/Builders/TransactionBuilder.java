package main.logic.Transactions.Builders;

import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public interface TransactionBuilder {
    TransactionBuilder setTransactionHash(long hash);
    TransactionBuilder setTransactionDate(Date date);
    TransactionBuilder setCompanyID(int companyID);
    TransactionBuilder setTransactionAuthor(String author);
    TransactionBuilder setTransactionType(TransactionType transactionType);
    TransactionBuilder setPriority(Priority priority);
    SharesLiquidation getTransaction();
}
