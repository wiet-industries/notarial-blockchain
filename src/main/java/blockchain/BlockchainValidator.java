package blockchain;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import node.BlockchainProcessingHandler;

public class BlockchainValidator {

    private final Blockchain blockchain;
    private final MemPool memPool;

    public BlockchainValidator(Blockchain blockchain, MemPool memPool) {
        this.blockchain = blockchain;
        this.memPool = memPool;
    }

    private boolean validateAddCompany(Company company, AbstractTransaction abstractTransaction) {

    }


    public boolean checkIfCompanyWasPreviouslyCreated(AbstractTransaction transaction) {
        BlockchainProcessingHandler blockchainProcessingHandler = new BlockchainProcessingHandler(this.blockchain, this.memPool);

        try {
            Company company = blockchainProcessingHandler.getCompanyWithID(transaction.getCompanyID());
        } catch (IllegalArgumentException e) {
            return this.companyCreatedInMempool(transaction)
        }
        return false;
    }
}
