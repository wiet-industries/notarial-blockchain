package blockchain.helpers;

import com.google.gson.Gson;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.util.Comparator;

public class TransactionComparator implements Comparator<AbstractTransaction> {
    private final Gson parser = new Gson();

    @Override
    public int compare(AbstractTransaction o1, AbstractTransaction o2) {
        String json1 = this.parser.toJson(o1);
        String json2 = this.parser.toJson(o2);
        // TODO Make comparator compare by priority
        return json1.compareTo(json2);
    }
}
