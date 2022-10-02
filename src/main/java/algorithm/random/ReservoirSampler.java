package algorithm.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReservoirSampler {

    private int draws = 0;
    private Integer pick;

    public void draw() {
        ++draws;
        if (Math.random() <= 1.0 / draws) {
            pick = draws;
        }
    }

    public Integer getPick() {
        return pick;
    }

    public void reset() {
        draws = 0;
        pick = null;
    }

    public static void main(String[] args) {
        ReservoirSampler sampler = new ReservoirSampler();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 10; j++) {
                sampler.draw();
            }
            counts.put(sampler.getPick(), counts.getOrDefault(sampler.getPick(), 0) + 1);
            sampler.reset();
        }

        System.out.println(counts);
    }

}
