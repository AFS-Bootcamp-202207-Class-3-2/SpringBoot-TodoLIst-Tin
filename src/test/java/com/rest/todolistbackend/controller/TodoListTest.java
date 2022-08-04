package com.rest.todolistbackend.controller;

import com.rest.todolistbackend.entity.Todo;
import com.rest.todolistbackend.repository.TodoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TodoListTest {

    @Autowired
    MockMvc client;

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    public void cleanData(){
        todoRepository.deleteAll();
    }

    public List<Todo> getTodoList(){
        ArrayList<Todo> testList = new ArrayList<Todo>(){{
            add(new Todo(null,"testA", false, new Date(), new Date()));
            add( new Todo(null,"testB", false, new Date(), new Date()));
            add(new Todo(null,"testC", false, new Date(), new Date()));
        }};
        return testList;
    }

    @Test
    void should_get_all_TodoL_when_perform_get() throws Exception {
        //given
        todoRepository.saveAll(getTodoList());
        //when
        client.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].text",containsInAnyOrder("testA","testB","testC")));
        //then
    }
}