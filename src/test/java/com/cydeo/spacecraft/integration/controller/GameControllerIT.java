package com.cydeo.spacecraft.integration.controller;

import com.cydeo.spacecraft.enumtype.Boost;
import com.cydeo.spacecraft.enumtype.Level;
import com.cydeo.spacecraft.model.request.CreateGameRequest;
import com.cydeo.spacecraft.model.response.CreateGameResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class GameControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;// to convert object to JSON

    @Test
    public void
    should_create_game_successfully() throws Exception {

        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setUsername("username");
        createGameRequest.setBoost(Boost.BIG_BOMB);
        createGameRequest.setLevel(Level.EASY);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/v1/game/createGame")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createGameRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").exists())
                .andExpect(jsonPath("$.responseMessage").value("SUCCESS"))
                .andReturn();

        String json = result.getResponse().getContentAsString();
        System.out.println(json);
        CreateGameResponse createGameResponse = objectMapper.readValue(json,CreateGameResponse.class);

        System.out.println(createGameResponse.getGameId());

    }

}
