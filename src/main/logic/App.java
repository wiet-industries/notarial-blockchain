package main.logic;

import main.logic.Transactions.ConcreteTransactions.AbstractTransaction;
import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.TransactionFactory;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        long i = 1337;
        AbstractTransaction transaction = TransactionFactory.getSharesLiquidationTransaction(i, new Date(), 2, "Masty Ben", TransactionType.SharesLiquidation,
                "GIT", Priority.HIGH, 10, "owner1");
        System.out.println(transaction);
    }
}
