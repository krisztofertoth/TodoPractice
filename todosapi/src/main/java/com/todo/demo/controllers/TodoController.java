package com.todo.demo.controllers;

import com.todo.demo.models.Todo;
import com.todo.demo.payload.request.TodoRequest;
import com.todo.demo.payload.response.MessageResponse;
import com.todo.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @Autowired



    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    @PostMapping(value = "add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody TodoRequest todoRequest){

        Todo todo = new Todo(todoRequest.getText());
        todoRepository.save(todo);

        return ResponseEntity.ok(new MessageResponse("Todo is created!"));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTodo(@PathVariable long id){
        Todo todo = todoRepository.findById(id).orElse(null);
        todoRepository.delete(todo);

        return ResponseEntity.ok(new MessageResponse("Todo is deleted"));

    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> changeTodoStatus(@PathVariable long id){
        Todo todo = todoRepository.findById(id).orElse(null);
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);

        return ResponseEntity.ok(new MessageResponse("Todo status has been changed"));
    }
    @PutMapping("{id}/edit")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editTodo(@PathVariable long id, @RequestBody TodoRequest todoRequest){
        Todo todo = todoRepository.findById(id).orElse(null);
        todo.setText(todoRequest.getText());
        todoRepository.save(todo);

        return ResponseEntity.ok(new MessageResponse("Todo has been changed"));
    }



}

