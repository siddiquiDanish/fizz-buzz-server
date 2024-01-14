package com.fizzbuzz.fizzbuzzserver.controller;

import com.fizzbuzz.fizzbuzzserver.service.FizzBuzzService;
import com.fizzbuzz.fizzbuzzserver.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FizzBuzzController {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Autowired
    StatisticsService statisticsService;

    private static final Logger log = LoggerFactory.getLogger(FizzBuzzController.class);

    @GetMapping(value = "/fizzbuzz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> fizzBuzz(
            @RequestParam(required = true) int int1,
            @RequestParam(required = true) int int2,
            @RequestParam(required = true) int limit,
            @RequestParam(required = true) String str1,
            @RequestParam(required = true) String str2
    ) {

        List<String> result = fizzBuzzService.generateFizzBuzz(int1, int2, limit, str1, str2);
        String requestParametersKey = currentReqWithParameters(int1, int2, limit, str1, str2);
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("integer1", int1);
        reqMap.put("integer2", int2);
        reqMap.put("limit", limit);
        reqMap.put("String1", str1);
        reqMap.put("String2", str2);
        statisticsService.updateStatistics(requestParametersKey, reqMap);
        log.debug("fizzBuzz() : request completed.");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        HashMap<String, Object> mostUsedReq = statisticsService.getMostUsedRequestParameters();
        Integer mostUsedReqHitCount = statisticsService.getNumberOfHits();
        statistics.put("mostUsedRequestParameters",
                mostUsedReq != null ?
                        mostUsedReq : "No request recieved yet");
        statistics.put("mostUsedRequest-hitCount", mostUsedReqHitCount != null ?
                mostUsedReqHitCount : 0);
        log.debug("getStatistics() : request completed.");
        return ResponseEntity.ok(statistics);
    }

    private String currentReqWithParameters(int int1, int int2, int limit, String str1, String str2) {
        return int1 + "-" + int2 + "-" + limit + "-" + str1 + "-" + str2;
    }
}
