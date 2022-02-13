package node;

import blockchain.*;
import blockchain.helpers.BlockchainTraverse;
import blockchain.helpers.UnparsedBlock;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import logic.Company;
import logic.TransactionAdapter;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockchainProcessingHandler {
    private final Miner miner;
    private final MemPool memPool;
    private final Blockchain blockchain;

    public BlockchainProcessingHandler() {
        //TODO this should work with DATABASE
        this.blockchain = new Blockchain();
        this.memPool = new MemPool();

        //TODO MOVE THIS SOMEWHERE ELSE
        this.miner = new Miner(this.memPool, this.blockchain);
        this.miner.start();
    }

    public BlockchainProcessingHandler(Blockchain blockchain, MemPool memPool) {
        this.blockchain = blockchain;
        this.memPool = memPool;
        this.miner = new Miner(this.memPool, this.blockchain);
        this.miner.start();
    }


    public Blockchain getBlockchain() {
        return blockchain;
    }

    public void addTransactionToMemPool(AbstractTransaction transaction) {
        // VALIDATION HERE
        if (BlockchainValidator.validate(blockchain, memPool, transaction)) {
            System.out.println("VALIDATE RIGHT");
            this.memPool.addTransaction(transaction);
            // TODO does it work?
            synchronized (this.miner) {
                this.miner.notify();
            }
        }


    }

    public void handleBlockchainFromOtherNode(JsonElement unparsedBlockchain) {
        List<Block> blockchain = this.parseJsonElementToBlockList(unparsedBlockchain);

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
        this.blockchain.writeBlockchainToFile();
        System.out.println(this.blockchain.getBlockchain());
        //sprawdzić poprawność otrzymanego i wybrać dłuższy
    }

    public Company getCompanyWithID(int ID) {
        return BlockchainTraverse.getCompanyWithID(ID, this.blockchain.getFlattenBlockchain());
    }

    private List<Block> parseJsonElementToBlockList(JsonElement unparsedBlockchain) {
        UnparsedBlock[] unparsedBlockList = new Gson().fromJson(unparsedBlockchain.toString(), UnparsedBlock[].class);
        List<Block> blockList = new LinkedList<>();
        TransactionAdapter adapter = new TransactionAdapter();
        for (UnparsedBlock unparsedBlock : unparsedBlockList) {
            List<AbstractTransaction> parsedTransactions = new LinkedList<>();
            unparsedBlock.transactions.forEach(transaction -> {
                try {
                    adapter.createTransactionFromJson(transaction.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("Error 77Line BlockchainProcessingHandler");
                    e.printStackTrace();
                }
                parsedTransactions.add(adapter.getTransaction());
            });
            Block blockToAdd = new Block(parsedTransactions, unparsedBlock.hash, unparsedBlock.previousHash, unparsedBlock.creationDate);
            blockList.add(blockToAdd);
        }
        return blockList;
    }

    public void createBlockchainFromFile(){
        String filename = System.getenv("BLOCKCHAIN_FILE_PATH");
        try {
            String blockchainJson = new String(Files.readAllBytes(Paths.get(filename)));
            this.blockchain.setBlockchain(this.parseJsonElementToBlockList(new JsonParser().parse(blockchainJson)));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading blockchain from file");
        }
    }
}
