package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public abstract class AbstractTransaction implements Comparable {
    public String hash;
    private Date transactionDate;
    private int companyID;
    // TODO
    //Consider adding class "Owner" - only company owners can create Transaction in company
    private String transactionAuthor;
    private TransactionType transactionType;
    private String Status;
    private int priority;

    public AbstractTransaction(Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String status, int priority, String hash) {
        this.transactionDate = transactionDate;
        this.companyID = companyID;
        this.transactionAuthor = transactionAuthor;
        this.transactionType = transactionType;
        this.Status = status;
        this.priority = priority;
        this.hash = hash;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "AbstractTransaction{" + hash +
                ", transactionDate=" + transactionDate +
                ", companyID=" + companyID +
                ", transactionAuthor='" + transactionAuthor + '\'' +
                ", transactionType=" + transactionType +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.transactionDate.compareTo(((AbstractTransaction) o).getTransactionDate());
    }
}
