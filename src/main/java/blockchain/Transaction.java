package blockchain;


public class Transaction {
    public String data;

    public Transaction(String data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "data='" + data + '\'' +
                '}';
    }
}
