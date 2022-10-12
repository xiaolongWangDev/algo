package practice.zuosbook.chapter6;

// there are 2 Billion numbers. We can only use 2 GB memory.
// find the most Frequent number. Number range is 4B Integer.
//
// because we need to count freq, cannot use bitmap.
// we need to use a hashmap, key type is an integer, the value type needs to accommodate 2 billion numbers. 4B integer has
// 2^32 ~= 4 * 10^9. So we also use integer for value type. Then each entry is 8 Byte long.
// if we have all different values the mem is 2 * 10^9 * 8 Byte ~= 16 GB.
// for easier calculation and stay safe within 2 GB mem limit.
// we can divide the numbers into 16 groups. And we process 1 group at a time. The distinct number count in each group
// is supposed to be 125 M, which results in a 1 GB hashmap size.

import java.util.*;
import java.util.stream.Collectors;

public class FindMostFrequentIn2Billion {

    // this simulates we store the data on disk
    static class FakeFile {
        List<Integer> storage = new ArrayList<>();

        void write(Integer num) {
            storage.add(num);
        }

        Iterator<Integer> getIterator() {
            return storage.iterator();
        }
    }

    public int solve(Iterator<Integer> allData) {
        FakeFile[] fakeFiles = new FakeFile[16];
        while (allData.hasNext()) {
            int num = allData.next();
            int fileIndex = goodHashFunction(num);
            if (fakeFiles[fileIndex] == null) {
                fakeFiles[fileIndex] = new FakeFile();
            }
            fakeFiles[fileIndex].write(num);
        }

        int max = 0;
        int maxNum = 0;
        for (FakeFile fakeFile : fakeFiles) {
            if (fakeFile != null) {
                Map<Integer, Integer> counts = new HashMap<>();
                var iter = fakeFile.getIterator();
                while (iter.hasNext()) {
                    int num = iter.next();
                    counts.put(num, counts.getOrDefault(num, 0) + 1);
                    if (counts.get(num) > max) {
                        max = counts.get(num);
                        maxNum = num;
                    }
                }
            }
        }

        return maxNum;
    }

    private int goodHashFunction(Integer num) {
        return num % 16;
    }

    public static void main(String[] args) {
        List<Integer> testData = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            testData.add(r.nextInt(30));
        }
        var f = new FindMostFrequentIn2Billion();
        System.out.println(testData.stream().collect(Collectors.groupingBy(o -> o, Collectors.counting())));
        System.out.println(f.solve(testData.iterator()));
    }

}
