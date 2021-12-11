package core.models;

public class Event {
    private final Message message;

    public Event(Message message) {
        this.message = message;
    }

    public Message getPayload() {
        return this.message;
    }
}
