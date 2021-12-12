package blockchain;

import blockchain.helpers.SHA256;

public class Transaction {
    private String data;
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
        this.hash = SHA256.generateHash(this.data);
        System.out.println(this.hash);
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "data='" + data + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
