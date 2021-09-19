package com.tdd.demotdd.repos;


import com.tdd.demotdd.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
@Repository
public interface TodoRepository extends JpaRepository<TodoItem,Integer> {
    Optional<TodoItem> findTodoItemByTitle(String tile);

}
