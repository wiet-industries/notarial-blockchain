package node;

import blockchain.*;
import blockchain.helpers.BlockchainTraverse;
import blockchain.helpers.SHA256;
import blockchain.helpers.UnparsedBlock;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import logic.Company;
import logic.TransactionAdapter;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.AddNotary;
import logic.Transactions.ConcreteTransactions.DeleteNotary;
import logic.Transactions.Utilities.TransactionType;
import logic.utils.EnvConfig;
import logic.utils.RSAUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        encryptTransactionBasedOnLastBlock(transaction, this.blockchain.getLastBlockHash());
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

    private void encryptTransactionBasedOnLastBlock(AbstractTransaction transaction, String hash) {
        try {
            String encrypted = RSAUtil.sign(hash, EnvConfig.getPrivateKey());
            transaction.setVerification(encrypted);
        } catch (Exception e) {
            System.err.println("Something went wrong with encrypting transaction verification field");
            e.printStackTrace();
        }
    }

    public void handleBlockchainFromOtherNode(JsonElement unparsedBlockchain) {
        List<Block> blockchain = this.parseJsonElementToBlockList(unparsedBlockchain);

        if (blockchain.size() < this.blockchain.getBlockchain().size()) {
            return;
        }
        for (int i = blockchain.size() - 1; i >= 1; i--) {
            Block b = blockchain.get(i);
            Block c = blockchain.get(i - 1);
//            System.out.println("Siema" + c);
            String currentHash = SHA256.generateHash(c.getDataToHashWithNonce());
//            System.out.println(c.getDataToHashWithNonce());
//            System.out.println(c.nonce + "     " + currentHash + "     " + c.getHash() + "         " + b.getPreviousHash());
            if (!currentHash.equals(b.getPreviousHash())) {
                throw new RuntimeException("Blockchain Invalid");
            }
        }
        this.blockchain.setBlockchain(blockchain);
        this.blockchain.writeBlockchainToFile();
        System.out.println(this.blockchain.getBlockchain());
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
            Block blockToAdd = new Block(parsedTransactions, unparsedBlock.hash, unparsedBlock.previousHash, unparsedBlock.creationDate, unparsedBlock.nonce);
            blockList.add(blockToAdd);
        }
        return blockList;
    }

    public void createBlockchainFromFile() {
        String filename = System.getenv("BLOCKCHAIN_FILE_PATH");
        try {
            String blockchainJson = new String(Files.readAllBytes(Paths.get(filename)));
            this.blockchain.setBlockchain(this.parseJsonElementToBlockList(new JsonParser().parse(blockchainJson)));
        } catch (IOException e) {
        }
    }

    private boolean isBlockChainValid(List<Block> blockchain) {
        return this.isBlockChainLonger(blockchain) &&
                this.isGeminiMatching(blockchain) &&
                this.areTransactionsValid(blockchain);
    }

    private boolean isBlockChainLonger(List<Block> blockchain) {
        return this.blockchain.getBlockchain().size() > blockchain.size();
    }

    private boolean isGeminiMatching(List<Block> blockchain) {
        return blockchain.get(0).equals(this.blockchain.getBlockchain().get(0));
    }

    private boolean areTransactionsValid(List<Block> blockchain) {
        Map<String, String> publicKeys = this.blockchain.getPublicKeysFromGemini();
        Block gemini = blockchain.get(0);
        String prevHash = gemini.hash;

        for (Block block : blockchain) {
            if (block.equals(gemini)) {
                continue;
            }
            for (AbstractTransaction transaction : block.getTransactions()) {
                if (!RSAUtil.verify(transaction.getVerification(), prevHash, publicKeys.get(transaction.getNotarialID()))) {
                    return false;
                }
                updatePublicKeys(transaction, publicKeys);
            }
            prevHash = block.hash;
        }
        return true;
    }

    private void updatePublicKeys(AbstractTransaction transaction, Map<String, String> publicKeys) {
        if (transaction.getTransactionType() == TransactionType.AddNotary) {
            AddNotary addNotary = (AddNotary) transaction;
            publicKeys.put(addNotary.getNotarialID(), addNotary.getPublicKey());
        }
        if (transaction.getTransactionType() == TransactionType.DeleteNotary) {
            DeleteNotary deleteNotary = (DeleteNotary) transaction;
            publicKeys.remove(deleteNotary.getNotarialID());
        }
    }
}
