package com.todo_application.example.todo_app.controller;

import com.todo_application.example.todo_app.models.TodoItem;
import com.todo_application.example.todo_app.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoFormController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/todo")
    public List<TodoItem> getAllTodos() {
        return todoItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        return todoItemService.findById(id)
                .map(todoItem -> ResponseEntity.ok().body(todoItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-todo")
    public TodoItem createTodoItem(@Valid @RequestBody TodoItem todoItem) {
        return todoItemService.save(todoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @Valid @RequestBody TodoItem todoItemDetails) {
        return todoItemService.findById(id)
                .map(todoItem -> {
                    todoItem.setDescription(todoItemDetails.getDescription());
                    todoItem.setIsComplete(todoItemDetails.getIsComplete());
                    return ResponseEntity.ok(todoItemService.save(todoItem));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodoItem(@PathVariable Long id) {
        return todoItemService.findById(id)
                .map(todoItem -> {
                    todoItemService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

