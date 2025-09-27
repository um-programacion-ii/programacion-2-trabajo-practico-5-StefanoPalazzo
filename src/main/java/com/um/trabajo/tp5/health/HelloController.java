package com.um.trabajo.tp5.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/status")
    public String status() {
        return "ok";
    }
}
