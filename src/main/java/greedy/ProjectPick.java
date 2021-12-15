package greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ProjectPick {
    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Project{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }

    public List<Project> pick(List<Project> input, int initFund, int maxProjects) {
        PriorityQueue<Project> minCostHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        PriorityQueue<Project> maxProfitHeap = new PriorityQueue<>(Comparator.comparingInt((Project o) -> o.profit).reversed());
        minCostHeap.addAll(input);

        int fund = initFund;
        List<Project> result = new ArrayList<>();
        for (int i = 0; i < maxProjects; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= fund) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return result;
            }

            Project project = maxProfitHeap.poll();
            fund += project.profit;
            result.add(project);
        }
        return result;
    }

    public static void main(String[] args) {
        ProjectPick algo = new ProjectPick();
        List<Project> result = algo.pick(List.of(
                        new Project(3, 1),
                        new Project(1, 2),
                        new Project(4, 3),
                        new Project(9, 7),
                        new Project(9, 4)
                ),
                1, 4);
        result.forEach(System.out::println);
    }
}
