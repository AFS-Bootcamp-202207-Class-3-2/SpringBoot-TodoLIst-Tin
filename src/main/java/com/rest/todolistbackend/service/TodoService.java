package com.rest.todolistbackend.service;

import com.rest.todolistbackend.entity.Todo;
import com.rest.todolistbackend.exception.TodoNotFoundException;
import com.rest.todolistbackend.repository.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    private Todo findTodoById(Integer id){
        return todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }
    public Todo updateTodo(Integer id, Todo todo) {
        Todo todoById = findTodoById(id);
        if(todo.getText() != null){
            todoById.setText(todo.getText());
        }
        if(todo.getDone() != null){
            todoById.setDone(todo.getDone());
        }
        return todoRepository.save(todoById);
    }

    public void deleteTodo(Integer id) {
        Todo todoById = findTodoById(id);
        todoRepository.delete(todoById);
    }
}
