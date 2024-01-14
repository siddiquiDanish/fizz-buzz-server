package com.fizzbuzz.fizzbuzzserver.service;

import com.fizzbuzz.fizzbuzzserver.controller.FizzBuzzController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    private final Map<String, Integer> requestCounts = new HashMap<>();
    private final HashMap<String,HashMap<String,Object>> paramsMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(StatisticsService.class);

    /**
     * Method to update request statistics
     * @param requestKey - key with request params
     * @param currentParamsMap - Map with all the  params for current req
     **/
    public void updateStatistics(String requestKey,  HashMap<String,Object> currentParamsMap) {
        requestCounts.put(requestKey, requestCounts.getOrDefault(requestKey, 0) + 1);
        if(!paramsMap.containsKey(requestKey)){
            paramsMap.put(requestKey, currentParamsMap);
            log.debug("updateStatistics() : current request has new parameters.");
        }
    }

    /**
     * Method to returns parameters for most used request.
     **/
    public  HashMap<String,Object> getMostUsedRequestParameters() {
        return paramsMap.get(this.mostUsedRequest());
    }

    /**
     * Method to get count of most used request.
     **/
    public Integer getNumberOfHits() {
        return requestCounts.get(this.mostUsedRequest());
    }

    /**
     * Method to get key for most used request.
     **/
    private String mostUsedRequest(){
        return requestCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No request");
    }


}
