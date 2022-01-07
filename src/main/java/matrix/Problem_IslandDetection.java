package matrix;

import set.UnionFindSet;

import java.util.*;
import java.util.function.BiConsumer;

public class Problem_IslandDetection {

    public static class Bounds {
        int U;
        int D;
        int L;
        int R;

        public Bounds(int u, int d, int l, int r) {
            U = u;
            D = d;
            L = l;
            R = r;
        }
    }

    /**
     * in our matrix,
     * X goes down
     * Y goes right
     */
    public static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    public static class Island {

    }

    public Map<Island, List<Coordinate>> detect(int[][] nodeMatrix, Bounds bounds) {
        Map<Island, List<Coordinate>> nodesOnEdge = new HashMap<>();

        for (int i = bounds.U; i < bounds.D; i++) {
            for (int j = bounds.L; j < bounds.R; j++) {
                if (nodeMatrix[i][j] == 1) {
                    Island island = new Island();
                    nodesOnEdge.put(island, new ArrayList<>());
                    infect(nodeMatrix, i, j, bounds, (x, y) -> nodesOnEdge.get(island).add(new Coordinate(x, y)));
                }
            }
        }
        return nodesOnEdge;
    }

    private void infect(int[][] nodeMatrix, int i, int j, Bounds bounds, BiConsumer<Integer, Integer> edgeNodeConsumer) {
        if (i < bounds.U || i >= bounds.D || j < bounds.L || j >= bounds.R || nodeMatrix[i][j] != 1) {
            return;
        }
        if (onTheEdge(i, j, bounds)) {
            edgeNodeConsumer.accept(i, j);
        }

        nodeMatrix[i][j] = 2;
        infect(nodeMatrix, i - 1, j, bounds, edgeNodeConsumer);
        infect(nodeMatrix, i + 1, j, bounds, edgeNodeConsumer);
        infect(nodeMatrix, i, j - 1, bounds, edgeNodeConsumer);
        infect(nodeMatrix, i, j + 1, bounds, edgeNodeConsumer);
    }

    public Map<Island, List<Coordinate>> mergeResults(Map<Island, List<Coordinate>> result1, Map<Island, List<Coordinate>> result2, Bounds bounds1, Bounds bounds2) {
        Map<Coordinate, Island> coordinatesToMap1 = new HashMap<>();
        Map<Coordinate, Island> coordinatesToMap2 = new HashMap<>();

        Map<Island, List<Coordinate>> result = new HashMap<>();
        List<Island> allIslands = new ArrayList<>();
        allIslands.addAll(result1.keySet());
        allIslands.addAll(result2.keySet());
        UnionFindSet<Island> ufs = new UnionFindSet<>(allIslands);

        result1.forEach((island, coordinates) -> coordinates.forEach((cord -> coordinatesToMap1.put(cord, island))));
        result2.forEach((island, coordinates) -> coordinates.forEach((cord -> coordinatesToMap2.put(cord, island))));

        // iterator all edge nodes on map1, if it's adjacent to a node on map2, try merge the 2 islands
        findOnIsland(coordinatesToMap1, coordinatesToMap2, ufs);
        findOnIsland(coordinatesToMap2, coordinatesToMap1, ufs);

        // now prepare the result
        result1.forEach((island, coords) -> {
            Island head = ufs.findHead(island);
            if (!result.containsKey(head)) {
                result.put(head, new ArrayList<>());
            }
            result.get(head).addAll(coords);
        });

        result2.forEach((island, coords) -> {
            Island head = ufs.findHead(island);
            if (!result.containsKey(head)) {
                result.put(head, new ArrayList<>());
            }
            result.get(head).addAll(coords);
        });

        // the islands are correct now, let remove nodes that are no longer on edges
        // it's an improvement. not required
        // to make it simpler, we require the merge to always result in a rectangle. No L shape or other odd shapes.
        // this is doable if we control the divide/merge process
        Bounds newBound = new Bounds(Math.min(bounds1.U, bounds2.U), Math.max(bounds1.D, bounds2.D), Math.min(bounds1.L, bounds2.L), Math.max(bounds1.R, bounds2.R));
        result.forEach((island, coords) -> coords.removeIf(coord -> !onTheEdge(coord.x, coord.y, newBound)));

        return result;
    }

    private boolean onTheEdge(int x, int y, Bounds bounds) {
        return x == bounds.U || x == bounds.D - 1 || y == bounds.L || y == bounds.R - 1;
    }

    private void findOnIsland(Map<Coordinate, Island> coordinatesToMap1, Map<Coordinate, Island> coordinatesToMap2, UnionFindSet<Island> ufs) {
        for (Coordinate coordinate : coordinatesToMap1.keySet()) {
            Island myIsland = coordinatesToMap1.get(coordinate);
            tryMerge(ufs, myIsland, coordinatesToMap2.get(new Coordinate(coordinate.x, coordinate.y + 1)));
            tryMerge(ufs, myIsland, coordinatesToMap2.get(new Coordinate(coordinate.x, coordinate.y - 1)));
            tryMerge(ufs, myIsland, coordinatesToMap2.get(new Coordinate(coordinate.x - 1, coordinate.y)));
            tryMerge(ufs, myIsland, coordinatesToMap2.get(new Coordinate(coordinate.x + 1, coordinate.y)));
        }
    }

    private void tryMerge(UnionFindSet<Island> ufs, Island myIsland, Island otherIsland) {
        if (otherIsland != null) {
            if (!ufs.isInSameSet(myIsland, otherIsland)) {
                ufs.union(myIsland, otherIsland);
            }
        }
    }

    public static void main(String[] args) {

//        int[][] testMatrix = {
//                {0, 1, 0, 0, 0},
//                {0, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0},
//                {1, 0, 1, 1, 0},
//                {1, 0, 0, 0, 0}
//        };
        int[][] testMatrix = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        Problem_IslandDetection p = new Problem_IslandDetection();
        Bounds leftMapBounds = new Bounds(0, 7, 0, 3);
        Map<Island, List<Coordinate>> leftResult = p.detect(testMatrix, leftMapBounds);
        Bounds rightMapBounds = new Bounds(0, 7, 3, 5);
        Map<Island, List<Coordinate>> rightResult = p.detect(testMatrix, rightMapBounds);
        System.out.println(leftResult);
        System.out.println(rightResult);
        System.out.println(p.mergeResults(leftResult, rightResult, leftMapBounds, rightMapBounds));

    }
}
