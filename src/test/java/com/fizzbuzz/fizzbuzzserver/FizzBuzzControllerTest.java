package com.fizzbuzz.fizzbuzzserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.fizzbuzzserver.controller.FizzBuzzController;
import com.fizzbuzz.fizzbuzzserver.service.FizzBuzzService;
import com.fizzbuzz.fizzbuzzserver.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FizzBuzzController.class)
@AutoConfigureMockMvc
public class FizzBuzzControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FizzBuzzService fizzBuzzService;

    @MockBean
    private StatisticsService statisticsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFizzBuzzEndpoint() throws Exception {
        int int1 = 3;
        int int2 = 5;
        int limit = 15;
        String str1 = "Fizz";
        String str2 = "Buzz";

        List<String> fizzBuzzResult = Arrays.asList("1", "2", "Fizz", "4", "Buzz",
                "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");

        when(fizzBuzzService.generateFizzBuzz(anyInt(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(fizzBuzzResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                        .param("int1", String.valueOf(int1))
                        .param("int2", String.valueOf(int2))
                        .param("limit", String.valueOf(limit))
                        .param("str1", str1)
                        .param("str2", str2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(fizzBuzzResult.size()));

    }

    @Test
    public void testGetStatisticsEndpoint() throws Exception {
        when(statisticsService.getMostUsedRequestParameters()).thenReturn(new HashMap<>());
        when(statisticsService.getNumberOfHits()).thenReturn(5);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mostUsedRequestParameters").exists())
                .andExpect(jsonPath("$.mostUsedReqHitCount").value(5));
    }
}
