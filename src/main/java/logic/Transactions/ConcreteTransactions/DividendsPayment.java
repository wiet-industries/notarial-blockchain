package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class DividendsPayment extends AbstractTransaction {
    private int numberOfMoneyPayed;
    private String receiver;

    public DividendsPayment(Date transactionDate, int companyID, String transactionAuthor,
                            TransactionType transactionType, String status, int priority, int numberOfMoneyPayed, String receiver, String hash) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority, hash);
        this.numberOfMoneyPayed = numberOfMoneyPayed;
        this.receiver = receiver;
    }

    public int getNumberOfMoneyPayed() {
        return numberOfMoneyPayed;
    }

    public void setNumberOfMoneyPayed(int numberOfMoneyPayed) {
        this.numberOfMoneyPayed = numberOfMoneyPayed;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "DividendsPayment{" +
                "numberOfMoneyPayed=" + numberOfMoneyPayed +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
