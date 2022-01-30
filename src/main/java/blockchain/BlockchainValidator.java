package blockchain;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import node.BlockchainProcessingHandler;

public class BlockchainValidator {

    private static boolean validateAddCompany(Company company, AbstractTransaction abstractTransaction) {

    }


    public static boolean validate(Blockchain blockchain, MemPool memPool, AbstractTransaction transaction) {
        BlockchainProcessingHandler blockchainProcessingHandler = new BlockchainProcessingHandler(blockchain, memPool);

        Company company;
        try {
            company = blockchainProcessingHandler.getCompanyWithID(transaction.getCompanyID());
        } catch (IllegalArgumentException e) {
            return false;
        }


        return false;
    }
}
