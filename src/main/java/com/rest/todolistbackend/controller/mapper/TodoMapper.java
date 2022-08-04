package com.rest.todolistbackend.controller.mapper;

import com.rest.todolistbackend.controller.vo.TodoVO;
import com.rest.todolistbackend.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoVO todoVO) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoVO, todo);
        return todo;
    }
}
