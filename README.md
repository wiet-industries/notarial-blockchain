# notarial-blockchain
Infrastructure to store notarial data based on blockchain, detailed documentation of each package or module:
- [Node](https://github.com/wiet-industries/notarial-blockchain/tree/master/src/main/java/node)
- [Core](https://github.com/wiet-industries/notarial-blockchain/tree/master/src/main/java/core)
- [Blockchain](https://github.com/wiet-industries/notarial-blockchain/tree/master/src/main/java/blockchain)
- [Logic](https://github.com/wiet-industries/notarial-blockchain/tree/master/src/main/java/logic)
- [Rest](https://github.com/wiet-industries/notarial-blockchain/tree/master/src/main/java/rest)

## Overview
Application infrastructure is based on web of `Nodes`. Each Node shares data with others using P2P model (NAT Hole punching is used). To establish connections between nodes each node sends requests to `Core` with public IP, so it can be reachable from any Node in Web. Node is a service which runs it's own `REST` and open a `frontend` web application. Application provides set of operations to controll node, establish connection, add transaction to blockchain, see blocks etc. and use REST to connect with Node service which is responsible for connection and sharing data. Blockchain data is stored localy in database Blockchain architecture is classic blockchain-like app.

![image](https://user-images.githubusercontent.com/30171233/145886543-ded726b2-d6fd-46f2-a1b2-15baa2ddee06.png)


## Authors
- Adrian Ryt (adrianryt3@gmail.com)
- Kamil Kurowski (kkurowski.kk@gmail.com)
- Adam Dyda (adam.dyda1@gmail.com)
- Mateusz Kowalski (wawel37@gmail.com)


## Stack
- Java (Spring, Maven)
- React (with TS)

