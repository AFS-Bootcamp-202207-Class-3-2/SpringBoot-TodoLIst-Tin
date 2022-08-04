package com.rest.todolistbackend.controller;

import com.rest.todolistbackend.entity.Todo;
import com.rest.todolistbackend.repository.TodoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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
    void should_get_all_Todo_when_perform_get() throws Exception {
        //given
        todoRepository.saveAll(getTodoList());
        //when
        client.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].text",containsInAnyOrder("testA","testB","testC")));
    }

    @Test
    void should_create_Todo_when_perform_post_given_a_todo() throws Exception {
        //given
        String newTodo = "{\n" +
                "\"text\":\"testA\"" +
                "}";
        //when
        client.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTodo))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text").value("testA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.done").value(false));

        List<Todo> todos = todoRepository.findAll();
        assertThat(todos, hasSize(1));
        assertThat(todos.get(0).getText(), equalTo("testA"));
        assertThat(todos.get(0).getDone(), equalTo(false));
    }

    @Test
    void should_update_Todo_when_perform_put_given_a_todo() throws Exception {
        //given
        String updateTodo = "{\n" +
                "    \"text\":\"testA\",\n" +
                "    \"done\":true" +
                "}";
        Todo todo = todoRepository.save(new Todo(null, "Apple", false, new Date(), new Date()));
        //when
        client.perform(MockMvcRequestBuilders.put("/todos/{id}",todo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateTodo))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text").value("testA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.done").value(true));

        List<Todo> todos = todoRepository.findAll();
        assertThat(todos, hasSize(1));
        assertThat(todos.get(0).getText(), equalTo("testA"));
        assertThat(todos.get(0).getDone(), equalTo(true));
    }

    @Test
    void should_delete_Todo_when_perform_delete_given_a_id() throws Exception {
        //given
        Todo todo = todoRepository.save(new Todo(null, "Apple", false, new Date(), new Date()));

        //when
        client.perform(MockMvcRequestBuilders.delete("/todos/{id}",todo.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNumber());

        //then
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos, hasSize(0));
    }
}