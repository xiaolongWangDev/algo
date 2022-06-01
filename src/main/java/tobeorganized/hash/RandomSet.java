package tobeorganized.hash;

import java.util.HashMap;
import java.util.Random;

public class RandomSet<K> {
    private final HashMap<Integer, K> ik = new HashMap<>();
    private final HashMap<K, Integer> ki = new HashMap<>();
    private final Random random = new Random();
    private int count;

    public void add(K key) {
        ik.put(count, key);
        ki.put(key, count++);
    }

    public void delete(K key) {
        Integer index = ki.get(key);
        swap(index, count - 1);
        ik.remove(count - 1);
        ki.remove(key);
        count--;
    }

    private void swap(int aIndex, int bIndex) {
        K a = ik.get(aIndex);
        K b = ik.get(bIndex);
        ki.put(a, bIndex);
        ki.put(b, aIndex);
        ik.put(aIndex, b);
        ik.put(bIndex, a);
    }

    public K getRandom() {
        if (count == 0) {
            return null;
        }
        return ik.get(random.nextInt(count));
    }

    public static void main(String[] args) {
        RandomSet<String> rs = new RandomSet<>();
        rs.add("a");
        rs.add("b");
        rs.add("c");
        rs.add("d");
        rs.add("e");
        rs.add("f");
        rs.add("g");
        rs.delete("a");
        rs.delete("b");
        rs.delete("c");
        rs.delete("d");
        rs.delete("e");
        rs.delete("f");
//        rs.delete("g");
        System.out.println(rs.getRandom());
    }
}
