package main.node;

import main.node.Model.Event;

public interface EventListener {
    void update(Event event);
}
