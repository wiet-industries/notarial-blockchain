package rest;

import com.google.gson.JsonObject;
import logic.TransactionAdapter;
import node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/node")
public class NodeController {

    //TODO ADD VALIDATION WHILE WORKING ON SERVER

    @Autowired
    private Node node;

    @Autowired
    private TransactionAdapter adapter;

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
        System.out.println(transactionJson);
        try {
            adapter.createTransactionFromJson(transactionJson);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Bad /add/transaction data");
        }
        System.out.println(transactionJson);
        this.node.addTransactionToMemPool(adapter.getTransaction());
        System.out.println("dodana transakcja B) \n");
        JsonObject response = new JsonObject();
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/company/info/{id}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable String id) {
        return this.node.getCompanyWithID(Integer.parseInt(id)).toJson();
    }

}
