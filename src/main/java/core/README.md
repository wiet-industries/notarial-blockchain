# Core

implemented as rendezvous server which makes communication between nodes possible.

The server is hosted on the remote machine with the public IP address. This way it can work as an agent between nodes that want to connect with each other.


Diagrams below explain briefly how it is done.

### Communication with Node:
![image](https://user-images.githubusercontent.com/30171233/143587283-26e3750c-433a-442d-ab97-bc88fcca1ef1.png)

### Handling Node to Node connection:
![image](https://user-images.githubusercontent.com/30171233/143587375-0dec4f69-e80d-435a-b8f4-97b3042c1ae2.png)

## Used design patterns

### Observer

We decided to use observer pattern as we need to notify the Server object (which manages all the logic) as we want to choose proper strategy when processing the received messages.
The server takes role of the observer which subscribe to subjects, such as new tcp connection, upd and tcp message.
Our event managers work independent of each other and each of them work on a different thread.
That's the reason our main collection of ClientHandlers had to be synchronized.

### Strategy

Considering we have a lot of different types of message, such as: CONNECTION, REGISTER, DISCONNECT, etc.
we had to use strategy pattern for managing server actions. Server is just in charge of selecting proper strategy according to what type of message it receives.
StrategyContext is responsible for proper strategy execution, which was chosen by the server. For each message type we implemented separate strategy.

## CommunicationDTO

We wanted to standardize the way that each node communicates with each other, as well as with the server. Every message received or sent has given data model. Here is an example one:

```JSON
{
  "messageType": "OPEN_REQUEST",
  "content": {
    "ipAddress": "77.66.55.44",
    "port": "26655"
  },
  "ID": 4
}
```








