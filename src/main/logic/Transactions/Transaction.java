package main.logic.Transactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public interface Transaction {
    long getTransactionHash();
    void setTransactionHash(long transactionHash);
    Date getTransactionDate();
    void setTransactionDate(Date transactionDate);
    int getCompanyID();
    void setCompanyID(int companyID);
    String getTransactionAuthor();
    void setTransactionAuthor(String transactionAuthor);
    TransactionType getTransactionType();
    void setTransactionType(TransactionType transactionType);
    Priority getPriority();
    void setPriority(Priority priority);
}
