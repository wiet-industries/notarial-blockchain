package core.connection;

import core.utils.EventListener;
import core.models.Event;

import java.util.LinkedList;
import java.util.List;

public abstract class EventManager extends Thread {
    List<EventListener> listeners = new LinkedList<>();

    public void subscribe(EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    public boolean unsubscribe(EventListener eventListener) {
        return this.listeners.remove(eventListener);
    }

    public void notify(Event event) {
        for (EventListener eventListener : this.listeners) {
            eventListener.update(event);
        }
    }
}
