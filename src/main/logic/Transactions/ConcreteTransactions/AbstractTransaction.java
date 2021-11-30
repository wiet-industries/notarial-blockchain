package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public abstract class AbstractTransaction {
    private long transactionHash;
    private Date transactionDate;
    private int companyID;
    // TODO
    //Consider adding class "Owner" - only company owners can create Transaction in company
    private String transactionAuthor;
    private TransactionType transactionType;
    private String Status;
    private Priority priority;


    public AbstractTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String status, Priority priority) {
        this.transactionHash = transactionHash;
        this.transactionDate = transactionDate;
        this.companyID = companyID;
        this.transactionAuthor = transactionAuthor;
        this.transactionType = transactionType;
        this.Status = status;
        this.priority = priority;
    }

    public long getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(long transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getTransactionAuthor() {
        return transactionAuthor;
    }

    public void setTransactionAuthor(String transactionAuthor) {
        this.transactionAuthor = transactionAuthor;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

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
