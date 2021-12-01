package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    //TODO consider Thread safe queue and add DataBase
    private List<Block> blockchain = new ArrayList<>();

    public Blockchain() {
        createFirstBlock();
    }

    private void createFirstBlock() {
        // TODO add parameters to first block
        this.blockchain.add(new Block());
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
    }

    private Block getHead() {
        if (this.blockchain.size() > 0) {
            return this.blockchain.get(this.blockchain.size() - 1);
        } else {
            throw new RuntimeException("No Block's have been added to chain...");
        }
    }
}
