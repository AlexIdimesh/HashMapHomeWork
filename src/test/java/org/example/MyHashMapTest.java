package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyHashMapTest {
    private MyHashMap<Integer,String> map = new MyHashMap<>();

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    public void testPrintHashMap() {
        map.put(1, "one");
        map.put(2, "two");

        map.printHashMap();
    }
    @Test
    public void testKeySet() {
        map.put(1, "one");
        map.put(2, "two");

        assertTrue(map.keySet().contains(1));
        assertTrue(map.keySet().contains(2));
    }
    @Test
    public void testContainsKey() {
        map.put(1, "one");
        map.put(2, "two");

        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(3));
    }
    @Test
    public void testGet() {
        map.put(1, "one");
        map.put(2, "two");

        assertNotNull(map.get(1));
        assertNull(map.get(3));
    }

    @Test
    public void testRemove() {
        map.put(1, "one");
        map.put(2, "two");

        map.remove(1);
        assertEquals(null, map.get(1));
    }

    @Test
    public void testPut() {
        map.put(1, "one");
        map.put(2, "two");

        assertEquals("one", map.get(1));
    }
    @ParameterizedTest
    @CsvSource({
            "1 , one",
            "2, two",
            "3, three",
            "4, four"
    })
    public void testPutWithLargeData(int key, String value){
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
    public void testPrintHashMapWithLargeData(int key, String value) {
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
    public void testKeySetWithLargeData(int key, String value) {
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
    public void testContainsKeyWithLargeData(int key, String value) {
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
    public void testGetWithLargeData(int key, String value) {
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
    public void testRemoveWithLargeData(int key, String value) {
        map.put(key,value);
        map.remove(key);

        assertEquals(null, map.get(key));
    }
}
