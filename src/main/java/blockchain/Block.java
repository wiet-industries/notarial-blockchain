package blockchain;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Block {
    // TODO this should not be public but is due to Gson casting
    public List<Transaction> transactions = new LinkedList<>();
    public String hash;
    public String previousHash;
    public Date creationDate;


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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
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
