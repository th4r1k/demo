package app.brickup.demo.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.brickup.demo.model.Todo;
import app.brickup.demo.repository.TodoRepository;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "https://redux-todo-delta.vercel.app")
public class TodoController {
    
    private TodoRepository repository;

    TodoController(TodoRepository todoRepository){
        this.repository = todoRepository;
    }

    @GetMapping
    @CrossOrigin(origins = "https://redux-todo-delta.vercel.app")
    public List<Todo> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable(value = "id") long id){
    return repository.findById(id)
           .map(data -> ResponseEntity.ok().body(data))
           .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @CrossOrigin(origins = "https://redux-todo-delta.vercel.app")
    public Todo create(@Validated @Valid @RequestBody Todo todo){
    return repository.save(todo);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "https://redux-todo-delta.vercel.app")
    public ResponseEntity<Todo> update(@PathVariable(value = "id") long id,
    @RequestBody Todo todo) {
    return repository.findById(id)
            .map(data -> {
                data.setTarefa(todo.getTarefa());
                data.setStatus(todo.getStatus());
                data.setFoto(todo.getFoto());
                Todo updated = repository.save(data);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "https://redux-todo-delta.vercel.app")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
    return repository.findById(id)
            .map(data -> {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }
    }
