package com.fizzbuzz.fizzbuzzserver.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    private final Map<String, Integer> requestCounts = new HashMap<>();
    private final HashMap<String,HashMap<String,Object>> paramsMap = new HashMap<>();

    public void updateStatistics(String request,  HashMap<String,Object> currentParamsMap) {
        requestCounts.put(request, requestCounts.getOrDefault(request, 0) + 1);
        if(!paramsMap.containsKey(request)){
            paramsMap.put(request, currentParamsMap);
        }
    }

    public  HashMap<String,Object> getMostUsedRequestParameters() {
        return paramsMap.get(this.mostUsedRequest());
    }

    public Integer getNumberOfHits() {
        return requestCounts.get(this.mostUsedRequest());
    }

    public String mostUsedRequest(){
        return requestCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No request");
    }


}
