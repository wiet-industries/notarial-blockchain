package logic;

import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.SellBuyShares;
import logic.Transactions.TransactionFactory;
import logic.Transactions.Utilities.Priority;
import logic.Transactions.Utilities.TransactionType;
import com.google.gson.Gson;

import java.lang.reflect.Type;
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


        Gson parser = new Gson();
        String serializedTransaction = parser.toJson(t1);
        System.out.println(serializedTransaction);
        System.out.println("DUPA1");
        AbstractTransaction t2 = parser.fromJson(serializedTransaction, SellBuyShares.class);
        System.out.println("DUPA2");
        System.out.println(t2);
    }
}
