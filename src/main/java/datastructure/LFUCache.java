package datastructure;

import java.util.HashMap;
import java.util.Map;

import static datastructure.LRUCache.nonRepeatTest;

public class LFUCache<K, V> implements Cache<K, V> {

    class Node {
        Node prev;
        Node next;
        K key;
        int count;
    }

    class Bucket {
        Node head;
        Node tail;
    }

    private final Map<K, V> valueMap = new HashMap<>();
    private final Map<K, Node> nodeMap = new HashMap<>();
    private final Map<Integer, Bucket> buckets = new HashMap<>();

    private int lowestCount = 1;
    private final int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public void set(K key, V val) {
        valueMap.put(key, val);



        // updating existing key
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            removeNodeFromBucket(node);

            // update node
            node.prev = null;
            node.next = null;
            node.count++;
        } else {
            if (nodeMap.size() == capacity) {
                Node nodeToRemove = buckets.get(lowestCount).head;
                K keyToRemove = nodeToRemove.key;
                valueMap.remove(keyToRemove);
                nodeMap.remove(keyToRemove);
                removeNodeFromBucket(nodeToRemove);
            }

            //adding new key
            node = new Node();
            node.key = key;
            node.count = 1;
            lowestCount = 1;

        }
        addNewNode(node);

    }

    public V get(K key) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            removeNodeFromBucket(node);

            // update node
            node.prev = null;
            node.next = null;
            node.count++;
            addNewNode(node);
        }

        return valueMap.get(key);
    }

    @Override
    public Map<K, V> peekValues() {
        return valueMap;
    }

    private void removeNodeFromBucket(Node node) {
        Bucket currentBucket = buckets.get(node.count);
        if (currentBucket.head == node) {
            currentBucket.head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (currentBucket.head == null && node.count == lowestCount) {
            lowestCount++;
        }
    }

    private void addNewNode(Node node) {
        buckets.putIfAbsent(node.count, new Bucket());
        Bucket newBucket = buckets.get(node.count);
        if (newBucket.head == null) {
            newBucket.head = node;
        } else {
            node.prev = newBucket.tail;
            newBucket.tail.next = node;
        }
        newBucket.tail = node;
        nodeMap.put(node.key, node);
    }

    public static void main(String[] args) {
        var cache = new LFUCache<String, Integer>(5);
//        nonRepeatTest(cache);
//        shouldEvictB(cache);
        shouldEvictC(cache);
    }

    public static void shouldEvictB(Cache<String, Integer> cache) {
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("b", 2);
        cache.set("c", 3);
        cache.set("d", 4);
        cache.set("e", 5);
        cache.set("f", 6);
        System.out.println(cache.peekValues());
    }

    public static void shouldEvictC(Cache<String, Integer> cache) {
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("a", 1);
        cache.set("b", 2);
        cache.set("b", 2);
        cache.set("b", 2);
        cache.set("c", 3);
        cache.set("c", 3);
        cache.set("d", 4);
        cache.set("d", 4);
        cache.set("d", 4);
        cache.set("d", 4);
        cache.set("e", 5);
        cache.set("e", 5);
        cache.set("e", 5);
        cache.set("e", 5);
        cache.set("f", 6);
        System.out.println(cache.peekValues());
    }
}
