package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;

public class NewSharesEmission extends AbstractTransaction {
    private String owner;
    private int numberOfEmitedShares;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumberOfEmitedShares() {
        return numberOfEmitedShares;
    }

    public void setNumberOfEmitedShares(int numberOfEmitedShares) {
        this.numberOfEmitedShares = numberOfEmitedShares;
    }

    @Override
    public String toString() {
        return "NewSharesEmission{" +
                "owner='" + owner + '\'' +
                ", numberOfEmitedShares=" + numberOfEmitedShares +
                '}';
    }
}
