package org.example;

import java.util.ArrayList;
/**
 * @author Idimeshev Alexander
 * @version 1.0
 * Это моя внутренняя реализая HashMap на языке Java
 * @param <K>  Ключ
 * @param <V>  Значение
 */
public class MyHashMap<K, V> {
    /**
     * Приватное поле, представляющее массив "ведер" (buckets) для хранения элементов
     */
    private ArrayList<Entry<K, V>> bucketArray;
    /**
     * Приватное поле, представляющее начальное кол-во "ведер" (bucket) для хранения элеметов
     */
    private int numBuckets;
    /**
     * Приватное поле, обозначающее текущий размер
     */
    private int size;
    /**
     * Конструктор - заполняет ячейки null
     */
    public MyHashMap() {
        bucketArray = new ArrayList<>();
        numBuckets = 16;
        size = 0;
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }

    /**
     * Возвращает значение хеш-фукнции по ключу
     * @param key - ключ
     * @return значение хеш-фукции
     */
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % numBuckets;
    }
    /**
     * Возвращает значение для заданного ключа
     * @param key - ключ
     * @return Значение, соответствующее ключу, или null, если ключ отсутствует
     */
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    /**
     * Возвращает список уникальных ключей в HashMap
     * @return Список ключей
     */
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (Entry<K, V> entry : bucketArray) {
            Entry<K, V> current = entry;
            while (current != null) {
                keys.add(current.key);
                current = current.next;
            }
        }
        return keys;
    }
    /**
     * Проверяет наличие заданного ключа в HashMap
     * @param key - ключ для проверки
     * @return true, если ключ присутствует, иначе - false
     */
    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
    /**
     * Добавляет значение по заданному ключу.
     * @param key - ключ, по которому хранится значение.
     * @param value - значение, которое необходимо добавить.
     */
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        bucketArray.set(bucketIndex, newEntry);

        if ((1.0 * size) / numBuckets > 0.7) {
            ArrayList<Entry<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
            for (Entry<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
    /**
     * Удаляет значение для заданного ключа
     * @param key - ключ
     * @return Удаленное значение, или null, если ключ отсутствует
     */
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> head = bucketArray.get(bucketIndex);
        Entry<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            bucketArray.set(bucketIndex, head.next);
        }
        return head.value;
    }
    /**
     * Выводит содержимое HashMap (ключи и значения) на экран.
     */
    public void printHashMap() {
        for (Entry<K, V> entry : bucketArray) {
            Entry<K, V> current = entry;
            while (current != null) {
                System.out.println("Key: " + current.key + ", Value: " + current.value);
                current = current.next;
            }
        }
    }
    /**
     * Внутренний класс, представляющий элемент данных (ключ-значение).
     * @param <K> Тип ключа.
     * @param <V> Тип значения.
     */
    private static class Entry<K, V> {
        /**
         * Ключ
         */
        final K key;
        /**
         * Значение
         */
        V value;
        /**
         * Ссылка на следующий элемент
         */
        Entry<K, V> next;
        /**
         * Конструктор для создания элемента
         * @param key
         * @param value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

