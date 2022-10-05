package datastructure;

import java.util.HashMap;
import java.util.Map;


public class LRUCache<K, V> implements Cache<K, V> {

    class Node {
        Node prev;
        Node next;
        K key;
    }

    private final Map<K, V> valueMap = new HashMap<>();
    private final Map<K, Node> nodeMap = new HashMap<>();
    private Node head;
    private Node tail;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void set(K key, V val) {
        valueMap.put(key, val);
        if (nodeMap.size() == 0) {
            Node newNode = new Node();
            newNode.key = key;
            head = newNode;
            tail = newNode;
            nodeMap.put(key, newNode);
        } else {
            if (nodeMap.get(key) == null) {
                Node newNode = new Node();
                newNode.key = key;
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
                nodeMap.put(key, newNode);
            } else {
                renewKey(key);
            }
        }
        if (nodeMap.size() > capacity) {
            nodeMap.remove(head.key);
            valueMap.remove(head.key);
            head = head.next;
            head.prev = null;
        }
    }

    public V get(K key) {
        if (nodeMap.containsKey(key)) {
            renewKey(key);
        }
        return valueMap.get(key);
    }

    @Override
    public Map<K, V> peekValues() {
        return valueMap;
    }

    private void renewKey(K key) {
        Node node = nodeMap.get(key);
        if (node == head) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        node.prev = tail;
        node.next = null;
        tail.next = node;
        tail = node;
    }

    public static void main(String[] args) {
        var cache = new LRUCache<String, Integer>(5);
        nonRepeatTest(cache);
    }

    public static void nonRepeatTest(Cache<String, Integer> cache) {
        cache.set("a", 1);
        cache.set("b", 2);
        cache.set("c", 3);
        cache.set("d", 4);
        cache.set("e", 5);
        cache.set("f", 6);
        System.out.println(cache.peekValues());
        cache.set("g", 7);
        System.out.println(cache.peekValues());
        System.out.println(cache.get("c"));
        cache.set("h", 8);
        System.out.println(cache.peekValues());
        cache.set("i", 9);
        System.out.println(cache.peekValues());
        cache.set("f", 66);
        cache.set("j", 10);
        System.out.println(cache.peekValues());
    }
}
