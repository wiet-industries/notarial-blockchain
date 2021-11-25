package main.logic;
import logic.Transactions.ConcreteTransactionBuilderFactory;
import main.logic.Transactions.Builders.SharesLiquidationBuilder;
import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.TransactionsDirector;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        TransactionsDirector director = new main.logic.Transactions.TransactionsDirector();
        SharesLiquidationBuilder builder = ConcreteTransactionBuilderFactory.getSharesLiquidationBuilder();
        director.createSharesLiquidationTransaction(builder,
                1337, new Date(), 2, "Masty Ben", TransactionType.SharesLiquidation,
                Priority.HIGH, 10, "owner1");
        SharesLiquidation transaction = builder.getTransaction();
        System.out.println(transaction.toString());
    }
}
