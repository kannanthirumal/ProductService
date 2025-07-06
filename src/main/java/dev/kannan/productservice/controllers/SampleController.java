package dev.kannan.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class SampleController {

    @GetMapping
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/{nameOfUser}")
    public String sayHelloPersonalized(@PathVariable("nameOfUser") String name) {
        return "Hello " + name + "!";
    }
}
