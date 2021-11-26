package main.core;

import java.util.LinkedList;
import java.util.List;

public abstract class EventManager extends Thread {
    List<EventListener> listeners = new LinkedList<>();

    void subscribe(EventListener eventListener) {
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
