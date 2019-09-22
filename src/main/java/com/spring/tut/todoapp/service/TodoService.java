package com.spring.tut.todoapp.service;

import com.spring.tut.todoapp.exception.ConflictException;
import com.spring.tut.todoapp.exception.NotFoundException;
import com.spring.tut.todoapp.respository.TodoRepository;
import com.spring.tut.todoapp.view.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public List<Todo> findByUser(final String id) {
        return repository.findByUserId(id);
    }

    public Todo getById(final String id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", id));
        }
    }

    public Todo save(final Todo todo) {
        if(repository.findByTitle(todo.getTitle()) != null) {
            throw new ConflictException("Another record with same title exists");
        }
        return repository.insert(todo);
    }

    public void delete(final String id) {
        repository.deleteById(id);
    }
}
