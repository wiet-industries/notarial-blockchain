package blockchain.helpers;

import com.google.gson.JsonElement;

import java.util.Date;
import java.util.List;

public class UnparsedBlock {
    public List<JsonElement> transactions;
    public String hash;
    public String previousHash;
    public Date creationDate;
    public long nonce;

    @Override
    public String toString() {
        return "UnparsedBlock{" +
                "unparsedTransactions=" + transactions +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
