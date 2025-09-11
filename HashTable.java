import java.util.*;

public class HashTable<K, V> {
    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] table;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        // Creating a generic array with a cast
        this.table = (LinkedList<Entry>[]) new LinkedList[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update value
                return;
            }
        }

        table[index].add(new Entry(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] == null) {
            return null;
        }

        for (Entry entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        if (table[index] == null) {
            return null;
        }

        Iterator<Entry> it = table[index].iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            if (entry.key.equals(key)) {
                V value = entry.value;
                it.remove();
                size--;
                return value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry entry : table[i]) {
                    System.out.println(entry.key + " => " + entry.value);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);
        hashTable.put("Alice", 30);
        hashTable.put("Bob", 25);
        hashTable.put("Charlie", 35);

        System.out.println("Size: " + hashTable.size());

        System.out.println("Get Alice: " + hashTable.get("Alice"));
        System.out.println("Get Bob: " + hashTable.get("Bob"));
        System.out.println("Get Charlie: " + hashTable.get("Charlie"));

        hashTable.remove("Bob");
        System.out.println("Size after removal: " + hashTable.size());

        hashTable.printAll();
    }
}
