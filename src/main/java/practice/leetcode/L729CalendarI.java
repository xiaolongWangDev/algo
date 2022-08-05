package practice.leetcode;

import java.util.TreeMap;

public class L729CalendarI {

    public static class MyCalendar {

        private final TreeMap<Integer, Integer> map = new TreeMap<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            Integer prevStart = map.floorKey(start);
            Integer nextStart = map.ceilingKey(start);

            if ((prevStart == null || map.get(prevStart) <= start) && (nextStart == null || end <= nextStart)) {
                map.put(start, end);
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        var c = new MyCalendar();
        c.book(1, 1);
        c.book(1, 1);
    }
}
