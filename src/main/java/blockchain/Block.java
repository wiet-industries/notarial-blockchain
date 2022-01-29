package blockchain;

import com.google.gson.Gson;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Block {
    // TODO this should not be public but is due to Gson casting
    public List<AbstractTransaction> transactions;
    public String hash;
    public String previousHash;
    public Date creationDate;

    public Block() {
        this.transactions = new LinkedList<>();
    }

    public Block(List<AbstractTransaction> transactions, String hash, String previousHash, Date creationDate) {
        this.transactions = transactions;
        this.hash = hash;
        this.previousHash = previousHash;
        this.creationDate = creationDate;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<AbstractTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AbstractTransaction> transactions) {
        this.transactions = transactions;
    }

    public String transactionsToJson() {
        return new Gson().toJson(this.transactions);
    }

    @Override
    public String toString() {
        return "Block{" +
                "transactions=" + transactions +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
