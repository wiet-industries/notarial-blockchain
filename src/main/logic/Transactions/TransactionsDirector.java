package main.logic.Transactions;

import main.logic.Transactions.Builders.*;
import main.logic.Transactions.ConcreteTransactions.*;
import main.logic.Transactions.Utilities.DistributedShares;
import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;
import main.logic.Transactions.Utilities.Voting;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    public SharesLiquidation createSharesLiquidationTransaction(long hash, Date date, int companyID, String author,
                                                                TransactionType type, Priority priority,
                                                                int n, String owner) {
        if (type != TransactionType.SharesLiquidation){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SharesLiquidationBuilder builder = ConcreteTransactionBuilderFactory.getSharesLiquidationBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setNumberOfSharesToLiquidate(n)
                .setOwner(owner);

        return builder.getTransaction();
    }
    public AddCompany createAddCompanyTransaction(long hash, Date date, int companyID, String author,
                                                  TransactionType type, Priority priority, String companyName,
                                                  int newCompanyID, int companyValue, int companyAccount,
                                                  int shareValue, List<DistributedShares> distributedShares,
                                                  List<Voting> voting) {
        if (type != TransactionType.AddCompany){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AddCompanyBuilder builder = ConcreteTransactionBuilderFactory.getAddCompanyBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setCompanyName(companyName)
                .setNewCompanyID(newCompanyID)
                .setCompanyValue(companyValue)
                .setCompanyAccount(companyAccount)
                .setShareValue(shareValue)
                .setDistributedShares(distributedShares)
                .setVoting(voting);

        return builder.getTransaction();
    }

    public CompanyAccountUpdate createCompanyAccountUpdateTransaction(long hash, Date date, int companyID, String author,
                                                                   TransactionType type, Priority priority, int valueToAdd) {
        if (type != TransactionType.CompanyAccountUpdate){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CompanyAccountUpdateBuilder builder = ConcreteTransactionBuilderFactory.getCompanyAccountUpdateBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setValueToAdd(valueToAdd);

        return builder.getTransaction();
    }
    public CompanyValueUpdate createCompanyValueUpdateTransaction(long hash, Date date, int companyID, String author,
                                                                 TransactionType type, Priority priority,
                                                                 int valueToAdd) {
        if (type != TransactionType.CompanyValueUpdate){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CompanyValueUpdateBuilder builder = ConcreteTransactionBuilderFactory.getCompanyValueUpdateBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setValueToAdd(valueToAdd);

        return builder.getTransaction();
    }
    public DividendsPayment createDividendsPaymentTransaction(long hash, Date date, int companyID, String author,
                                                               TransactionType type, Priority priority,
                                                               int moneyPaid, String receiver) {
        if (type != TransactionType.DividendsPayment){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DividendsPaymentBuilder builder = ConcreteTransactionBuilderFactory.getDividendsPaymentBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setNumberOfMoneyPayed(moneyPaid)
                .setReceiver(receiver);

        return builder.getTransaction();
    }
    public NewSharesEmission createNewSharesEmissionTransaction(long hash, Date date, int companyID, String author,
                                                                TransactionType type, Priority priority,
                                                                int n, String owner) {
        if (type != TransactionType.NewSharesEmission){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        NewSharesEmissionBuilder builder = ConcreteTransactionBuilderFactory.getNewSharesEmissionBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setNumberOfEmitedShares(n)
                .setOwner(owner);

        return builder.getTransaction();
    }
    public SellBuyShares createSellBuySharesTransaction(long hash, Date date, int companyID, String author,
                                                                TransactionType type, Priority priority,
                                                                int numberOfShares, String seller, String buyer) {
        if (type != TransactionType.SharesBuySell){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SellBuySharesBuilder builder = ConcreteTransactionBuilderFactory.getSellBuySharesBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setNumberOfShares(numberOfShares)
                .setBuyer(buyer)
                .setSeller(seller);

        return builder.getTransaction();
    }
    public VotingResults createVotingResultsTransaction(long hash, Date date, int companyID, String author,
                                                                TransactionType type, Priority priority,
                                                                Voting voting) {
        if (type != TransactionType.VotingResults){
            try {
                throw new Exception("Wrong Transaction Type given");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        VotingResultsBuilder builder = ConcreteTransactionBuilderFactory.getVotingResultsBuilder();

        this.createBasicTransaction(builder, hash, date, companyID, author, type, priority);
        builder.setVoting(voting);

        return builder.getTransaction();
    }
}
