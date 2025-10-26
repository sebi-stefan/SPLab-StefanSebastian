package org.example.splabstefansebastian.controller;

import lombok.RequiredArgsConstructor;
import org.example.splabstefansebastian.lab3components.ClientComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HelloController {

    private final ClientComponent cc;

    @GetMapping
    public String hello(){
        return "Hello from Spring Boot";
    }

    @GetMapping("/clientComponent")
    public String helloFromClientComponent(){
        return cc.toString();
    }


}
