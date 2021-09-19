package com.tdd.demotdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.demotdd.controller.TodoController;
import com.tdd.demotdd.domain.TaskStatus;
import com.tdd.demotdd.domain.TodoItem;
import com.tdd.demotdd.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;

    private TodoItem todoItem1, todoItem2, todoItem3;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        todoItem1 = new TodoItem(1,"code", LocalDate.now(), "coding intervew", TaskStatus.PENDING);
        todoItem2 = new TodoItem(2,"learn", LocalDate.now(), "learning", TaskStatus.PENDING);

    }

    @Test

    void getAllTodos() throws Exception {
        //Assign
        Page<TodoItem> todoItemList= (Page<TodoItem>) Arrays.asList(todoItem1,todoItem2);
        org.springframework.data.domain.Pageable paging = PageRequest.of(1, 1);
        //action
       when(todoService.findAll(paging)).thenReturn(todoItemList);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).
                andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todoItemList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //assert

        String actualResponseBody = result.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(todoItemList));

    }

    @Test
    void getbyTitle() throws Exception {
      //arrange
        when(todoService.findByTitle((todoItem1.getTitle()))).thenReturn(java.util.Optional.ofNullable(todoItem1));

        // action

        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/todos/"+todoItem1.getTitle())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result= mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todoItem1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String actualBodyResponse= result.getResponse().getContentAsString();

        //assert
        assertThat(actualBodyResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(todoItem1));

    }


    @Test
    void  addNewTodoItem() throws Exception{
        //arrange
        when(todoService.save(todoItem1)).thenReturn(todoItem1);

        //action
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoItem1));

     MvcResult result =  mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todoItem1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

       ArgumentCaptor<TodoItem> todoItemArgumentCaptor=ArgumentCaptor.forClass(TodoItem.class);

        verify(todoService,times(1)).save(todoItemArgumentCaptor.capture());


        String actualBodyResponse=result.getResponse().getContentAsString();

        //assert
        assertThat(actualBodyResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(todoItem1));
     //   assertThat(todoItemArgumentCaptor.getValue().getDescription()).isEqualTo("coding intervew");

    }

    @Test
    void ValidateNullInput_thenReturns400()throws Exception{
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/todos"
                ).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoItem2));

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }



    @Test
    void updateTodosItem() throws Exception{
        //assign
        when(todoService.updateTodos(todoItem1)).thenReturn(todoItem1);
        //action

        //assert

    }
}
