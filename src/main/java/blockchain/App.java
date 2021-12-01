package blockchain;


public class App {
    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        transaction.setTransactionJson("AAA");
        transaction.calculateHash();
        System.out.println(transaction);
    }
}
