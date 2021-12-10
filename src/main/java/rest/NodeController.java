package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/node")
public class NodeController {

    @RequestMapping(value = "/tmp", method = RequestMethod.POST)
    public String tmp(@RequestParam(value = "name") String name) {
        return name;
    }
}