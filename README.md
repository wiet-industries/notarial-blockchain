# notarial-blockchain
Infrastructure to store notarial data based on blockchain

## Overview
Application infrastructure is based on web of `Nodes`. Each Node shares data with others using P2P model (NAT Hole is used). To establish connection between nodes each node send requests to `Core` with public IP, so can be reachable for any Node in Web. Node is a service which runs it's own `REST` and open a `frontend` web application. Application provides sets of operation to controll node, establish connection, add transaction to blockchain, see blocks etc. and use REST to connect with Node service which is responsible for connection and sharing data. Blockchain data is stored localy in database (still developing). Blockchain architecture is classic blockchai-like app.

![image](https://user-images.githubusercontent.com/30171233/145886543-ded726b2-d6fd-46f2-a1b2-15baa2ddee06.png)


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

Rendezvous server which manage nodes connection


Core is a virtual private server with public IP, is listening on both TCP and UDP ports.


Comunication with Node:

![image](https://user-images.githubusercontent.com/30171233/143587283-26e3750c-433a-442d-ab97-bc88fcca1ef1.png)


Handling Node to Node connection:

![image](https://user-images.githubusercontent.com/30171233/143587375-0dec4f69-e80d-435a-b8f4-97b3042c1ae2.png)

## Node

single blockchain node


Node is listening on both TCP and UDP ports.



Comunication with Server:

![image](https://user-images.githubusercontent.com/30171233/143587283-26e3750c-433a-442d-ab97-bc88fcca1ef1.png)


Sending to server request to broadcast data:

![image](https://user-images.githubusercontent.com/30171233/143587375-0dec4f69-e80d-435a-b8f4-97b3042c1ae2.png)


Connection with different node:

![image](https://user-images.githubusercontent.com/30171233/143588169-5cd19734-f3cf-4da4-8e74-8491b27c649d.png)


## Blockchain

Blocks and transactions

![image](https://user-images.githubusercontent.com/72470330/144079094-deb3d591-76a7-4490-8735-e4d8aa5ffef6.png)

## Logic

<h3> COMPANY </h3>
Every company will be represented by following values:

    -Company Value
    -Company Account(Earnings - can be negative)
    -Share Value - value of 1 share
    -Distributed Shares - We have to know who and how
    many company's shares every owner have
    -Dividends payment - Store informaction
    about dividens
    -Voting results
We need to be able to calculate/update those values
depending on what actions company will make.
For this every company action will be held as a transaction.
Every transaction will have required information and will trigger
proper action on company.

You can read more about blockchain transaction at global ReadMe

<h3> Transactions </h3>
Transaction is an object representing changes at company.

Every transaction has those properties:

    -Hash
    -Author
    -Data
    -Priority
    -Status
    -CompanyID
    -Type
    -Other properties depending on Transaction Type

<h4> Transaction Types </h4>
1. AddCompany - transaction responsible for adding new company
    
    Additional properties
    -Company Name
    -Company ID
    -Company Value
    -Company Account (0 if not providet)
    -Share Value
    -Distributed Shares
    -Voting results

2.CompanyValueUpdate - Adds value to CompanyValue, keep in mind that it causes share value decrease/increase


    Additional properties
    -ValueToAdd
3.NewSharesEmission - Emits new shares, keep in mind 
that emission of new shares causes reduction of 1 share value

    Additional properties
    -To who we emit new shares(1 person per transaction)
    -Who gets shares
4.SellBuyShares - Transaction providing possibility of exchange

    Additional properties
    -Seller
    -Buyer
    -How much shares

5.SharesLiquidation - Liquidate shares of one owner, keep in mind that
liquidation of shares causes share value increase
    
    Additional properties
    -How much shares to liquidate
    -Whose shares are going to be liquidate
6.Dividends payment - Transaction proving possibility of dividends payments, keep in mind that
cash comes from Company Account(earnings) not from Company Value 
    
    Additional properties
    -How mutch $
    -In whose pocket $ lands
7.VotingResults - Transaction representing Voting
    
    Additional properties
    -Question
    -List: Answer: Voters number in %
8.CompanyAccountUpdate - Transaction for updating CompanyAccount
    
    Additional properties
    -ValueToAdd

<h4> Implementation </h4>

![image](https://user-images.githubusercontent.com/72470330/144077593-6e342cfc-b60d-4b1a-991e-b0b8af81177f.png)


Design pattern used: <b>Builder and Factory</b> <br>

<h5>Short summary of implementation</h5>
- Every concrete transaction has own builder. <br>
- Every concrete builder can be returned by BuilderFactory <br>
- To create transaction we should use TransactionsDirector


## REST

## Frontend


