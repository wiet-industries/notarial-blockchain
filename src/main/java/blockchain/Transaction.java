package blockchain;

import blockchain.helpers.SHA256;

public class Transaction {
    public String data;
    public String hash;

    public Transaction(String data, String hash) {
        this.data = data;
        this.hash = hash;
    }

    public Transaction() {
    }
    //TODO klucze ogarnąć

//    {
//        value: {
//            transactionJson value goes here
//        }
//        hash: hash ? <- use calculateHash() to calculate hash
//    }

//    IN THIS MODEL WE NEED TO REMOVE HASH FROM LOGIC TRANSACTIONS


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void calculateHash() {
        this.hash = SHA256.generateHash(this.data);
        System.out.println(this.hash);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "data='" + data + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
