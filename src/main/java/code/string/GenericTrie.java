package code.string;

import java.util.*;

public class GenericTrie<T extends Comparable<T>> {
    public static class GenericTrieNode<T extends Comparable<T>> {
        int pass;
        int end;
        T id;

        public GenericTrieNode(T id) {
            this.id = id;
        }

        public TreeMap<T, GenericTrieNode<T>> children = new TreeMap<>();

        /* in a NON GC language, this simulates a destruction method */
        public void clear() {
            System.out.printf("node %s is cleared\n", id);
        }
    }

    GenericTrieNode<T> root = new GenericTrieNode<>(null);

    public void addEntity(Iterable<T> entity) {
        GenericTrieNode<T> cur = root;
        cur.pass++;
        for (T element : entity) {
            if (!cur.children.containsKey(element)) {
                cur.children.put(element, new GenericTrieNode<>(element));
            }
            cur = cur.children.get(element);
            cur.pass++;
        }
        cur.end++;
    }

    public int countEntity(Iterable<T> entity) {
        GenericTrieNode<T> cur = root;
        for (T element : entity) {
            if (!cur.children.containsKey(element)) {
                return 0;
            }
            cur = cur.children.get(element);
        }
        return cur.end;
    }

    public int countPrefix(Iterable<T> entityPrefix) {
        GenericTrieNode<T> cur = root;
        for (T element : entityPrefix) {
            if (!cur.children.containsKey(element)) {
                return 0;
            }
            cur = cur.children.get(element);
        }
        return cur.pass;
    }

    public void deleteEntityWithCleanup(Iterable<T> entity) {
        if (countEntity(entity) == 0) return;

        GenericTrieNode<T> cur = root;
        cur.pass--;
        for (T element : entity) {
            GenericTrieNode<T> child = cur.children.get(element);
            if (child.pass == 1) {
                cur.children.remove(element);
            }
            if (cur.pass == 0) {
                cur.clear();
            }
            cur = child;
            cur.pass--;

        }
        cur.end--;
        if (cur.pass == 0) {
            cur.clear();
        }
    }

    public void deleteEntity(Iterable<T> entity) {
        if (countEntity(entity) == 0) return;

        GenericTrieNode<T> cur = root;
        cur.pass--;
        for (T element : entity) {
            GenericTrieNode<T> child = cur.children.get(element);
            if (child.pass == 1) {
                cur.children.remove(element);
                return;
            }
            cur = child;
            cur.pass--;
        }
        cur.end--;
    }

    public static void main(String[] args) {
        GenericTrie<Character> trie = new GenericTrie<>();
        trie.addEntity(b("abc"));
        trie.addEntity(b("abcd"));
        trie.addEntity(b("bcd"));
        trie.addEntity(b("abc"));
        System.out.println(trie.countEntity(b("ab")));
        System.out.println(trie.countEntity(b("abc")));
        System.out.println(trie.countEntity(b("abcd")));
        System.out.println(trie.countPrefix(b("a")));
        System.out.println(trie.countPrefix(b("abcde")));
        trie.deleteEntityWithCleanup(b("abc"));
        System.out.println(trie.countEntity(b("abc")));
        System.out.println(trie.countPrefix(b("ab")));
        trie.deleteEntityWithCleanup(b("abc"));
        System.out.println(trie.countEntity(b("abc")));
        System.out.println(trie.countPrefix(b("ab")));
        System.out.println(trie.countEntity(b("abcd")));
        trie.deleteEntityWithCleanup(b("abcd"));
        System.out.println(trie.countPrefix(b("a")));
    }

    private static List<Character> b(String s) {
        List<Character> r = new ArrayList<>();
        for (char c : s.toCharArray()) r.add(c);
        return r;
    }

}
