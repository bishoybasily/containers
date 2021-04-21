package com.gmail.bishoybasily.containers.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author bishoybasily
 * @since 2021-04-21
 */
public interface TodoRepo extends ReactiveMongoRepository<Todo, String> {
}
