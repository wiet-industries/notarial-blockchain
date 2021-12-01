package blockchain;

import blockchain.helpers.TransactionComparator;

import java.util.concurrent.PriorityBlockingQueue;

import static blockchain.helpers.Config.MEMPOOLCAPACITY;

public class MemPool {
    //Thread safe queue
    public final PriorityBlockingQueue<Transaction> priorityBlockingQueue =
            new PriorityBlockingQueue<>(MEMPOOLCAPACITY, new TransactionComparator());


    public void addTransaction(Transaction transaction) {
        // TODO check if mempool is full
        this.priorityBlockingQueue.add(transaction);
    }

    public Transaction getTransaction() {
        return this.priorityBlockingQueue.poll();
    }

}
