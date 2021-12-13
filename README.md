# notarial-blockchain
Infrastructure to store notarial data based on blockchain

## Overview
Application infrastructure is based on web of `Nodes`. Each Node shares data with others using P2P model (NAT Hole is used). To establish connection between nodes each node send requests to `Core` with public IP, so can be reachable for any Node in Web. Node is a service which runs it's own `REST` and open a `frontend` web application. Application provides sets of operation to controll node, establish connection, add transaction to blockchain, see blocks etc. and use REST to connect with Node service which is responsible for connection and sharing data. Blockchain data is stored localy in database (still developing). Blockchain architecture is classic blockchai-like app.

## Authors
- Adrian Ryt (adrianryt3@gmail.com)
- Kamil Kurowski (kkurowski.kk@gmail.com)
- Adam Dyda (adam.dyda1@gmail.com)
- Mateusz Kowalski (wawel37@gmail.com)


## Stack
- Java (Spring, Maven)
- React (with TS)
- DB (TODO: Mongo or WatermelonDB)

# Modules

## Core

## Node

## Blockchain

## Logic

## REST

## Frontend


