package org.example;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Idimeshev Alexander
 * @version 1.0
 * Это моя внутренняя реализая HashMap на языке Java
 * @param <K>  Ключ
 * @param <V>  Значение
 */
public class MyHashMap<K, V> {

    /**
     * Статическое final поле с заданым кол-во бакетов
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Приватное поле, представляющее массив "ведер" (buckets) для хранения элементов
     */
    private Entry<K, V>[] bucketArray;

    /**
     * Приватное поле, представляющее начальное кол-во "ведер" (bucket) для хранения элеметов
     */
    private int numBuckets;

    /**
     * Приватное поле, обозначающее текущий размер
     */
    private int size;

    /**
     * Конструктор
     */
    public MyHashMap() {
        bucketArray = new Entry[INITIAL_CAPACITY];
        numBuckets = INITIAL_CAPACITY;
        size = 0;
    }

    /**
     * Возвращает значение хеш-фукнции по ключу
     * @param key - ключ
     * @return значение хеш-фукции
     */
    private int getBucketIndex(K key) {
        int hash = key.hashCode();
        return hash % numBuckets;
    }

    /**
     * Добавляет значение по заданному ключу.
     * @param key - ключ, по которому хранится значение.
     * @param value - значение, которое необходимо добавить.
     */
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);

        Entry<K, V> current = bucketArray[bucketIndex];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = bucketArray[bucketIndex];
        bucketArray[bucketIndex] = newEntry;
        size++;

        if ((1.0 * size) / numBuckets > 0.7) {
            rehash();
        }
    }

    /**
     * Метод, котороый увеличивый количество бакетов
     * и перезаписывает их
     */
    private void rehash() {
        numBuckets *= 2;
        Entry<K, V>[] tempArray = bucketArray;
        bucketArray = new Entry[numBuckets];
        size = 0;

        for (Entry<K, V> entry : tempArray) {
            Entry<K, V> current = entry;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    /**
     * Возвращает значение для заданного ключа
     * @param key - ключ
     * @return Значение, соответствующее ключу, или null, если ключ отсутствует
     */
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = bucketArray[bucketIndex];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Удаляет значение для заданного ключа
     * @param key - ключ
     * @return Удаленное значение, или null, если ключ отсутствует
     */
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = bucketArray[bucketIndex];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    bucketArray[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Выводит содержимое HashMap (ключи и значения) на экран.
     */
    public void printHashMap() {
        for (int i = 0; i < bucketArray.length; i++) {
            Entry<K, V> current = bucketArray[i];
            while (current != null) {
                System.out.println("Key: " + current.key + ", Value: " + current.value);
                current = current.next;
            }
        }
    }

    /**
     * Возвращает список уникальных ключей в HashMap
     * @return Список ключей
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
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
        Entry<K, V> current = bucketArray[bucketIndex];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
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


