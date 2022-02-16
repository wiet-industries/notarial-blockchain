package logic;

import blockchain.helpers.SHA256;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import logic.Transactions.ConcreteTransactions.*;
import logic.Transactions.Utilities.TransactionType;

import static logic.Transactions.Utilities.TransactionType.valueOf;

public class TransactionAdapter implements ITransactionAdapter {
    private final Gson gson;
    private AbstractTransaction transaction;

    public TransactionAdapter() {
        this.gson = new Gson();
    }

    @Override
    public void createTransactionFromJson(String transactionJson) throws ClassNotFoundException {
        this.transaction = null;
        JsonObject jsonObject = this.gson
                .fromJson(transactionJson, JsonElement.class)
                .getAsJsonObject();
        TransactionType transactionType = this.getTransactionType(jsonObject);
        switch (transactionType) {
            case AddCompany:
                this.transaction = gson.fromJson(transactionJson, AddCompany.class);
                break;
            case CompanyAccountUpdate:
                this.transaction = gson.fromJson(transactionJson, CompanyAccountUpdate.class);
                break;
            case NewSharesEmission:
                this.transaction = gson.fromJson(transactionJson, NewSharesEmission.class);
                break;
            case SharesBuySell:
                this.transaction = gson.fromJson(transactionJson, SellBuyShares.class);
                break;
            case SharesLiquidation:
                this.transaction = gson.fromJson(transactionJson, SharesLiquidation.class);
                break;
            case DividendsPayment:
                this.transaction = gson.fromJson(transactionJson, DividendsPayment.class);
                break;
            case VotingResults:
                this.transaction = gson.fromJson(transactionJson, VotingResults.class);
                break;
            case CompanyValueUpdate:
                this.transaction = gson.fromJson(transactionJson, CompanyValueUpdate.class);
                break;
            case AddNotary:
                this.transaction = gson.fromJson(transactionJson, AddNotary.class);
            case DeleteNotary:
                this.transaction = gson.fromJson(transactionJson, DeleteNotary.class);
            default:
                throw new ClassNotFoundException();
        }
        this.transaction.setHash(SHA256.generateHash(transactionJson));
        // TODO here I want to encrypt verification with RSA
//        this.transaction.setVerification();
    }

    @Override
    public AbstractTransaction getTransaction() {
        return this.transaction;
    }

    public TransactionType getTransactionType(JsonObject transactionJson) {
        return valueOf(transactionJson.get("transactionType").getAsString());
    }
}
