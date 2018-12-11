package com.tech.task.soccer.soccer.manager;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tech.task.soccer.manager.SoccerManagerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoccerManagerApplication.class)
@AutoConfigureMockMvc
public class SoccerManagerApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllUsers_sizeIs13() throws Exception {
        mvc.perform(get("/player/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(13)));
    }


    @Test
    public void getUserWithId1_NameISJohn() throws Exception {
        mvc.perform(get("/player/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.teamId", is(1)))
                .andExpect(jsonPath("$.position", is("CD")))
                .andExpect(jsonPath("$.playerNumber", is(12)));
    }
}
