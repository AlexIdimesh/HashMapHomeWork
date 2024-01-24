package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyHashMapTest {
    private MyHashMap<Integer,String> map = new MyHashMap<>();

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
    }
    @Test
    void testPrintHashMap() {
        for(int i = 0; i < 100_00; i ++) {
            map.printHashMap();
        }
    }
    @Test
    void testKeySet() {
        for (int i = 0; i < 100_00;i ++) {
            assertTrue(map.keySet().contains(1));
            assertTrue(map.keySet().contains(2));
        }
    }
    @Test
    void testContainsKey() {
        for (int i = 0; i < 100_00; i ++) {
            assertTrue(map.containsKey(1));
            assertFalse(map.containsKey(3));
        }
    }
    @Test
    void testGet() {
        for (int i = 0; i < 100_000; i ++) {
            assertNotNull(map.get(1));
            assertNull(map.get(3));
        }
    }

    @Test
    void testRemove() {
        for (int i = 0; i < 100_00; i ++) {
            map.remove(1);
            assertEquals(null, map.get(1));
        }
    }

    @Test
    void testPut() {
        for (int i = 0; i < 100_000; i ++) {
            assertEquals("one", map.get(1));
        }
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testPutWithLargeData(int key, String value){
        map.put(key,value);
        assertEquals(value, map.get(key));
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testPrintHashMapWithLargeData(int key, String value) {
        map.put(key,value);

        map.printHashMap();
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testKeySetWithLargeData(int key, String value) {
        map.put(key,value);

        assertTrue(map.keySet().contains(key));
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testContainsKeyWithLargeData(int key, String value) {
        map.put(key,value);

        assertTrue(map.containsKey(key));
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testGetWithLargeData(int key, String value) {
        map.put(key,value);

        assertNotNull(map.get(key));
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    void testRemoveWithLargeData(int key, String value) {
        map.put(key,value);
        map.remove(key);

        assertEquals(null, map.get(key));
    }
}
