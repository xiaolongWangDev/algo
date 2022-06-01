package tobeorganized.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem_ProgramFitter {
    private static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public List<Program> arrange(List<Program> programs) {
        programs.sort(Comparator.comparingInt(o -> o.end));
        int cur = 0;
        List<Program> result = new ArrayList<>();
        for (Program program : programs) {
            if (program.start >= cur) {
                cur = program.start;
                result.add(program);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem_ProgramFitter algo = new Problem_ProgramFitter();
        List<Program> result = algo.arrange(new ArrayList<>(List.of(new Program(0, 100), new Program(2, 3), new Program(5, 80), new Program(10, 30), new Program(6, 9))));
        result.forEach(System.out::println);
    }
}
