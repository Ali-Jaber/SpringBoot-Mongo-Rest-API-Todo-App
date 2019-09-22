package com.spring.tut.todoapp.respository;

import com.spring.tut.todoapp.view.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {

    Todo findByTitle(final String title);

    List<Todo> findByUserId(final String userId);
}
