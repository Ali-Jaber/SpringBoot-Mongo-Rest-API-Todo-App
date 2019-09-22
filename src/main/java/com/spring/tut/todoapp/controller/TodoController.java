package com.spring.tut.todoapp.controller;

import com.spring.tut.todoapp.service.TodoService;
import com.spring.tut.todoapp.view.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable final String id) {
        Todo result = service.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody final Todo todo) {
        Todo result = service.save(todo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
