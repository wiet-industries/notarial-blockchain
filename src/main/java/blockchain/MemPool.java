package blockchain;

import blockchain.helpers.TransactionComparator;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.util.concurrent.PriorityBlockingQueue;

import static blockchain.helpers.Config.MEMPOOLCAPACITY;

public class MemPool {
    //Thread safe queue
    public final PriorityBlockingQueue<AbstractTransaction> priorityBlockingQueue =
            new PriorityBlockingQueue<>(MEMPOOLCAPACITY, new TransactionComparator());


    public void addTransaction(AbstractTransaction transaction) {
        // TODO check if mempool is full
        System.out.println(transaction);
        this.priorityBlockingQueue.add(transaction);
    }

    public AbstractTransaction getTransaction() {
        return this.priorityBlockingQueue.poll();
    }

}
