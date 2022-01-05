package blockchain.helpers;

import node.EventListener;
import node.Model.Event;

import java.util.LinkedList;
import java.util.List;

public abstract class BlockchainEventManager {
    private final List<EventListener> listeners = new LinkedList<>();

    public void subscribe(EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    boolean unsubscribe(EventListener eventListener) {
        return this.listeners.remove(eventListener);
    }

    protected void notify(Event event) {
        for (EventListener eventListener : this.listeners) {
            eventListener.update(event);
        }
    }
}
