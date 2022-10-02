package algorithm.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReservoirKSampler {

    private final int capacity;
    private int[] picks;
    private int draws = 0;

    private final Random random = new Random();

    public ReservoirKSampler(int capacity) {
        this.capacity = capacity;
        this.picks = new int[capacity];
    }

    public void draw() {
        ++draws;
        if (draws <= capacity) {
            picks[draws - 1] = draws;
        } else {
            int replace = random.nextInt(draws) + 1; // from [1, draws] get an index to replace
            if (replace <= capacity) { // the probability of this draw being picked is capacity / draws
                picks[replace - 1] = draws;
            }
        }
    }

    public int[] getPicks() {
        return picks;
    }

    public void reset() {
        draws = 0;
        picks = new int[capacity];
    }

    public static void main(String[] args) {
        ReservoirKSampler sampler = new ReservoirKSampler(4);
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 10; j++) {
                sampler.draw();
            }
            for (int pick : sampler.getPicks()) {
                counts.put(pick, counts.getOrDefault(pick, 0) + 1);
            }
            sampler.reset();
        }

        System.out.println(counts);
    }

}
