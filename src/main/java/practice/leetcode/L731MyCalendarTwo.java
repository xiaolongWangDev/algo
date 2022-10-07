package practice.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class L731MyCalendarTwo {

    class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private final List<Range> overlapRanges = new ArrayList<>();
    private final List<Range> allRanges = new ArrayList<>();


    public boolean book(int start, int end) {
        for (Range r : overlapRanges) {
            if (start < r.end && end > r.start) {
                return false;
            }
        }

        for (Range r : allRanges) {
            if (start < r.end && end > r.start) {
                overlapRanges.add(new Range(Math.max(start, r.start), Math.min(end, r.end)));
            }
        }
        allRanges.add(new Range(start, end));
        return true;
    }
}

class L731MyCalendarTwo2 {

    private final TreeMap<Integer, Integer> levelChanges = new TreeMap<>();

    public boolean book(int start, int end) {
        levelChanges.put(start, levelChanges.getOrDefault(start, 0) + 1);
        levelChanges.put(end, levelChanges.getOrDefault(end, 0) - 1);

        int level = 0;
        for (Map.Entry<Integer, Integer> entry : levelChanges.entrySet()) {
            int moment = entry.getKey();
            int changes = entry.getValue();
            level += changes;
            if (level >= 3) {
                levelChanges.put(start, levelChanges.get(start) - 1);
                levelChanges.put(end, levelChanges.get(end) + 1);
                return false;
            }

            if(moment > end) break;
        }

        return true;
    }
}
