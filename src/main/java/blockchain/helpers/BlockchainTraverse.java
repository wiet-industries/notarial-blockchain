package blockchain.helpers;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import node.TransactionProcess.*;

import java.util.List;

public class BlockchainTraverse {
    public static Company getCompanyWithID(int ID, List<AbstractTransaction> transactionList) {
        Company company = new Company(ID);
        boolean companyFound = false;
        TransactionProcessContext transactionProcessContext = new TransactionProcessContext();
        for (AbstractTransaction transaction : transactionList) {
            if (transaction.getCompanyID() == ID) {
                companyFound = true;
                transactionProcessContext.setTransactionProcess(getProperTransactionProcess(transaction));
                transactionProcessContext.process(transaction, company);
            }
        }
        if (!companyFound) {
            return null;
        }
        return company;
    }

    public static TransactionProcess getProperTransactionProcess(AbstractTransaction transaction) {
        switch (transaction.getTransactionType()) {
            case AddCompany:
                return new AddCompanyTransactionProcess();
            case CompanyValueUpdate:
                return new CompanyValueUpdateProcess();
            case NewSharesEmission:
                return new NewSharesEmissionProcess();
            case SharesBuySell:
                return new SharesBuySellProcess();
            case SharesLiquidation:
                return new SharesLiquidationProcess();
            case DividendsPayment:
                return new DividendsPaymentProcess();
            case VotingResults:
                return new VotingResultsProcess();
            case CompanyAccountUpdate:
                return new CompanyAccountUpdateProcess();
            case AddNotary:
                return new AddNotaryTransactionProcess();
            case DeleteNotary:
                return new DeleteNotaryTransactionProcess();
            default:
                throw new IllegalArgumentException("Non supported transaction type");
        }
    }
}
