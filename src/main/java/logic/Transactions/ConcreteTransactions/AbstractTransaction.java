package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractTransaction implements Comparable {
    public String hash;
    private Date transactionDate;
    private int companyID;
    private String transactionAuthor;
    private TransactionType transactionType;
    private String notaryID;
    private String verification;
    private int priority;

    public AbstractTransaction(Date transactionDate, int companyID, String transactionAuthor, TransactionType transactionType, String notaryID, int priority, String hash, String verification) {
        this.transactionDate = transactionDate;
        this.companyID = companyID;
        this.transactionAuthor = transactionAuthor;
        this.transactionType = transactionType;
        this.notaryID = notaryID;
        this.priority = priority;
        this.hash = hash;
        this.verification = verification;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
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

    public String getNotarialID() {
        return notaryID;
    }

    public void setNotarialID(String notarialID) {
        this.notaryID = notarialID;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHashData() {
        return this.getCompanyID() + this.getTransactionAuthor() + this.getPriority() + this.getNotarialID() + this.getTransactionType() + this.getTransactionDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTransaction that = (AbstractTransaction) o;
        return companyID == that.companyID && priority == that.priority && Objects.equals(hash, that.hash) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(transactionAuthor, that.transactionAuthor) && transactionType == that.transactionType && Objects.equals(notaryID, that.notaryID) && Objects.equals(verification, that.verification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, transactionDate, companyID, transactionAuthor, transactionType, notaryID, verification, priority);
    }

    @Override
    public String toString() {
        return "AbstractTransaction{" + hash +
                ", transactionDate=" + transactionDate +
                ", companyID=" + companyID +
                ", transactionAuthor='" + transactionAuthor + '\'' +
                ", transactionType=" + transactionType +
                ", priority=" + priority +
                ", notary id=" + notaryID +

                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.transactionDate.compareTo(((AbstractTransaction) o).getTransactionDate());
    }
}
