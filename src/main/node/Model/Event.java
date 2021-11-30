package main.node.Model;

public class Event {
    private byte[] data;

    public Event(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }
}
