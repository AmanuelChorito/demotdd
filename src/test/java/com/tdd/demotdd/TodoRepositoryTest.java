package com.tdd.demotdd;

import com.tdd.demotdd.domain.TaskStatus;
import com.tdd.demotdd.domain.TodoItem;
import com.tdd.demotdd.repos.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

//to configure real database
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
public class TodoRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;// interact with persistence context.
    @Autowired
    TodoRepository todoRepository;



    @Test
    void shoud_save_new_todo(){
       TodoItem todoItem1 = new TodoItem("code", LocalDate.now(), "coding intervew", TaskStatus.PENDING);
       testEntityManager.persist(todoItem1);
        TodoItem todoItem2 = new TodoItem("code", LocalDate.now(), "coding intervew", TaskStatus.PENDING);
        testEntityManager.persist(todoItem2);

        List<TodoItem> todoItems= Arrays.asList(todoItem1,todoItem2);

        assertEquals(todoItems, todoRepository.findAll());
    }
}
