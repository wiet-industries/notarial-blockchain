package blockchain;

import logic.Transactions.ConcreteTransactions.AbstractTransaction;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import static blockchain.helpers.Config.MEMPOOLCAPACITY;

public class MemPool {
    //Thread safe queue
    public final PriorityBlockingQueue<AbstractTransaction> priorityBlockingQueue =
            new PriorityBlockingQueue<>(MEMPOOLCAPACITY);


    public void addTransaction(AbstractTransaction transaction) {
        // TODO check if mempool is full
        //System.out.println(transaction);
        this.priorityBlockingQueue.add(transaction);
    }

    public AbstractTransaction getTransaction() {
        return this.priorityBlockingQueue.poll();
    }

    public List<AbstractTransaction> getMemPoolTransactions() {
        return new LinkedList<>(priorityBlockingQueue);
    }

}
