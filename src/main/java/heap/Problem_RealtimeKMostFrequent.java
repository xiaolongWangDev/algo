package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Problem_RealtimeKMostFrequent {
    private static class StringAndCount {
        String s;
        Integer c;

        public StringAndCount(String s, Integer c) {
            this.s = s;
            this.c = c;
        }
    }

    private static class MagicCollection {
        // default is max root heap
        private final MutableHeap<StringAndCount, String> minHeap = new MutableHeap<>(Comparator.comparingInt((StringAndCount o) -> o.c).reversed(), o -> o.s);
        private final Map<String, Integer> counts = new HashMap<>();

        private final int k;

        public MagicCollection(int k) {
            this.k = k;
        }

        public void add(String s) {
            counts.put(s, counts.getOrDefault(s, 0) + 1);
            // you may doubt here, since Im not keeping track of the existing StringAndCount's,
            // but rather just create a new one. How can this be found?
            // This is OK because the key used is of type String. And it's being used for the contains check
            // for an Object type key, you may need to change it's hashCode and equals logic
            StringAndCount stringAndCount = new StringAndCount(s, counts.get(s));
            if (minHeap.contains(stringAndCount)) {
                minHeap.setValue(s, stringAndCount);
            } else {
                if (minHeap.size() < k) {
                    minHeap.add(stringAndCount);
                } else if (minHeap.peek().c < stringAndCount.c) {
                    minHeap.poll();
                    minHeap.add(stringAndCount);
                }
            }
        }

        public void print() {
            minHeap.getAll().forEach(o -> System.out.printf("%s(%d) ", o.s, o.c));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MagicCollection magicCollection = new MagicCollection(3);
        magicCollection.add("A");
        magicCollection.print();
        magicCollection.add("B");
        magicCollection.print();
        magicCollection.add("A");
        magicCollection.print();
        magicCollection.add("C");
        magicCollection.print();
        magicCollection.add("C");
        magicCollection.print();
        magicCollection.add("B");
        magicCollection.print();
        magicCollection.add("B");
        magicCollection.print();
        magicCollection.add("D");
        magicCollection.print();
        magicCollection.add("D");
        magicCollection.print();
        magicCollection.add("D");
        magicCollection.print();
        magicCollection.add("A");
        magicCollection.print();
        magicCollection.add("A");
        magicCollection.print();
    }
}
