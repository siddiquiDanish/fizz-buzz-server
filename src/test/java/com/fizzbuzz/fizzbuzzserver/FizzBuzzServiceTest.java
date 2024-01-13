package com.fizzbuzz.fizzbuzzserver;

import com.fizzbuzz.fizzbuzzserver.service.FizzBuzzService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzServiceTest {


    private static FizzBuzzService fizzbuzzService;

    @BeforeAll
    public static void setUp() {
        fizzbuzzService = new FizzBuzzService();
    }

    @Test
    public void testGeneratefizzbuzz() {
        List<String> result1 = fizzbuzzService.generateFizzBuzz(3, 5, 15,
                "fizz", "buzz");
        assertEquals("1", result1.get(0));
        assertEquals("2", result1.get(1));
        assertEquals("fizz", result1.get(2));
        assertEquals("4", result1.get(3));
        assertEquals("buzz", result1.get(4));
        assertEquals("fizz", result1.get(5));
        assertEquals("7", result1.get(6));
        assertEquals("8", result1.get(7));
        assertEquals("fizz", result1.get(8));
        assertEquals("buzz", result1.get(9));
        assertEquals("11", result1.get(10));
        assertEquals("fizz", result1.get(11));
        assertEquals("13", result1.get(12));
        assertEquals("14", result1.get(13));
        assertEquals("fizzbuzz", result1.get(14));
    }
}
