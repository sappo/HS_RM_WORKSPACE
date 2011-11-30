package org.ks.woerterbuch;

import javax.naming.LimitExceededException;

/**
 * Woerterbuch class.
 * @author Kevin Sapper 2011
 */
public class WoerterBuch {

    private String[] keySet;

    private String[] entrySet;

    private int entryCount;

    private final int capacity;

    public WoerterBuch(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }
        this.capacity = capacity;

        entryCount = 0;
        keySet = new String[this.capacity];
        entrySet = new String[this.capacity];
    }

    /**
     * Put add new entry to woerterbuch.
     * @param key the entry key
     * @param entry the entry
     */
    public void put(String key, String entry) throws LimitExceededException {
        // check if there's capacity left
        if (entryCount == capacity) {
            throw new LimitExceededException("Woerterbuch limit reached!");
        }
        if (entryCount < capacity) {
            keySet[entryCount] = key;
            entrySet[entryCount] = entry;
            entryCount++;
        }
        // only sort if there are at least two entries
        if (entryCount > 1) {
            sortiere();
        }
    }

    /**
     * Get an entry by its key.
     * @param key the key of the entry to return
     * @return the associated entry with the parameter key
     */
    public String get(String key) {
        int keyPos = Funktionen.wortPosition(keySet, key);
        return keyPos == -1 ? null : entrySet[keyPos];
    }

    private void sortiere() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < keySet.length; i++) {
                if (keySet[i - 1] != null && keySet[i] != null) {
                    if (keySet[i - 1].compareTo(keySet[i]) > 0) {
                        swap(keySet, i - 1, i);
                        swap(entrySet, i - 1, i);
                        swapped = true;
                    }
                }
            }
        } while (swapped);
    }

    private void swap(String[] array, int i1, int i2) {
        String temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
