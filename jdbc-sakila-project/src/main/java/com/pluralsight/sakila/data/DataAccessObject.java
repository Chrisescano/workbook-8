package com.pluralsight.sakila.data;

public interface DataAccessObject<T, K> {
    T createEntry(T object);
    T readEntry(K key);
    int updateEntry(T object);
    int deleteEntry(T object);
}
