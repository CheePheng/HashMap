/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author D00216500
 */
public class HashMap {

    private static final int DEFAULT_CAPACITY = 20;
    private Entry[] data;
    private int size;

    public HashMap() {
        data = new Entry[DEFAULT_CAPACITY];
    }

    public HashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Map capacity cannot be less than 1");
        }
        data = new Entry[capacity];
    }

    public int size() {
        return size;
    }

    private int hash(String key) {
        int hash = key.hashCode();
        hash = Math.abs(hash);
        hash = hash % data.length;
        return hash;
    }

    public String put(String key, String value) {
        int slot = hash(key);
        if (data[slot] == null) {
            Entry newEntry = new Entry(key, value);
            data[slot] = newEntry;
            size++;
            return null;
        } else {
            String oldValue = data[slot].updateValue(value);
            return oldValue;
        }
    }

    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int slot = hash(key);
        if (data[slot] == null) {
            return null;
        } else {
            return data[slot].value;
        }

    }

    private static class Entry {

        private String key;
        private String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String updateValue(String newValue) {
            String oldValue = value;
            this.value = newValue;
            return oldValue;
        }
    }

}
