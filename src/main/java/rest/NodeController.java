package rest;

import blockchain.Transaction;
import blockchain.helpers.SHA256;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import logic.Transactions.ConcreteTransactions.*;
import logic.Transactions.Utilities.TransactionType;
import node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/node")
public class NodeController {

    //TODO ADD VALIDATION WHILE WORKING ON SERVER

    @Autowired
    private Node node;

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connectHandler() {
        // TODO handle this, for now static response
        this.node.connectToServer();
        this.node.startNode();
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerHandler() {
        this.node.registerNode();
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        response.addProperty("ID", this.node.getID());
        return response.toString();
    }

    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    public String broadcastHandler() {
        this.node.requestBroadcast();
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.DELETE)
    public String disconnectHandler() {
        this.node.disconnect();
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/add/transaction", method = RequestMethod.POST)
    public String addTransaction(@RequestBody String transactionJson) {
        //TODO add body validation
        TransactionType transactionType = this.chooseProperTransactionType(transactionJson);
        Transaction transactionToAdd = new Transaction(transactionJson, SHA256.generateHash(transactionJson), transactionType);
        this.node.addTransactionToMemPool(transactionToAdd);
        System.out.print(transactionToAdd + "\n");
        var parser = new Gson();
        AbstractTransaction t2 = parser.fromJson(
                transactionToAdd.getData(), VotingResults.class);
        System.out.println(t2);
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/company/info/{id}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable String id) {
        this.node.
    }

    public TransactionType chooseProperTransactionType(String transactionJson) {
        Gson gson = new Gson();
        String type = gson
                .fromJson(transactionJson, JsonElement.class)
                .getAsJsonObject()
                .get("type")
                .getAsString();
        return TransactionType.valueOf(type);
    }
}
