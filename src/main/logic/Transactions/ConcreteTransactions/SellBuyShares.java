package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class SellBuyShares extends AbstractTransaction {
    private String Seller;
    private String Buyer;
    private int numberOfShares;

    public SellBuyShares(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                         TransactionType transactionType, String status, Priority priority, String seller, String buyer, int numberOfShares) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
        this.Seller = seller;
        this.Buyer = buyer;
        this.numberOfShares = numberOfShares;
    }

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
