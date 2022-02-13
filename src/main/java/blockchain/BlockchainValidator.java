package blockchain;

import blockchain.helpers.BlockchainTraverse;
import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import node.TransactionProcess.TransactionProcess;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockchainValidator {

    public static boolean validate(Blockchain blockchain, MemPool memPool, AbstractTransaction transaction) {
        List<AbstractTransaction> blockchainTransactions = Stream.concat(blockchain.getFlattenBlockchain().stream(), memPool.getMemPoolTransactions().stream())
                .collect(Collectors.toList());

        Company company = BlockchainTraverse.getCompanyWithID(transaction.getCompanyID(), blockchainTransactions);
        TransactionProcess process = BlockchainTraverse.getProperTransactionProcess(transaction);
        return process.validate(transaction, company);
    }
}
