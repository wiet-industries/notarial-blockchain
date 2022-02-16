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
                                                                      String owner, String hash, String verification) {

        return new SharesLiquidation(transactionDate, companyID, transactionAuthor,
                transactionType, status, priority, numberOfSharesToLiquidate, owner, hash, verification);
    }

    public static AbstractTransaction getAddCompanyTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                               TransactionType transactionType, String status, int priority, String companyName, int companyValue, int companyAccount, int shareValue, Map<String, Integer> distributedShares, String hash, String verification) {
        return new AddCompany(transactionDate, companyID, transactionAuthor, transactionType, status,
                priority, companyName, companyValue, companyAccount, shareValue, distributedShares, hash, verification);
    }

    public static AbstractTransaction getCompanyAccountUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                         TransactionType transactionType, String status, int priority, int valueToAdd, String hash, String verification) {
        return new CompanyAccountUpdate(transactionDate, companyID, transactionAuthor, transactionType,
                status, priority, valueToAdd, hash, verification);
    }

    public static AbstractTransaction getCompanyValueUpdateTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                       TransactionType transactionType, String status, int priority, int valueToAdd, String hash, String verification) {
        return new CompanyValueUpdate(transactionDate, companyID, transactionAuthor, transactionType, status, priority, valueToAdd, hash, verification);
    }

    public static AbstractTransaction getDividendsPaymentTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                     TransactionType transactionType, String status, int priority, int numberOfMoneyPayed, String receiver, String hash, String verification) {
        return new DividendsPayment(transactionDate, companyID, transactionAuthor, transactionType, status, priority, numberOfMoneyPayed, receiver, hash, verification);
    }

    public static AbstractTransaction getNewSharesEmissionTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status, int priority, String owner, int numberOfEmittedShares, String hash, String verification) {
        return new NewSharesEmission(transactionDate, companyID, transactionAuthor, transactionType, status, priority, owner, numberOfEmittedShares, hash, verification);
    }

    public static AbstractTransaction getSellBuySharesTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, String seller, String buyer, int numberOfShares, String hash, String verification) {
        return new SellBuyShares(transactionDate, companyID, transactionAuthor, transactionType, status, priority, seller, buyer, numberOfShares, hash, verification);
    }

    public static AbstractTransaction getVotingResultsTransaction(Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, int priority, Voting voting, String hash, String verification) {
        return new VotingResults(transactionDate, companyID, transactionAuthor, transactionType, status, priority, voting, hash, verification);
    }
}
