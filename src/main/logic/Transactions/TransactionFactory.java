package main.logic.Transactions;

import main.logic.Transactions.ConcreteTransactions.*;
import main.logic.Transactions.Utilities.DistributedShares;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;
import main.logic.Transactions.Utilities.Voting;

import java.util.Date;
import java.util.List;

public class TransactionFactory {
    public static AbstractTransaction getSharesLiquidationTransaction(long transactionHash, Date transactionDate,
                                                                    int companyID, String transactionAuthor,
                                                                    TransactionType transactionType, String status,
                                                                    Priority priority, int numberOfSharesToLiquidate,
                                                                    String owner) {

        return new SharesLiquidation(transactionHash, transactionDate, companyID, transactionAuthor,
                transactionType, status, priority, numberOfSharesToLiquidate, owner);
    }

    public static AbstractTransaction getAddCompanyTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                  TransactionType transactionType, String status, Priority priority, String companyName,
                                                  int newCompanyID, int companyValue, int companyAccount, int shareValue, List<DistributedShares> distributedShares,
                                                  List<Voting> votings) {
        return new AddCompany(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status,
                priority, companyName, newCompanyID, companyValue, companyAccount, shareValue, distributedShares, votings);
    }

    public static AbstractTransaction getCompanyAccountUpdateTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                                          TransactionType transactionType, String status, Priority priority, int valueToAdd) {
        return new CompanyAccountUpdate(transactionHash, transactionDate, companyID, transactionAuthor, transactionType,
                status, priority, valueToAdd);
    }

    public static AbstractTransaction getCompanyValueUpdateTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                                      TransactionType transactionType, String status, Priority priority, int valueToAdd) {
        return new CompanyValueUpdate(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority, valueToAdd);
    }

    public static AbstractTransaction getDividendsPaymentTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                                  TransactionType transactionType, String status, Priority priority, int numberOfMoneyPayed, String receiver) {
        return new DividendsPayment(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority, numberOfMoneyPayed, receiver);
    }

    public static AbstractTransaction getNewSharesEmissionTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                                    TransactionType transactionType, String status, Priority priority, String owner, int numberOfEmittedShares) {
        return new NewSharesEmission(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority, owner, numberOfEmittedShares);
    }

    public static AbstractTransaction getSellBuySharesTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                            TransactionType transactionType, String status, Priority priority, String seller, String buyer, int numberOfShares) {
        return new SellBuyShares(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority, seller, buyer, numberOfShares);
    }

    public static AbstractTransaction getVotingResultsTransaction(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                                            TransactionType transactionType, String status, Priority priority, Voting voting) {
        return new VotingResults(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority, voting);
    }
}
