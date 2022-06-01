package tobeorganized.graph;

import java.util.*;
import java.util.stream.Collectors;

import static tobeorganized.graph.GraphUtils.*;

public class Problem_MaxProfitMinDays {
    public ProfitAndDays find(Node targetNode, int maxDays) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(targetNode);
        Map<Node, List<ProfitAndDays>> cumulated = new HashMap<>();
        cumulated.put(targetNode, List.of((ProfitAndDays) targetNode.data));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            ProfitAndDays curProfitAndDays = (ProfitAndDays) cur.data;
            List<ProfitAndDays> candidates = new ArrayList<>();
            for (Link outgoing : cur.outgoing) {
                if (cumulated.containsKey(outgoing.to)) {
                    for (ProfitAndDays profitAndDays : cumulated.get(outgoing.to)) {
                        if (curProfitAndDays.days + profitAndDays.days <= maxDays) {
                            candidates.add(new ProfitAndDays(curProfitAndDays, profitAndDays));
                        }
                    }
                }
            }

            cumulated.put(cur, sortMonotoneAndRemoveRedundant(candidates, cumulated.get(cur)));

            List<Link> incomingLinks = cur.getIncoming();
            for (Link incomingLink : incomingLinks) {
                queue.add(incomingLink.from);
            }
        }

        List<ProfitAndDays> result = sortMonotoneAndRemoveRedundant(cumulated.values().stream().flatMap(Collection::stream).collect(Collectors.toList()), null);
        return result.isEmpty() ? null : result.get(0);

    }

    private List<ProfitAndDays> sortMonotoneAndRemoveRedundant(List<ProfitAndDays> newProfitAndDays, List<ProfitAndDays> existingProfitAndDays) {
        List<ProfitAndDays> result = new ArrayList<>(newProfitAndDays);
        if (existingProfitAndDays != null) {
            result.addAll(existingProfitAndDays);
        }
        result.sort(Comparator.comparing(o -> o.profit, Comparator.reverseOrder()));

        Iterator<ProfitAndDays> iter = result.iterator();
        ProfitAndDays prev = null;
        while (iter.hasNext()) {
            ProfitAndDays cur = iter.next();
            if (prev != null) {
                if (cur.days >= prev.days) {
                    iter.remove();
                }
            }

            prev = cur;
        }

        return result;
    }

    private static class ProfitAndDays {
        int profit;
        int days;

        public ProfitAndDays(int profit, int days) {
            this.profit = profit;
            this.days = days;
        }

        public ProfitAndDays(ProfitAndDays one, ProfitAndDays another) {
            this.profit = one.profit + another.profit;
            this.days = one.days + another.days;
        }

        @Override
        public String toString() {
            return "ProfitAndDays{" +
                    "profit=" + profit +
                    ", days=" + days +
                    '}';
        }
    }

    public static void main(String[] args) {
        Integer[][] adjMatrix = new Integer[][]{
                {0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
        Graph testData = fromAdjacencyMatrix(adjMatrix);
        testData.nodes.get(1).data = new ProfitAndDays(2000, 3);
        testData.nodes.get(2).data = new ProfitAndDays(4000, 3);
        testData.nodes.get(3).data = new ProfitAndDays(2500, 2);
        testData.nodes.get(4).data = new ProfitAndDays(1600, 1);
        testData.nodes.get(5).data = new ProfitAndDays(3800, 4);
        testData.nodes.get(6).data = new ProfitAndDays(2600, 2);
        testData.nodes.get(7).data = new ProfitAndDays(4000, 4);
        testData.nodes.get(8).data = new ProfitAndDays(3500, 3);

        Problem_MaxProfitMinDays p = new Problem_MaxProfitMinDays();
        System.out.println(p.find(testData.nodes.get(8), 10));
    }
}
