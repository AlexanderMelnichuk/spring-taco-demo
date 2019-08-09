package tacos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {

    @RequestMapping(path = "/env")
    public String test() {
        return String.format("Classpath:<br>%s", System.getProperty("java.class.path"));
    }
}
