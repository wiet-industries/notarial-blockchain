package blockchain;

import blockchain.helpers.SHA256;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static blockchain.helpers.Config.BLOCKNONCE;
import static blockchain.helpers.Config.BLOCKSIZE;

public class Miner extends Thread {
    private final MemPool memPool;
    private final Blockchain blockchain;

    public Miner(MemPool memPool, Blockchain blockchain) {
        this.memPool = memPool;
        this.blockchain = blockchain;
    }

    @Override
    public synchronized void run() {
        while(true) {
            try {
                //notify when transaction added to memPool
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (memPool.priorityBlockingQueue.size() >= BLOCKSIZE) {
                createBlockAndApplyToChain();
            }
        }
    }

    private void createBlockAndApplyToChain() {
        Block block = blockchain.getNewBlock();
        block.setCreationDate(new Date());
        // set previous hash with current hash
        block.setPreviousHash(blockchain.getLastBlockHash());
        // set block hash
        List<Transaction> transactionsForBlock = new ArrayList<>();
        IntStream.range(0, BLOCKSIZE).forEach(i -> {
                transactionsForBlock.add(memPool.getTransaction());
        });
        block.setTransactions(transactionsForBlock);
        block.setHash(this.proofOfWork(block));
        this.blockchain.addAndValidateBlock(block);
    }

    private String proofOfWork(Block block) {
        String nonceKey = BLOCKNONCE;
        long nonce = 0;
        boolean nonceFound = false;
        String nonceHash = "";

        // There were more properties in Block at https://github.com/in-the-keyhole/khs-blockchain-java-example/blob/master/src/main/java/simple/chain/Block.java
        String message = block.getCreationDate()  + block.transactionsToJson()
                + block.getPreviousHash();

        // ewentualnie sleep(5000) XD
        while (!nonceFound) {
            nonceHash = SHA256.generateHash(message + nonce);
            nonceFound = nonceHash.substring(0, nonceKey.length()).equals(nonceKey);
            nonce++;
        }
        return nonceHash;
    }

}
