package blockchain;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Block {
    private List<Transaction> transactions = new LinkedList<>();
    private String hash;
    private String previousHash;
    private Date creationDate;

    //TODO Make adapter
    private  Gson parser = new Gson();


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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String transactionsToJson() {
        return this.parser.toJson(this.transactions);
    }
}
