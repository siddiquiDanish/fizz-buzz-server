package com.fizzbuzz.fizzbuzzserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FizzBuzzService {

    private static final Logger log = LoggerFactory.getLogger(FizzBuzzService.class);

    /**
     * Method containing logic to generate fizz-buzz
     * @param int1 - integer1
     * @param int2 - integer2
     * @param limit
     * @param str1 - string1
     * @param str2  - string2
     **/
    public List<String> generateFizzBuzz(int int1, int int2, int limit, String str1, String str2) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            if (i % (int1 * int2) == 0) {
                result.add(str1 + str2);
            } else if (i % int1 == 0) {
                result.add(str1);
            } else if (i % int2 == 0) {
                result.add(str2);
            } else {
                result.add(String.valueOf(i));
            }
        }
        log.debug("generateFizzBuzz() : fizz buzz conversion completed.");
        return result;
    }
}
