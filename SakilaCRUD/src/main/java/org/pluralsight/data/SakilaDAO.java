package org.pluralsight.data;

public interface SakilaDAO <T, K> {
    //create, read, update, delete

    T createEntry(T object);
    T readEntry(K key);
    int updateEntry(T object);
    int deleteEntry(T object);
}
