package dev.kannan.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping
    public String welcome() {
        return "Hello World!";
    }

    @GetMapping("/{nameOfUser}")
    public String welcomeByName(@PathVariable("nameOfUser") String nameOfUser) {
        nameOfUser = nameOfUser.toUpperCase().trim();

        if (nameOfUser.isEmpty()) {
            return "Hello World!";
        }
        return "Hello " + nameOfUser + "!";
    }
}
