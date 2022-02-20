package blockchain;

import com.google.gson.Gson;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Block {
    // TODO this should not be public but is due to Gson casting
    public List<AbstractTransaction> transactions;
    public String hash;
    public String previousHash;
    public Date creationDate;
    public long nonce;

    public Block() {
        this.transactions = new LinkedList<>();
    }

    public Block(List<AbstractTransaction> transactions, String hash, String previousHash, Date creationDate) {
        this.transactions = transactions;
        this.hash = hash;
        this.previousHash = previousHash;
        this.creationDate = creationDate;
        this.nonce = 0;
    }

    public Block(List<AbstractTransaction> parsedTransactions, String hash, String previousHash, Date creationDate, long nonce) {
        this.transactions = parsedTransactions;
        this.hash = hash;
        this.previousHash = previousHash;
        this.creationDate = creationDate;
        this.nonce = nonce;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
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

    public String getDataToHash() {
//        return "DUPA" + this.previousHash;
        return this.transactionsToJson() + this.previousHash;
    }

    public String getDataToHashWithNonce() {
        return this.getDataToHash() + this.nonce;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(transactions, block.transactions) && Objects.equals(hash, block.hash) && Objects.equals(previousHash, block.previousHash) && Objects.equals(creationDate, block.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions, hash, previousHash, creationDate);
    }
}
