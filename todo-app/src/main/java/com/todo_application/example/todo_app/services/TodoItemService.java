package com.todo_application.example.todo_app.services;

import com.todo_application.example.todo_app.models.TodoItem;
import com.todo_application.example.todo_app.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> findById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem save(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public void deleteById(Long id) {
        todoItemRepository.deleteById(id);
    }
}


