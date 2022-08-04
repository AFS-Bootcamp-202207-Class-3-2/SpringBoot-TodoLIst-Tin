package com.rest.todolistbackend.controller;

import com.rest.todolistbackend.common.Result;
import com.rest.todolistbackend.controller.mapper.TodoMapper;
import com.rest.todolistbackend.controller.vo.TodoVO;
import com.rest.todolistbackend.entity.Todo;
import com.rest.todolistbackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

    @GetMapping
    public Result getAllTodos(){
        List<Todo> todos = todoService.getAllTodos();
        return Result.success(todos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result createTodos(@RequestBody TodoVO todoVO){
        Todo todos = todoService.createTodo(todoMapper.toEntity(todoVO));
        return Result.success(todos);
    }

    @PutMapping("/{id}")
    public Result updateTodos(@PathVariable Integer id,@RequestBody TodoVO todoVO){
        Todo todos = todoService.updateTodo(id, todoMapper.toEntity(todoVO));
        return Result.success(todos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Result deleteTodos(@PathVariable Integer id){
        todoService.deleteTodo(id);
        return Result.success("删除成功");
    }
}
