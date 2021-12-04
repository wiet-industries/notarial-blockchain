package core.models;

public class Event {
    private final SocketPayload payload;

    public Event(SocketPayload payload) {
        this.payload = payload;
    }

    public SocketPayload getPayload() {
        return this.payload;
    }
}
