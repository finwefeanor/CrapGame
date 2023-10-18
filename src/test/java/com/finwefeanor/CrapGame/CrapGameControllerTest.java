package com.finwefeanor.CrapGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Unit tests for the CrapGameController.
 * These tests use MockMvc to simulate HTTP requests and validate the responses.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CrapGameControllerTest  {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the behavior when playing multiple rounds with a valid request.
     */
    @Test
    public void testPlayMultipleRounds() throws Exception {
        this.mockMvc.perform(post("/play/multi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"games\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStake").value(5));
    }

    /**
     * Tests the behavior when an invalid games count is provided.
     * The system should respond with a BadRequest (400) status.
     */
    @Test
    public void testInvalidGamesCount() throws Exception {
        this.mockMvc.perform(post("/play/multi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"games\": 200}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("games"));
    }


}
