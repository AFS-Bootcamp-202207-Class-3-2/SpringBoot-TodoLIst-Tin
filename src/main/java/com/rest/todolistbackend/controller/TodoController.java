package com.rest.todolistbackend.controller;

import com.rest.todolistbackend.common.Result;
import com.rest.todolistbackend.common.ResultEnum;
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
@RequestMapping("/todos")
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
        return Result.success().code(ResultEnum.CREATED).data(todos).msg("创建成功");
    }

    @PutMapping("/{id}")
    public Result updateTodos(@PathVariable Integer id,@RequestBody TodoVO todoVO){
        Todo todos = todoService.updateTodo(id, todoMapper.toEntity(todoVO));
        System.out.println(todos.toString());
        return Result.success(todos).msg("修改成功");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Result deleteTodos(@PathVariable Integer id){
        todoService.deleteTodo(id);
        return Result.success().code(ResultEnum.NO_CONTENT).data(id).msg("删除成功");
    }
}
