package com.example.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/greet")
    @ResponseStatus(HttpStatus.OK)
    public String greetings(@RequestParam(value = "name", required = false, defaultValue = "") String name)
    throws Exception {
        log.info("Greetings from Jetty Server!");
        return String.format("Hello %s, Welcome to Jetty World!", name);
    }
}
