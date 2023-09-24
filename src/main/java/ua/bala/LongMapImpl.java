package ua.bala;

import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<V>[] buckets;
    private int size;

    public LongMapImpl() {
        this(DEFAULT_CAPACITY);
    }

    public LongMapImpl(int initialCapacity) {
        buckets = new Entry[initialCapacity];
    }

    @Override
    public V put(long key, V value) {
        if (size + 1 >= buckets.length * LOAD_FACTOR) {
            resize();
        }

        Entry<V> foundEntry = findEntry(key);
        if (foundEntry != null) {
            V oldValue = foundEntry.value;
            foundEntry.value = value;
            return oldValue;
        }

        addEntryToBucket(buckets, key, value);
        size++;
        return null;
    }

    @Override
    public V get(long key) {
        Entry<V> foundEntry = findEntry(key);
        return foundEntry != null ? foundEntry.value : null;
    }

    @Override
    public V remove(long key) {
        int index = indexFor(key, buckets.length);
        Entry<V> prev = null;
        Entry<V> entry = buckets[index];

        while (entry != null) {
            if (entry.key == key) {
                if (prev == null) {
                    buckets[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }

        return null;
    }

    private Entry<V> findEntry(long key) {
        int index = indexFor(key, buckets.length);
        Entry<V> entry = buckets[index];

        while (entry != null) {
            if (entry.key == key) {
                return entry;
            }
            entry = entry.next;
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(long key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (Entry<V> entry : buckets) {
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public long[] keys() {
        long[] keyArray = new long[size];
        int index = 0;

        for (Entry<V> entry : buckets) {
            while (entry != null) {
                keyArray[index] = entry.key;
                entry = entry.next;
            }
        }

        return keyArray;
    }

    @Override
    public V[] values() {
        @SuppressWarnings("unchecked")
        V[] valueArray = (V[]) new Object[size];
        int index = 0;

        for (Entry<V> entry : buckets) {
            while (entry != null) {
                valueArray[index] = entry.value;
                entry = entry.next;
            }
        }

        return valueArray;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    private void resize() {
        int newCapacity = Math.min(buckets.length * 2, Integer.MAX_VALUE);
        @SuppressWarnings("unchecked")
        Entry<V>[] newTable = new Entry[newCapacity];

        for (Entry<V> entry : buckets) {
            while (entry != null) {
                addEntryToBucket(newTable, entry.key, entry.value);
                entry = entry.next;
            }
        }

        buckets = newTable;
    }

    private void addEntryToBucket(Entry<V>[] buckets, long key, V value) {
        int index = indexFor(key, buckets.length);
        Entry<V> newEntry = new Entry<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = newEntry;
            return;
        }
        buckets[index].next = newEntry;
    }

    private int indexFor(long key, int tableLength) {
        return (int) (key & (tableLength - 1));
    }

    private static class Entry<V> {
        long key;
        V value;
        Entry<V> next;

        protected Entry(long key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}

