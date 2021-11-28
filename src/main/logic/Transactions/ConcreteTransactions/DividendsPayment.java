package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;

public class DividendsPayment extends AbstractTransaction {
    private int numberOfMoneyPayed;
    private String receiver;

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
