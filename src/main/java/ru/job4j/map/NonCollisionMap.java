package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }

        int indexOfBucket = getIndexOfBucket(key);

        if (table[indexOfBucket] == null) {
            table[indexOfBucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V get(K key) {

        int indexOfBucket = getIndexOfBucket(key);

        if (table[indexOfBucket] != null) {
            MapEntry<K, V> mapEntry = table[indexOfBucket];
            int hashCode = Objects.hashCode(key);

            if (hashCode == Objects.hashCode(mapEntry.key)) {

                if (Objects.equals(mapEntry.key, key)) {
                    return mapEntry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {

        int indexOfBucket = getIndexOfBucket(key);

        if (table[indexOfBucket] != null) {
            MapEntry<K, V> mapEntry = table[indexOfBucket];
            int hashCode = Objects.hashCode(key);

            if (hashCode == Objects.hashCode(mapEntry.key)) {

                if (Objects.equals(mapEntry.key, key)) {
                    table[indexOfBucket] = null;
                    count--;
                    modCount++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (MapEntry<K, V> entry : table) {

            if (entry != null) {
                K key = entry.key;
                V value = entry.value;

                int indexOfBucket = getIndexOfBucketForNewCapacity(key, newCapacity);
                newTable[indexOfBucket] = new MapEntry<>(key, value);
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    private int getIndexOfBucket(K key) {
        int hashCode = Objects.hashCode(key);
        return indexFor(hash(hashCode));
    }

    private int getIndexOfBucketForNewCapacity(K key, int newCapacity) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        return hash % newCapacity;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();

        System.out.println("hash(0): " + map.hash(0));
        System.out.println("hash(65535): " + map.hash(65535));
        System.out.println("hash(65536): " + map.hash(65536));

        System.out.println("indexFor(0): " + map.indexFor(0));
        System.out.println("indexFor(7): " + map.indexFor(7));
        System.out.println("indexFor(8): " + map.indexFor(8));
    }
}
