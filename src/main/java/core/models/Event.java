package core.models;

public class Event {
    private final Message payload;

    public Event(Message payload) {
        this.payload = payload;
    }

    public Message getPayload() {
        return this.payload;
    }
}
