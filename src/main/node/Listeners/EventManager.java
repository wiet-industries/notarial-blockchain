package main.node.Listeners;

import main.node.Model.Event;
import main.node.EventListener;

import java.util.LinkedList;
import java.util.List;

public abstract class EventManager extends Thread {
    List<EventListener> listeners = new LinkedList<>();

    public void subscribe(EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    boolean unsubscribe(EventListener eventListener) {
        return this.listeners.remove(eventListener);
    }

    void notify(Event event) {
        for (EventListener eventListener : this.listeners) {
            eventListener.update(event);
        }
    }
}
