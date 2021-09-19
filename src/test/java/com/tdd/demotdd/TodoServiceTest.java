package com.tdd.demotdd;

import com.tdd.demotdd.domain.TodoItem;
import com.tdd.demotdd.repos.TodoRepository;
import com.tdd.demotdd.service.TodoService;
import com.tdd.demotdd.utils.TodoValidator;
import com.tdd.demotdd.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoValidator todoValidator;
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Captor
    ArgumentCaptor<TodoItem> todoItemArgumentCaptor;

    TodoItem todoItem1,todoItem2;

    @BeforeEach
    void setUp() {
        todoItem1 = new TodoItem(1,"code", LocalDate.now(), "coding intervew", TaskStatus.PENDING);
        todoItem2 = new TodoItem(2,"learn", LocalDate.now(), "learning", TaskStatus.PENDING);
    }

    @Test
    void itShouldAddNewTodoTask() throws IOException {
        //action
        when(todoRepository.save(todoItem1)).thenReturn(todoItem1);

        //assert
        assertEquals(todoService.save(todoItem1),todoItem1);
    }

    @Test
    void getAllTasks(){
        //assign
        Page<TodoItem> todoItems= (Page<TodoItem>) Arrays.asList(todoItem1,todoItem2);
        //action
        org.springframework.data.domain.Pageable paging = PageRequest.of(1, 1);
        when(todoRepository.findAll(paging)).thenReturn(todoItems);
        //assert
        assertEquals(todoService.findAll(paging),todoItems);
    }

}
