package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class DividendsPayment extends AbstractTransaction {
    private int numberOfMoneyPayed;
    private String receiver;

    public DividendsPayment(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                            TransactionType transactionType, String status, Priority priority, int numberOfMoneyPayed, String receiver) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
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
