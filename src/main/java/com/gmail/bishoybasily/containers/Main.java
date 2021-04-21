package com.gmail.bishoybasily.containers;

import com.gmail.bishoybasily.containers.model.Todo;
import com.gmail.bishoybasily.containers.model.TodoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner(TodoRepo repo) {
        return args -> {
            var todo = repo.save(new Todo().setDescription("hello mongo")).block();
            System.out.println(todo);
        };
    }

}
