package rest;

import blockchain.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "register", method = RequestMethod.POST)
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
        Transaction transactionToAdd = new Gson().fromJson(transactionJson, Transaction.class);
        this.node.addTransactionToMemPool(transactionToAdd);
//        System.out.printf(transactionToAdd.toString() + "\n");
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }
}
