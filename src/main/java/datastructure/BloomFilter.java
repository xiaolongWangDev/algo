package datastructure;

import java.util.List;
import java.util.function.Function;

public class BloomFilter {

    private final BitMap bitMap;
    private final List<Function<String, Integer>> hashFunctions;

    public BloomFilter(int len, List<Function<String, Integer>> hashFunctions) {
        this.bitMap = new BitMap(len);
        this.hashFunctions = hashFunctions;
    }

    public void add(String s) {
        for (var hashFunction : hashFunctions) {
            int bitToSet = hashFunction.apply(s) % bitMap.getLen();
            bitMap.set(bitToSet);
        }
    }

    public boolean isAdded(String s) {
        for (var hashFunction : hashFunctions) {
            int bitToSet = hashFunction.apply(s) % bitMap.getLen();
            if (!bitMap.get(bitToSet)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        var bloomFilter = new BloomFilter(100, List.of((s) -> s.hashCode(), (s) -> s.hashCode() + 1));

        System.out.println(bloomFilter.isAdded("b"));
        System.out.println(bloomFilter.isAdded("c"));
        bloomFilter.add("c");
        System.out.println(bloomFilter.isAdded("b"));
        System.out.println(bloomFilter.isAdded("c"));
        bloomFilter.add("a");
        System.out.println(bloomFilter.isAdded("b")); // false positive
        System.out.println(bloomFilter.isAdded("c"));
        System.out.println(bloomFilter.isAdded("a"));
    }
}
