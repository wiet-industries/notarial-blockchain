package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;

public class SellBuyShares extends AbstractTransaction {
    private String Seller;
    private String Buyer;
    private int numberOfShares;

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    @Override
    public String toString() {
        return "SellBuyShares{" +
                "Seller='" + Seller + '\'' +
                ", Buyer='" + Buyer + '\'' +
                ", numberOfShares=" + numberOfShares +
                '}';
    }
}
