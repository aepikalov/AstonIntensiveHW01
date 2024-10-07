package Collection;

import java.util.LinkedList;
import java.util.TreeMap;

public class HashMapCustom<K, V> {
    private final Object[] array;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int TREEIFY_THRESHOLD = 8;
    private int size;

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMapCustom() {
        array = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    private int hash(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode() % array.length);
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        int index = hash(key) % array.length;
        Object bucket = array[index];

        if (bucket == null) {
            array[index] = new LinkedList<Entry<K, V>>();
            bucket = array[index];
        }

        if (bucket instanceof LinkedList) {
            LinkedList<Entry<K, V>> list = (LinkedList<Entry<K, V>>) bucket;
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }

            list.add(new Entry<>(key, value));
            size++;

            if (list.size() > TREEIFY_THRESHOLD) {
                TreeMap<K, V> tree = new TreeMap<>();
                for (Entry<K, V> entry : list) {
                    tree.put(entry.key, entry.value);
                }
                array[index] = tree;
            }

        } else if (bucket instanceof TreeMap) {
            TreeMap<K, V> tree = (TreeMap<K, V>) bucket;
            tree.put(key, value);
        }
    }

    public V get(K key) {
        int index = hash(key) % array.length;
        Object bucket = array[index];

        if (bucket == null) {
            return null;
        }

        if (bucket instanceof LinkedList) {
            LinkedList<Entry<K, V>> list = (LinkedList<Entry<K, V>>) bucket;
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }

        } else if (bucket instanceof TreeMap) {
            TreeMap<K, V> tree = (TreeMap<K, V>) bucket;
            return tree.get(key);
        }

        return null;
    }

    public void remove(K key) {
        int index = hash(key) % array.length;
        Object bucket = array[index];

        if (bucket == null) {
            return;
        }

        if (bucket instanceof LinkedList) {
            LinkedList<Entry<K, V>> list = (LinkedList<Entry<K, V>>) bucket;
            list.remove(key);
            size--;

        } else if (bucket instanceof TreeMap) {
            TreeMap<K, V> tree = (TreeMap<K, V>) bucket;
            tree.remove(key);
            size--;
        }
    }
}

