package blockchain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import node.EventListener;
import node.Model.Event;
import node.Model.Message;
import node.Model.MessageType;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    //TODO consider Thread safe queue and add DataBase
    private final List<Block> blockchain = new ArrayList<>();
    //TODO this eventListener maybe je**** method
    private EventListener listener;

    public Blockchain() {
        createFirstBlock();
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public void subscribe(EventListener eventListener) {
        this.listener = eventListener;
    }

    public void unsubscribe(EventListener eventListener) {
        this.listener = null;
    }

    private void notify(Event event) {
        this.listener.update(event);
    }

    private void createFirstBlock() {
        // TODO add parameters to first block
        Block gemin = new Block();
        gemin.setHash("1");
        gemin.setPreviousHash(null);
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

    public JsonElement getBlockchainAsJsonElement() {
        return new JsonParser().parse(this.getBlockchainStringJson());
    }

    private String getBlockchainStringJson() {
        return new Gson().toJson(this.blockchain);
    }
}
