package com.tdd.demotdd.controller;

import com.tdd.demotdd.domain.TodoItem;
import com.tdd.demotdd.service.TodoService;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class TodoController {

    private final TodoService todoService;
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    ResponseEntity<Map<String,Object>>getAllTodo(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size){

            List<TodoItem> todoItems;
            org.springframework.data.domain.Pageable paging = PageRequest.of(page, size);
            Page<TodoItem> itemPage = todoService.findAll(paging);
            todoItems = itemPage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("todo", todoItems);
            response.put("currentPage", itemPage.getNumber());
            response.put("totalItems", itemPage.getTotalElements());
            response.put("totalPages", itemPage.getTotalPages());

                if(todoItems.size()!=0){
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/todos/{title}")
    ResponseEntity<Optional<TodoItem>> getTodoByTitle(@PathVariable String title){
        return new ResponseEntity<>(todoService.findByTitle(title),HttpStatus.OK);
    }

    @PostMapping("/todos")
    ResponseEntity<TodoItem> addNewTodos( @RequestBody @Validated TodoItem todoItem) throws IOException {
        return new ResponseEntity<>(todoService.save(todoItem),HttpStatus.CREATED);
    }
}
