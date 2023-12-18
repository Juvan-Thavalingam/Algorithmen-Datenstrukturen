package ch.zhaw.ads;

import java.util.*;

@SuppressWarnings("unchecked")
public class MyHashtable<K, V> implements Map<K, V> {
    private K[] keys = (K[]) new Object[10];
    private V[] values = (V[]) new Object[10];

    private int count = 0;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
        // to be done
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    // Removes all mappings from this map (optional operation).
    public void clear() {
        // to be done
        keys = (K[]) new Object[keys.length];
        values = (V[]) new Object[values.length];
    }

    // Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        // to be done
        int h = hash(key);
        if (key.hashCode() > keys.length) extendHashtable(key.hashCode());

        for (K k : keys) {
            if (k == key) return value;
        }

        if (keys[h] == null) {
            keys[h] = key;
            values[h] = value;
            return value;
        } else if (keys[h] != null) {
            int start = keys[h].hashCode();
            for(int i = start; i < keys.length; i++) {
                if (keys[i] == key) return value;
                if (keys[i] == null) {
                    keys[i] = key;
                    values[i] = value;
                    return value;
                }
            }
        }
        return null;
    }

    private void extendHashtable(int newLength) {
        K[] newKeys = (K[]) new Object[newLength];
        V[] newValues = (V[]) new Object[newLength];
        for (int i = 0; i < keys.length; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    // Returns the value to which this map maps the specified key.
    public V get(Object key) {
        // to be done
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == key) {
                return values[i];
            }
        }

        return null;
    }

    // Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        // to be done (Aufgabe 3)
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == key) {
                keys[i] = null;
                values[i] = null;
                return values[i];
            }
        }
        return null;
    }

    // Returns the number of key-value mappings in this map.
    public int size() {
        // to be done
        return (int) Arrays.stream(keys).filter(Objects::nonNull).count();
    }

    // UnsupportedOperationException ===================================================================
    // Returns a collection view of the values contained in this map.
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    // Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    // Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    // Returns a set view of the mappings contained in this map.
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    // Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    // Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map<? extends K, ? extends V> t) {
        throw new UnsupportedOperationException();
    }

    // Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    // Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    // Returns a set view of the keys contained in this map.
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
}
