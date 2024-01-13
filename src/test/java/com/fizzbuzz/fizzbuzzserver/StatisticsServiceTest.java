package com.fizzbuzz.fizzbuzzserver;

import com.fizzbuzz.fizzbuzzserver.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StatisticsServiceTest {

    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        statisticsService = new StatisticsService();
    }

    @Test
    public void testUpdateStatistics() {
        String request = "testRequest";
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("param1", "value1");
        paramsMap.put("param2", 42);

        statisticsService.updateStatistics(request, paramsMap);

        assertEquals(1, statisticsService.getNumberOfHits());
        assertEquals(paramsMap, statisticsService.getMostUsedRequestParameters());
    }

    @Test
    public void testGetMostUsedRequestParameters() {
        String request = "testRequest";
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("param1", "value1");
        paramsMap.put("param2", 42);

        statisticsService.updateStatistics(request, paramsMap);

        assertEquals(paramsMap, statisticsService.getMostUsedRequestParameters());
    }

    @Test
    public void testGetNumberOfHits() {
        String request = "testRequest";
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("param1", "value1");
        paramsMap.put("param2", 42);

        statisticsService.updateStatistics(request, paramsMap);

        assertEquals(1, statisticsService.getNumberOfHits());
    }

    @Test
    public void testMostUsedRequestWithNoRequests() {
        assertEquals("No request", statisticsService.mostUsedRequest());
    }
}
