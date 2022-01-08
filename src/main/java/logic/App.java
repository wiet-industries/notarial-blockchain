package logic;

import com.google.gson.Gson;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.SellBuyShares;
import logic.Transactions.TransactionFactory;
import logic.Transactions.Utilities.DistributedShares;
import logic.Transactions.Utilities.TransactionType;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        long i = 1337;
        AbstractTransaction transaction = TransactionFactory.getSharesLiquidationTransaction(new Date(), 2, "Masty Ben", TransactionType.SharesLiquidation,
                "GIT", 5, 10, "owner1");
        System.out.println(transaction);
        List<DistributedShares> shares = new LinkedList<>();
        shares.add(new DistributedShares("Andrzej", 13));
        shares.add(new DistributedShares("Kamil", 53));
        AbstractTransaction t1 = TransactionFactory.getAddCompanyTransaction(new Date(), 2, "Adrian",
                TransactionType.AddCompany, "GIT", 5, "Nazwa", 10, 100, 5, shares);
        System.out.println(t1);


        Gson parser = new Gson();
        String serializedTransaction = parser.toJson(t1);
        System.out.println(serializedTransaction);
        AbstractTransaction t2 = parser.fromJson(
                serializedTransaction, SellBuyShares.class);
        System.out.println(t2);

    }
}
