# Node
The node is used to communicate with other users as well as to handle server communication. Node is run on user's machine and it communicates with frontend using REST API while communicating with server and other nodes using TCP/UDP sockets.

The Diagram below shows the whole communication process.


![image](https://user-images.githubusercontent.com/37600249/148099570-d23e6f60-ab77-456f-809d-432e13c9ec40.png)


## Used design patterns

### Observer

We decided to use observer pattern as we need to notify the Node object 
The node takes role of the observer which subscribes to subjects - tcp or udp listeners.
Our event managers (tcp/udp listeners) work independent of each other and each of them work on a different thread.

## Server connection
Server connection is handled using ServerSessionHandler class; the class manages registering node on the server and requesting a broadcast to other nodes.

### Register message 
```JSON
{
  "messageType": "REGISTER",
  "content": "x" //x here is ID of the node
  "ID": x
}
```

### Request broadcast message
```JSON
{
  "messageType": "BROADCAST",
  "content": "x" //x here is ID of the node
  "ID": x
}
```

## Peers connection
Peer connection is handled using PeerConnectionHandler class, the class manages broadcasting data to other peers as well as opening session for other peers to send data to us.

### Send data message
```JSON
{
  "messageType": "DATA",
  "content": "[DATA TO SEND]"
  "ID": x
}
```

### Open session message
```JSON
{
  "messageType": "SESSION_DATA",
  "content": "[GARBAGE DATA]" //doesnt matter what data you send here it just has to be sent
  "ID": x
}
```

## UML of the Node package

![image](https://user-images.githubusercontent.com/37600249/147414868-a459356f-1673-4ebb-ae58-66f17c9567ae.png)




