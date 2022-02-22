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
        JsonObject response = new JsonObject();
        if (this.node.registerNode()) {
            response.addProperty("message", "OK");
        } else {
            response.addProperty("message", "NOT_AUTHORIZED");
        }
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
        //System.out.println(transactionJson);
        JsonObject response = new JsonObject();
        try {
            adapter.createTransactionFromJson(transactionJson);
            this.node.addTransactionToMemPool(adapter.getTransaction());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad /add/transaction data");
            response.addProperty("message", "Bad Request");
            return response.toString();
        }
        //System.out.println(transactionJson);
        //System.out.println("dodana transakcja B) \n");
        response.addProperty("message", "OK");
        return response.toString();
    }

    @RequestMapping(value = "/company/info/{id}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable String id) {
        return this.node.getCompanyWithID(Integer.parseInt(id)).toJson();
    }

}
