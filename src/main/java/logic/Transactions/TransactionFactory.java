package logic.Transactions;


import logic.Transactions.ConcreteTransactions.*;
import logic.Transactions.Utilities.TransactionType;
import logic.Transactions.Utilities.Voting;

import java.util.Date;
import java.util.Map;

public class TransactionFactory {
    public static AbstractTransaction getSharesLiquidationTransaction(Date transactionDate,
                                                                      int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status,
                                                                      int priority, int numberOfSharesToLiquidate,
                                                                      String owner, String hash) {

        return new SharesLiquidation(transactionDate, companyID, transactionAuthor,
                transactionType, status, priority, numberOfSharesToLiquidate, owner, hash);
    }

    public static AbstractTransaction getAddCompanyTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                               TransactionType transactionType, String status, int priority, String companyName, int companyValue, int companyAccount, int shareValue, Map<String, Integer> distributedShares, String hash) {
        return new AddCompany(transactionDate, companyID, transactionAuthor, transactionType, status,
                priority, companyName, companyValue, companyAccount, shareValue, distributedShares, hash);
    }

    public static AbstractTransaction getCompanyAccountUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                         TransactionType transactionType, String status, int priority, int valueToAdd, String hash) {
        return new CompanyAccountUpdate(transactionDate, companyID, transactionAuthor, transactionType,
                status, priority, valueToAdd, hash);
    }

    public static AbstractTransaction getCompanyValueUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                       TransactionType transactionType, String status, int priority, int valueToAdd, String hash) {
        return new CompanyValueUpdate(transactionDate, companyID, transactionAuthor, transactionType, status, priority, valueToAdd, hash);
    }

    public static AbstractTransaction getDividendsPaymentTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                     TransactionType transactionType, String status, int priority, int numberOfMoneyPayed, String receiver, String hash) {
        return new DividendsPayment(transactionDate, companyID, transactionAuthor, transactionType, status, priority, numberOfMoneyPayed, receiver, hash);
    }

    public static AbstractTransaction getNewSharesEmissionTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status, int priority, String owner, int numberOfEmittedShares, String hash) {
        return new NewSharesEmission(transactionDate, companyID, transactionAuthor, transactionType, status, priority, owner, numberOfEmittedShares, hash);
    }

    public static AbstractTransaction getSellBuySharesTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, String seller, String buyer, int numberOfShares, String hash) {
        return new SellBuyShares(transactionDate, companyID, transactionAuthor, transactionType, status, priority, seller, buyer, numberOfShares, hash);
    }

    public static AbstractTransaction getVotingResultsTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, Voting voting, String hash) {
        return new VotingResults(transactionDate, companyID, transactionAuthor, transactionType, status, priority, voting, hash);
    }
}
