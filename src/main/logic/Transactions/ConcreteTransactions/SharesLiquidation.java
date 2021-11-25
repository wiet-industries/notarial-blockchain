package main.logic.Transactions.ConcreteTransactions;


import main.logic.Transactions.AbstractTransaction;

public class SharesLiquidation extends AbstractTransaction {

    private int numberOfSharesToLiquidate;
    //Consider using Owner
    private String owner;

    public int getNumberOfSharesToLiquidate() {
        return numberOfSharesToLiquidate;
    }

    public void setNumberOfSharesToLiquidate(int numberOfSharesToLiquidate) {
        this.numberOfSharesToLiquidate = numberOfSharesToLiquidate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return super.toString() + "SharesLiquidation{" +
                "numberOfSharesToLiquidate=" + numberOfSharesToLiquidate +
                ", owner='" + owner + '\'' +
                '}';
    }
}
