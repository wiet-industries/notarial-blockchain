package logic.Transactions;


import logic.Transactions.ConcreteTransactions.*;
import logic.Transactions.Utilities.DistributedShares;
import logic.Transactions.Utilities.TransactionType;
import logic.Transactions.Utilities.Voting;

import java.util.Date;
import java.util.List;

public class TransactionFactory {
    public static AbstractTransaction getSharesLiquidationTransaction(Date transactionDate,
                                                                      int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status,
                                                                      int priority, int numberOfSharesToLiquidate,
                                                                      String owner) {

        return new SharesLiquidation(transactionDate, companyID, transactionAuthor,
                transactionType, status, priority, numberOfSharesToLiquidate, owner);
    }

    public static AbstractTransaction getAddCompanyTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                               TransactionType transactionType, String status, int priority, String companyName, int companyValue, int companyAccount, int shareValue, List<DistributedShares> distributedShares) {
        return new AddCompany(transactionDate, companyID, transactionAuthor, transactionType, status,
                priority, companyName, companyValue, companyAccount, shareValue, distributedShares);
    }

    public static AbstractTransaction getCompanyAccountUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                         TransactionType transactionType, String status, int priority, int valueToAdd) {
        return new CompanyAccountUpdate(transactionDate, companyID, transactionAuthor, transactionType,
                status, priority, valueToAdd);
    }

    public static AbstractTransaction getCompanyValueUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                       TransactionType transactionType, String status, int priority, int valueToAdd) {
        return new CompanyValueUpdate(transactionDate, companyID, transactionAuthor, transactionType, status, priority, valueToAdd);
    }

    public static AbstractTransaction getDividendsPaymentTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                     TransactionType transactionType, String status, int priority, int numberOfMoneyPayed, String receiver) {
        return new DividendsPayment(transactionDate, companyID, transactionAuthor, transactionType, status, priority, numberOfMoneyPayed, receiver);
    }

    public static AbstractTransaction getNewSharesEmissionTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status, int priority, String owner, int numberOfEmittedShares) {
        return new NewSharesEmission(transactionDate, companyID, transactionAuthor, transactionType, status, priority, owner, numberOfEmittedShares);
    }

    public static AbstractTransaction getSellBuySharesTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, String seller, String buyer, int numberOfShares) {
        return new SellBuyShares(transactionDate, companyID, transactionAuthor, transactionType, status, priority, seller, buyer, numberOfShares);
    }

    public static AbstractTransaction getVotingResultsTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, Voting voting) {
        return new VotingResults(transactionDate, companyID, transactionAuthor, transactionType, status, priority, voting);
    }
}
