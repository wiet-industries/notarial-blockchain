package logic;

import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.TransactionFactory;
import logic.Transactions.Utilities.Priority;
import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        long i = 1337;
        AbstractTransaction transaction = TransactionFactory.getSharesLiquidationTransaction(i, new Date(), 2, "Masty Ben", TransactionType.SharesLiquidation,
                "GIT", Priority.HIGH, 10, "owner1");
        System.out.println(transaction);

        AbstractTransaction t1 = TransactionFactory.getSellBuySharesTransaction(i, new Date(),2,"Ja",
                TransactionType.SharesBuySell, "GIT", Priority.HIGH, "Adam", "Tobys", 5);
        System.out.println(t1);
    }
}
