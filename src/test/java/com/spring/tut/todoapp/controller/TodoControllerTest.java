package com.spring.tut.todoapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.tut.todoapp.service.TodoService;
import com.spring.tut.todoapp.view.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void whenGetAllTodos_thenReturnJsonArray() throws Exception {
        Todo todo1 = new Todo("1", "Todo 1", "Todo 1");
        Todo todo2 = new Todo("2", "Todo 2", "Todo 2");
        List<Todo> data = Arrays.asList(todo1, todo2);

        given(todoService.findByUser(anyString())).willReturn(data);


        mockMvc.perform(get("/api/v1/todo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));

    }

    @Test
    public void whenPostTodo_thenCreateTodo() throws Exception{
        Todo todo1 = new Todo();
        todo1.setTitle("Title of todo");
        todo1.setDescription("Title of todo");

        given(todoService.save(Mockito.any(Todo.class))).willReturn(todo1);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                post("/api/v1/todo/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo1))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(todo1.getTitle())));

    }
}
