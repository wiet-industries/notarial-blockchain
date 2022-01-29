package node;

import blockchain.*;
import logic.Company;
import logic.Transactions.ConcreteTransactions.*;
import node.TransactionProcess.*;

import java.util.List;

public class BlockchainProcessingHandler {
    private final Miner miner;
    private final MemPool memPool;
    private final Blockchain blockchain;

    public BlockchainProcessingHandler() {
        //TODO this should work with DATABASE
        this.blockchain = new Blockchain();
        this.memPool = new MemPool();

        this.miner = new Miner(this.memPool, this.blockchain);
        this.miner.start();
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public void addTransactionToMemPool(AbstractTransaction transaction) {
        this.memPool.addTransaction(transaction);
        // TODO does it work?
        synchronized (this.miner) {
            this.miner.notify();
        }
    }

    public void handleBlockchainFromOtherNode(List<Block> blockchain) {
        if (blockchain.size() < this.blockchain.getBlockchain().size()) {
            return;
        }
        Block current = blockchain.get(blockchain.size() - 1);
        for (int i = blockchain.size() - 2; i >= 0; i--) {
            Block b = blockchain.get(i);
            if (b.getHash().equals(current.getPreviousHash())) {
                current = b;
            } else {
                throw new RuntimeException("Blockchain Invalid");
            }
        }
        this.blockchain.setBlockchain(blockchain);
        System.out.println(this.blockchain.getBlockchain());
        //sprawdzić poprawność otrzymanego i wybrać dłuższy
    }

    public Company getCompanyWithID(int ID) throws IllegalArgumentException {
        Company company = new Company(ID);
        TransactionProcessContext transactionProcessContext = new TransactionProcessContext();
        for (Block block : this.blockchain.getBlockchain()) {
            for (AbstractTransaction transaction : block.getTransactions()) {
                if (transaction.getCompanyID() == ID) {
                    // TODO: Check if AddCompany transaction is the first one
                    transactionProcessContext.setTransactionProcess(this.getProperTransactionProcess(transaction));
                    transactionProcessContext.process(transaction, company);
                }
            }
        }
        // TODO: Check if company with given id was found
        return company;
    }

    private TransactionProcess getProperTransactionProcess(AbstractTransaction transaction) {
        switch(transaction.getTransactionType()) {
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
            default:
                throw new IllegalArgumentException("Non supported transaction type");
        }
    }

//   public String getCompanyName(String ID) throws IllegalArgumentException {
//
//    }

}
