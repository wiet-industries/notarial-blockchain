package blockchain;

import blockchain.helpers.SHA256;

public class Transaction {
    private String transactionJson;
    private String hash;
    //TODO klucze ogarnąć

//    {
//        value: {
//            transactionJson value goes here
//        }
//        hash: hash ? <- use calculateHash() to calculate hash
//    }

//    IN THIS MODEL WE NEED TO REMOVE HASH FROM LOGIC TRANSACTIONS

    public void calculateHash() {
        this.hash = SHA256.generateHash(this.transactionJson);
        System.out.println(this.hash);
    }

    public void setTransactionJson(String transactionJson) {
        this.transactionJson = transactionJson;
    }
}
