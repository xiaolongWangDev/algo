package code.array;

import code.array.sorting.SortingUtils;

import java.util.Arrays;

import static code.util.Utils.preOrderPrintTreeNodeAndChildren;

public class MaxHeap {
    private int[] buffer = new int[8];
    private int heapSize;

    public MaxHeap() {
    }

    public MaxHeap(int[] buffer) {
        this.buffer = buffer;
        this.heapSize = buffer.length;

        if (heapSize > 1) {
            int previousLevel = (int) Math.floor(Math.log(buffer.length) / Math.log(2));
            for (int i = (int) Math.pow(2, previousLevel) - 1; i >= 0; i--) {
                heapify(i);
            }
        }
    }

    public void insert(int newValue) {
        if (heapSize >= buffer.length) {
            expand();
        }
        int pointer = heapSize;
        buffer[heapSize++] = newValue;
        int parentPoint = (pointer - 1) / 2;
        while (buffer[pointer] > buffer[parentPoint]) {
            SortingUtils.swap(pointer, parentPoint, buffer);
            pointer = parentPoint;
            parentPoint = (pointer - 1) / 2;
        }
    }

    public void heapify(int index) {
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize) {
            int rightChild = leftChild + 1;
            int indexOfLargest = rightChild < heapSize && buffer[rightChild] > buffer[leftChild] ? rightChild : leftChild;
            indexOfLargest = buffer[indexOfLargest] > buffer[index] ? indexOfLargest : index;
            if (index == indexOfLargest) {
                break;
            }
            SortingUtils.swap(index, indexOfLargest, buffer);
            index = indexOfLargest;
            leftChild = index * 2 + 1;
        }
    }

    public void sort() {
        int savedHeapSize = heapSize;
        while (heapSize > 0) {
            SortingUtils.swap(0, heapSize - 1, buffer);
            heapSize--;
            heapify(0);
        }
        heapSize = savedHeapSize;
    }

    private void expand() {
//        System.out.println("bump buffer size to " + buffer.length * 2);
        int[] temp = new int[buffer.length * 2];
        //noinspection ManualArrayCopy
        for (int i = 0; i < buffer.length; i++) {
            temp[i] = buffer[i];
        }
        buffer = temp;
    }

    private int[] getHeapArray() {
        int[] result = new int[heapSize];
        System.arraycopy(buffer, 0, result, 0, heapSize);
        return result;
    }


    public static void main(String[] args) {
//        checkBasicOperations(maxHeap);

        int[] testData = SortingUtils.generateTestData(1000000, 0, 100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        MaxHeap maxHeap = new MaxHeap(testData);

        // check sort
        maxHeap.sort();
        Arrays.sort(copiedArray);
        SortingUtils.compareArray(copiedArray, maxHeap.getHeapArray());
    }

    private static MaxHeap checkBasicOperations(MaxHeap maxHeap) {
        int[] testData = SortingUtils.generateTestData(15, 0, 100);
        SortingUtils.print(testData);

        // check insert
        for (int testDatum : testData) {
            maxHeap.insert(testDatum);
        }
        preOrderPrintTreeNodeAndChildren(0, maxHeap.getHeapArray());

        // check heapify
        maxHeap.buffer[0] = -1;
        maxHeap.heapify(0);

        preOrderPrintTreeNodeAndChildren(0, maxHeap.getHeapArray());
        return maxHeap;
    }
}
