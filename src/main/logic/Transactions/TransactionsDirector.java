package main.logic.Transactions;

import main.logic.Transactions.Builders.SharesLiquidationBuilder;
import main.logic.Transactions.Builders.TransactionBuilder;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class TransactionsDirector {

    private void createBasicTransaction(TransactionBuilder builder,
                                        long hash, Date date, int id, String author,
                                        TransactionType type, Priority priority) {
        builder.setTransactionHash(hash)
                .setTransactionDate(date)
                .setCompanyID(id)
                .setTransactionAuthor(author)
                .setTransactionType(type)
                .setPriority(priority);
    }

    public void createSharesLiquidationTransaction(SharesLiquidationBuilder transactionBuilder,
                                                   long hash, Date date, int companyID, String author,
                                                   TransactionType type, Priority priority,
                                                   int n, String owner) {
        if ( type != TransactionType.SharesLiquidation){
            try {
                throw new Exception("While creating SharesLiquidation transaction, type must be SharesLiquidation");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.createBasicTransaction(transactionBuilder, hash, date, companyID, author, type, priority);
        transactionBuilder.setNumberOfSharesToLiquidate(n)
                .setOwner(owner);
    }
}
