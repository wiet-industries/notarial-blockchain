package blockchain;

import blockchain.helpers.BlockchainEventManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.AddNotary;
import logic.Transactions.TransactionFactory;
import logic.Transactions.Utilities.TransactionType;
import node.Model.Event;
import node.Model.Message;
import node.Model.MessageType;

import java.io.IOException;
import java.io.PrintWriter;
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
        transactions.add(TransactionFactory.getAddNotaryTransaction(new Date(), -1, "Masny Ben", TransactionType.AddNotary, "-1", -1, "-1", "0", "1",
                "MIICXQIBAAKBgQCuuVaZtL6NIYhaAuGEcppc0u6yXcJHRNgK2AyaZZFW5m7jQzLU" +
                        "ywKmJX/NB+ztRSxAbG+fAXuvwgGSyCq13AknhoAInDBzUxVPI2gNoZCzB9y0DqPW" +
                        "GjmEADKLb0eKLTu+hjAiS0oxVeYg68kcBOERmtefB8k9+xTgW+y9Q7SE0QIDAQAB" +
                        "AoGBAI6mHoALQu9yiyIB7CD4d/KE/AB/79fD6yafUVrUxZ5PMAU3nc6BmD4Rq4+3" +
                        "gsYMjZ5jaZZF3beXCzXNWqFzbbyPwVocnvLsaIO9PA71ERm0DJW481SFkEFiWnjq" +
                        "pxfW+Xlz5+iRPaQLXJnWj5EAXs8dqZRvljYY39Q0i7Wb4eiBAkEA3DeiME9VdsDQ" +
                        "lEuPE1P26mYwPY2Rv4C3SDT0qnlb1yuCZJttmT4f1B25O3P7xcD9zFuk3xHdElfx" +
                        "poKyUKVQyQJBAMsdUixpnIAKH4ynLWBQs6Gl5Gid8GkhDLQuy9ykS4fr2fcvAQgZ" +
                        "sEMEPAArcgYTYbmQJfD5Q0TIZBcMH0HB38kCQBh9N1QX/nEOUCOto2OK2tKGOqcd" +
                        "ry+UqM0m1m5+wG/VLtLxVebMuc1k4MxzwXPkWlT3fAYyflsj3IOf1SSSvcECQG2B" +
                        "WF0zbzmku4+0TgWAXOhIrRUuGYBEXjoChMxnE4WrRW87uz2nw9zNup+IzMezyVsm" +
                        "+R2q9XZu5295jJyCLukCQQC9zr+55YS6BwSKRjuwLMU4DUMspMAOK3nNxL57f6Vz" +
                        "2sfNyWJgtjzB/bRkujYzcBkhhb2fNYttRdwQNk4mMlp9"));
        transactions.add(TransactionFactory.getAddNotaryTransaction(new Date(), -1, "Masny Ben", TransactionType.AddNotary, "-1", -1, "-1", "0", "2",
                "MIICXAIBAAKBgQC4lMJWrsSSrkKCNYLFCoLMzGaMVmkBLOzwtV9WMtjuvb+G4oow" +
                        "F44WAOGxBMKwuuChggRfmMNgqMmfejJiNW6OsGQ3AoZhtY+z6sMp/nFHii67ytxO" +
                        "h24IomJepi48ehfTFCsII0OKIgLx7O2E72pif4xYGHFF1KA6BPV0cNwpQQIDAQAB" +
                        "AoGAcM0gcv79TLLIHHD9SNOimev0Y6FPLJO6/WOqLrCGWGiHirQZRUiv4YyKYLcL" +
                        "EBw0uR8O8ykVhZXBGgAAmHoU5H23DBTfAPjG2NWjZOqRF0WgdC2WHmOofQuZ1YCc" +
                        "bMjPuPhz86yqa7vpbfg2ixLIQrzPpQmf+QJjWVuM3+t6MJECQQDv3XTyuM0n59v4" +
                        "GhyhdlsAacTyB5MSMqA8uVt5fUMt+ZkNsh2RxM6omtJIeBcoU0czYMLbNGO9Rr1y" +
                        "nk7xcmulAkEAxP9L/5Yc4VA2uSA343BoP3JbcrTc1m1k2v8FPiD03cQT0E1I4e3y" +
                        "rbQssmVjzEWcQ/RLX448Dd/iHp7xcLLEbQJAbgXpt9AWooBSwLnReipc0ZKyeMYK" +
                        "quU35MKs98wCPZtongSeVZetp17kp8MV9DRGy4VNu9iLC23gRivhUBTI1QJBAKks" +
                        "uT5X0ptoAGbBFV3blZsPARuj2JbIwTkUkpMFSGP+VCmphTKI3sRvm4bkbWzqRsVQ" +
                        "0T3Rbx9XKd+0DZID440CQGvEv+SAOZBgk4g8Swj0zXTgyC4DwqEjTHD0kWfoA1E0" +
                        "QAvjb2u2/YTDIabp7ybJgkglKfRpAMdQ3ia/HbwAMs0="));
        transactions.add(TransactionFactory.getAddNotaryTransaction(new Date(), -1, "Masny Ben", TransactionType.AddNotary, "-1", -1, "-1", "0", "3",
                "MIICXAIBAAKBgGYeBODNBcRyzfLwzLZSjfIXt1t7UcJ5Fohio9fLe+KKy3y1HUFq" +
                        "3msX9ejDYOLZgjv2RtccjZhbhxuRB9qxhE6ArlP611QSZ8H0jNz6jrc3V9VZRvvH" +
                        "+pv9QV9yGJHd2VpnTsW5V1YgDjheX0/24FNA8rMixd3WhB49X6yoAXaRAgMBAAEC" +
                        "gYA0FHtIa0fVuPdwP38oPVJHdfD9pxc7p6XmSOhm1lCIaWD+aSBSjL6Cv+WaoIp5" +
                        "J5VUH3Zhnx5X5dYyKPVog8CF4FfpIo/RdfI0hVPeJ/8GcUhTZNabJ1uUWjeO5TIB" +
                        "tPE2+8D0HlrY7XhOK6ypvqRZuhksMui0XaoluGvCD6H3AQJBAMfXGwxhgsXCWvJn" +
                        "GKeyDdgm9WFr8P6xkFqdhwA9H79TUVECzgetK9t1S5QObQY2dCr3oZ6N16RKmmHb" +
                        "DNDfdLUCQQCC0IhC8ugr5eeAjStqx7fUej6Yj25PTME0gHt06fnZBGmN9sxwuQ9d" +
                        "1SD29mfRD6TD+Yy0tbONCwtAfDX+zp/tAkEAkRAndUxPVmioBjAqaIjKne5hzy9A" +
                        "yFFyvSR+fADiy8qaS5kin9Xn8S1rCHWUVJ8wooQRUPwb3gm9mW0eF30txQJAIjM+" +
                        "zLIQ/RkYU0WVKk+YwhZLszscjeL1SDKnFEPvrEs5BYKWpIndeJy2B5sYPVIns4zU" +
                        "upIepStT/8H5o+qFaQJBAJkTqnXiJOhMfTmQnJJoUVX4x9v60djhNEgNeQJ2yVxH" +
                        "NNpL2EItqLhFwki2FnWzej0eebEGPzQlwF52PZ+x3Q0="));
        transactions.add(TransactionFactory.getAddNotaryTransaction(new Date(), -1, "Masny Ben", TransactionType.AddNotary, "-1", -1, "-1", "0", "4",
                "MIICWwIBAAKBgGLVOB5tw28Z5pxWdewxnTr6yDBamr30W2cDqbvHRVib8IXyLs+R" +
                        "aw19q3uBRqTPo18EY13HKoBFcU/icDWGSCED0x0TZ3WlUemSX0gQXg8bo1upBB+n" +
                        "uMJR15GkSwO/XFD+NAPgXzyd22T9vP/dRbth8WrHE/Bg58Pbmueghka9AgMBAAEC" +
                        "gYAyoTHnfKlib+1QZ4ZvYhc+0JvfEXs2FkkAo8K+3F1tAC2eIKvUT5V8ysUfNuGe" +
                        "qnPxyAizUMU4AtsCZmi/MrQd+fjP/+vjz2w2tbGHDd7yHp/6KfqDKZfMElwVzf2E" +
                        "BLu3/UtI+52NG5ATXvafiAD5oc94uWWdMTuEfkd3V0t64QJBAK4jn+7jYq/TjEqA" +
                        "sRWT4Amg/EEvNcdC1Mi35I9ayf+STCnchNkok51xw6oUFnWJBMKD1+5Ebo0cepA8" +
                        "QZFYsQUCQQCRSwj1uorwTu1Z/C9brN16BBMfqXD+fSi6ipJ6G1SkKkYIQHs3YqL8" +
                        "Bti3PK0dpahZnmayulXM/K05UEkl+oxZAkBY5BqJ6UzBEC33AgZjS177y0+5Po4n" +
                        "LzhLVYO4odGnQljarZSq/YmMvzvwH9zvFoh1erPJDumX4se1xOx7c1i5AkEAhwLF" +
                        "0anFX+bH0f1weGza5X4R0zMdp63gAe+wiz0IWwBZUWOx29aRg0ZNbYA5kboVBSoX" +
                        "Xm+eOTx3LzH2tw7qCQJATYWKmnuPgy5ZtCsc4YOcd58/XYBu94rXMt/SiPOsEWM4" +
                        "xQuMWZzqs5p15bVhMif8kBe+KcdscQOvSNABx0QfuQ=="));
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
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this.blockchain);
    }

    public void writeBlockchainToFile() {
        String filename = System.getenv("BLOCKCHAIN_FILE_PATH");
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.print(this.getBlockchainStringJson());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while saving blockchain to file");
        }
    }

    private Block getGemini() {
        return this.blockchain.get(0);
    }

    public Map<String, String> getPublicKeysFromGemini() {
        Block gemini = this.getGemini();
        Map<String, String> publicKeys = new HashMap<>();

        for (AbstractTransaction transaction : gemini.getTransactions()) {
            AddNotary addNotary = (AddNotary) transaction;
            publicKeys.put(addNotary.getNotaryIdToAdd(), addNotary.getPublicKey());
        }
        return publicKeys;
    }
}
