package com.gmail.bishoybasily.containers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class Cont {

    private final String uuid = UUID.randomUUID().toString();

    @GetMapping
    public String uuid() {
        return uuid;
    }
}
