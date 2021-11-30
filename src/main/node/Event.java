package main.node;

public class Event {
    private byte[] data;

    Event(byte[] data) {
        this.data = data;
    }

    byte[] getData() {
        return this.data;
    }
}
