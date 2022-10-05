package datastructure;

import java.util.Map;

public interface Cache<K, V> {
    void set(K key, V val);
    V get(K key);

    // for testing
    Map<K, V> peekValues();
}
