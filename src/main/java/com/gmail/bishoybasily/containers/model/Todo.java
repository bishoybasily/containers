package com.gmail.bishoybasily.containers.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bishoybasily
 * @since 2021-04-21
 */
@Data
@Accessors(chain = true)
@Document("todos")
public class Todo {

    @Id
    private String id;
    private String description;
}
