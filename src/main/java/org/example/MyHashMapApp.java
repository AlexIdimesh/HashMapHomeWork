package org.example;
/**
 * Класс для проверки работоспособности класса {@link MyHashMap}
 */
public class MyHashMapApp {
    public static void main(String[] args) {
        MyHashMap<String,Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("Sasha",10);
        myHashMap.put("Dima",1);
        myHashMap.put("Sveta",100);
        myHashMap.put("Dasha",12);
        myHashMap.put("Kim",15);
    }
}
