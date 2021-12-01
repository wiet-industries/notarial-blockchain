package blockchain.helpers;

import blockchain.Transaction;
import com.google.gson.Gson;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {
    private Gson parser = new Gson();
    @Override
    public int compare(Transaction o1, Transaction o2) {
        String json1 = this.parser.toJson(o1);
        String json2 = this.parser.toJson(o2);
        // TODO Make comparator compare by priority
        return json1.compareTo(json2);
    }
}
