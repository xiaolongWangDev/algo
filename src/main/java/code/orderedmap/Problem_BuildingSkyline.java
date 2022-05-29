package code.orderedmap;

import java.util.*;

public class Problem_BuildingSkyline {

    private static class Event {
        boolean up;
        int index;
        int height;

        public Event(boolean up, int index, int height) {
            this.up = up;
            this.index = index;
            this.height = height;
        }
    }

    public List<List<Integer>> solve(int[][] buildingDims) {
        Event[] events = new Event[buildingDims.length * 2];
        int i = 0;
        for (int[] buildingDim : buildingDims) {
            events[i++] = new Event(true, buildingDim[0], buildingDim[2]);
            events[i++] = new Event(false, buildingDim[1], buildingDim[2]);
        }
        Arrays.sort(events, Comparator.comparing((Event e) -> e.index).thenComparing(e -> e.up));
        TreeMap<Integer, Integer> heightCounts = new TreeMap<>();
        TreeMap<Integer, Integer> indexHeight = new TreeMap<>();
        for (Event event : events) {
            if (event.up) {
                heightCounts.put(event.height, heightCounts.getOrDefault(event.height, 0) + 1);
            } else if (heightCounts.get(event.height) == 1) {
                heightCounts.remove(event.height);
            } else {
                heightCounts.put(event.height, heightCounts.get(event.height) - 1);
            }
            indexHeight.put(event.index, heightCounts.isEmpty() ? 0 : heightCounts.lastKey());
        }

        Integer prevTurnIndex = 0;
        Integer prevHeight = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : indexHeight.entrySet()) {
            if (!prevHeight.equals(entry.getValue())) {
                result.add(List.of(prevTurnIndex, entry.getKey(), prevHeight));
                prevTurnIndex = entry.getKey();
            }
            prevHeight = entry.getValue();
        }
        return result;
    }

    public static void main(String[] args) {
        Problem_BuildingSkyline p = new Problem_BuildingSkyline();
        System.out.println(p.solve(new int[][]{
                {2, 6, 8}, {7, 11, 9}, {4, 8, 5}, {1, 14, 4}, {3, 5, 3}
        }));
    }
}
