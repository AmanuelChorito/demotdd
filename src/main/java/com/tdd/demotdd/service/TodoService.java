package com.tdd.demotdd.service;

import com.tdd.demotdd.domain.TodoItem;
import com.tdd.demotdd.repos.JSONFileReader;
import com.tdd.demotdd.repos.TodoRepository;
import com.tdd.demotdd.utils.TodoValidator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoValidator todoValidator;
    private final JSONFileReader jsonFileReader;
    private final TodoRepository todoRepository;
    @Autowired
    public TodoService(TodoValidator todoValidator, JSONFileReader jsonFileReader, TodoRepository todoRepository) {
        this.todoValidator = todoValidator;
        this.jsonFileReader = jsonFileReader;
        this.todoRepository = todoRepository;
    }
    @Value("#{${student.ages}.mike}")
   //@Value("#{${valuesMap}['mike']")
    private Integer x;


    public Integer getInt(){
        return x;
    }
    public Page<TodoItem> findAll(Pageable pageable) {

        System.out.println(getInt());
        return todoRepository.findAll( pageable);
    }

    public Optional<TodoItem> findByTitle(String title) {
            return null;
    }

    public TodoItem save(TodoItem todoItem1) throws IOException {

        return todoRepository.save(todoItem1);
    }

    public TodoItem updateTodos(TodoItem todoItem1) {
            return null;
    }

    public List<TodoItem> getJson() throws IOException {
       return jsonFileReader.getJsonFile();
    }

    public void setJson(TodoItem todoItem1) throws IOException {
          jsonFileReader.writetoJson(todoItem1);
    }
}
