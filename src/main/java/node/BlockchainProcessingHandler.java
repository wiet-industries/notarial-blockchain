package node;

import blockchain.*;

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

    public void addTransactionToMemPool(Transaction transaction) {
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

}
