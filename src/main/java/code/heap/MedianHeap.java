package code.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianHeap<T> {
    private PriorityQueue<T> maxHeap;
    private PriorityQueue<T> minHeap;
    private Comparator<T> comparator;

    public MedianHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.maxHeap = new PriorityQueue<>(comparator.reversed());
        this.minHeap = new PriorityQueue<>(comparator);
    }

    public void add(T element) {
        if ((maxHeap.size() == 0 && minHeap.size() == 0)
                || (comparator.compare(element, minHeap.peek()) > 0)) {
            minHeap.add(element);
        } else {
            maxHeap.add(element);
        }
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        } else if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }

    }

    public T getMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        // when there are even number of elements, median is the one on the lower side
        return maxHeap.peek();
    }

    public T pollMedian() {
        T result;
        if (minHeap.size() > maxHeap.size()) {
            result = minHeap.poll();
        } else {
            result = maxHeap.poll();
        }

        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        } else if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        MedianHeap<Integer> algo = new MedianHeap<>(Comparator.comparingInt(o -> o));
        algo.add(1);
        System.out.println(algo.getMedian());
        algo.add(2);
        System.out.println(algo.getMedian());
        algo.add(3);
        System.out.println(algo.getMedian());
        algo.add(4);
        System.out.println(algo.getMedian());
        algo.add(5);
        System.out.println(algo.getMedian());
        System.out.println(algo.pollMedian());
        System.out.println(algo.pollMedian());
        System.out.println(algo.pollMedian());
        System.out.println(algo.pollMedian());
        System.out.println(algo.pollMedian());
    }
}
