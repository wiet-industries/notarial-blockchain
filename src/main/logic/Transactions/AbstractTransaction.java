package main.logic.Transactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public abstract class AbstractTransaction implements Transaction {
    private long transactionHash;
    private Date transactionDate;
    private int companyID;
    // TODO
    //Consider adding class "Owner" - only company owners can create Transaction in company
    private String transactionAuthor;
    private TransactionType transactionType;
    private String Status;
    private Priority priority;

    @Override
    public long getTransactionHash() { return transactionHash; }
    @Override
    public void setTransactionHash(long transactionHash) {
        this.transactionHash = transactionHash;
    }
    @Override
    public Date getTransactionDate() {
        return transactionDate;
    }
    @Override
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    @Override
    public int getCompanyID() { return companyID; }
    @Override
    public void setCompanyID(int companyID) { this.companyID = companyID; }
    @Override
    public String getTransactionAuthor() { return transactionAuthor; }
    @Override
    public void setTransactionAuthor(String transactionAuthor) { this.transactionAuthor = transactionAuthor; }
    @Override
    public TransactionType getTransactionType() { return transactionType; }
    @Override
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
    @Override
    public Priority getPriority() { return priority; }
    @Override
    public void setPriority(Priority priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "AbstractTransaction{" +
                "transactionHash=" + transactionHash +
                ", transactionDate=" + transactionDate +
                ", companyID=" + companyID +
                ", transactionAuthor='" + transactionAuthor + '\'' +
                ", transactionType=" + transactionType +
                ", priority=" + priority +
                '}';
    }
}
