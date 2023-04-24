    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CollisionChainedHashMap;

import Utils.SlotOccupiedException;
import java.util.LinkedList;

/**
 *
 * @author D00216500
 */
public class hashMap {
    private static final int DEFAULT_CAPACITY = 103;
    //DO NOT MAKE LINKEDLIST LIKE THIS
    private LinkedList<Entry> [] data;
    private int size;
    
    public hashMap(){
        data = new LinkedList[DEFAULT_CAPACITY];
    }
    
    public hashMap(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Map capacity cannot be less than 1");
        }
        data = new LinkedList[capacity];
    }
    
    public int size(){
        return size;
    }
    
    private int hash(String key){
        int hash = key.hashCode();
        hash = Math.abs(hash);
        hash = hash % data.length;
        return hash;
    }
    
    public String put(String key, String value){
        if(key == null || value == null){
            throw new IllegalArgumentException("Null fields not permitted");
        }
        
        int slot = hash(key);
        
        if(data[slot] == null){
            // If it's a new entry to an empty slot
            data[slot] = new LinkedList();
            Entry newEntry = new Entry(key, value);
            data[slot].add(newEntry);
            size++;
            return null;
        }else{
            // If it's an update
            for(Entry e : data[slot]){
                if(e.key.equals(key)){
                    String oldValue = e.updateValue(value);
                    return oldValue;
                }
            }
            // Add collision
            Entry newEntry = new Entry(key, value);
            data[slot].add(newEntry);
            size++;
            return null;
        }
    }
    
    public String get(String key){
        int slot = hash(key);
        if(data[slot] != null){
            for(Entry e: data[slot]){
                if(e.key.equals(key)){
                    return e.value;
                }
            }
            return null;
        }else{
            return null;
        }
    }
    
    private static class Entry{
        private final String key;
        private String value;
        
        public Entry(String key, String value){
            this.key = key;
            this.value = value;
        }
        
        public String getKey(){
            return key;
        }
        public String getValue(){
            return value;
        }
        public String updateValue(String newValue){
            String oldValue = value;
            this.value = newValue;
            return oldValue;
        }
    }
    
}
