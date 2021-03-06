# LOGIC

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

We need to be able to calculate/update those values depending on what actions company will make. For this every company
action will be held as a transaction. Every transaction will have required information and will trigger proper action on
company.

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

3.NewSharesEmission - Emits new shares, keep in mind that emission of new shares causes reduction of 1 share value

    Additional properties
    -To who we emit new shares(1 person per transaction)
    -How many shares does the shareholder gets

4.SellBuyShares - Transaction providing possibility of exchange

    Additional properties
    -Seller
    -Buyer
    -How much shares

5.SharesLiquidation - Liquidate shares of one owner, keep in mind that liquidation of shares causes share value increase

    Additional properties
    -How much shares to liquidate
    -Whose shares are going to be liquidate

6.Dividends payment - Transaction proving possibility of dividends payments, keep in mind that cash comes from Company
Account(earnings) not from Company Value

    Additional properties
    -How much $
    -In whose pocket $ lands

7.VotingResults - Transaction representing Voting

    Additional properties
    -Question
    -List: Answer: Voters number in %

8.CompanyAccountUpdate - Transaction for updating CompanyAccount

    Additional properties
    -ValueToAdd

<h3> Implementation </h3>
<h4>Design pattern used: <b>Adapter</b></h4>  
Adapter is used to convert from json interface to object represesntations of each transaction.
<br><br>

UML Diagram

![image](https://user-images.githubusercontent.com/72470330/144077593-6e342cfc-b60d-4b1a-991e-b0b8af81177f.png)




