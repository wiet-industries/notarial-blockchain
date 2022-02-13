package blockchain;

import blockchain.helpers.BlockchainEventManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.TransactionFactory;
import logic.Transactions.Utilities.TransactionType;
import node.Model.Event;
import node.Model.Message;
import node.Model.MessageType;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Blockchain extends BlockchainEventManager {
    //TODO consider Thread safe queue and add DataBase
    public List<Block> blockchain = new ArrayList<>();

    public Blockchain() {
        createFirstBlock();
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(List<Block> blockchain) {
        this.blockchain = blockchain;
    }

    private void createFirstBlock() {
        // TODO add parameters to first block
        // TODO no to jest TAK OBRZYGANE że to ejst przesada
        List<AbstractTransaction> transactions = new LinkedList<>();
        transactions.add(TransactionFactory.getCompanyAccountUpdateTransaction(new Date(), 3, "Masny Ben", TransactionType.CompanyAccountUpdate, "GIT", 5, 4, "1"));
        Block gemin = new Block();
        gemin.setHash("1");
        gemin.setPreviousHash("-1");
        gemin.setCreationDate(new Date());
        gemin.setTransactions(transactions);
        this.blockchain.add(gemin);
    }

    public Block getNewBlock() {
        return new Block();
    }

    public String getLastBlockHash() {
        return getHead().getHash();
    }

    public void addAndValidateBlock(Block block) {
        // compare previous block hash back to genesis hash
        // maybe this is not necessary
        Block current = block;
        for (int i = blockchain.size() - 1; i >= 0; i--) {
            Block b = blockchain.get(i);
            if (b.getHash().equals(current.getPreviousHash())) {
                current = b;
            } else {
                throw new RuntimeException("Block Invalid");
            }
        }
        this.blockchain.add(block);
        this.writeBlockchainToFile();
        // TODO Make Node send data to other nodes                                     v kinda uselles but we have to send something
        Message blockchainMessage = new Message(MessageType.REQUEST_BROADCAST, null, -1);
        Event event = new Event(blockchainMessage.getData());
        this.notify(event);
    }

    private Block getHead() {
        if (this.blockchain.size() > 0) {
            return this.blockchain.get(this.blockchain.size() - 1);
        } else {
            throw new RuntimeException("No Block's have been added to chain...");
        }
    }

    public List<AbstractTransaction> getFlattenBlockchain() {
        return this.blockchain.stream().flatMap(o -> o.getTransactions().stream()).collect(Collectors.toList());
    }

    public JsonElement getBlockchainAsJsonElement() {
        return new JsonParser().parse(this.getBlockchainStringJson());
    }

    public String getBlockchainStringJson() {
        return new Gson().toJson(this.blockchain);
    }

    public void writeBlockchainToFile(){
        String filename = System.getenv("BLOCKCHAIN_FILE_PATH");
        try {
            File blockchainFile = new File(filename);
            blockchainFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.blockchain);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while saving blockchain to file");
        }
    }

    public void readBlockchainFromFile(){
        String filename = System.getenv("BLOCKCHAIN_FILE_PATH");
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Block[] fileBlockchain = (Block[]) ois.readObject();
            ois.close();
            blockchain = Arrays.asList(fileBlockchain);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error while reading blockchain from file");
        }

    }
}
