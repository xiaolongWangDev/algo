package heap;

import java.util.*;
import java.util.function.Function;

public class MutableHeap<T, U> {
    private final ArrayList<T> buffer = new ArrayList<>(8);
    private final Comparator<T> comparator;
    private final Map<U, Integer> indexMap = new HashMap<>();
    private final Function<T, U> indexKeyMapper;

    public MutableHeap(Function<T, U> indexKeyMapper) {
        this(null, indexKeyMapper);
    }

    public MutableHeap(Comparator<T> comparator, Function<T, U> indexKeyMapper) {
        this.comparator = comparator;
        this.indexKeyMapper = indexKeyMapper;
    }

    public void setAll(Collection<T> elements) {
        buffer.clear();
        indexMap.clear();
        buffer.addAll(elements);
        for (int i = 0; i < buffer.size(); i++) {
            indexMap.put(indexKeyMapper.apply(buffer.get(i)), i);
        }

        if (buffer.size() > 1) {
            int secondLastLevel = (int) Math.floor(Math.log(buffer.size()) / Math.log(2));
            for (int i = (int) Math.pow(2, secondLastLevel) - 1; i >= 0; i--) {
                heapify(i);
            }
        }
    }

    private void setValue(int index, T newValue) {
        indexMap.remove(indexKeyMapper.apply(buffer.get(index)));
        indexMap.put(indexKeyMapper.apply(newValue), index);
        buffer.set(index, newValue);

        bubbleUp(index);
        heapify(index);
    }

    public void setValue(U key, T newValue) {
        setValue(indexMap.get(key), newValue);
    }

    public T getValue(U key) {
        Integer index = indexMap.get(key);
        return index == null ? null : buffer.get(index);
    }

    public List<T> getAll() {
        return List.copyOf(buffer);
    }

    public T peek() {
        return buffer.get(0);
    }

    public boolean contains(T value) {
        return indexMap.containsKey(indexKeyMapper.apply(value));
    }

    public int size() {
        return indexMap.size();
    }

    public T poll() {
        if (buffer.size() > 0) {
            swap(0, buffer.size() - 1);
            T elementToReturn = buffer.get(buffer.size() - 1);
            buffer.remove(buffer.size() - 1);
            indexMap.remove(indexKeyMapper.apply(elementToReturn));
            heapify(0);
            return elementToReturn;
        }
        throw new IndexOutOfBoundsException("Heap is empty");
    }

    public void add(T element) {
        buffer.add(element);
        indexMap.put(indexKeyMapper.apply(element), buffer.size() - 1);
        bubbleUp(buffer.size() - 1);
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    // make the sub-tree using index at the root a heap
    // this involves recursively "sinking" the "bad" value down the tree
    private void heapify(int index) {
        int leftChildIndex = index * 2 + 1;
        while (leftChildIndex < buffer.size()) {
            int rightChildIndex = leftChildIndex + 1;
            int indexOfLargest = rightChildIndex < buffer.size() && greaterThan(rightChildIndex, leftChildIndex)
                    ? rightChildIndex : leftChildIndex;
            indexOfLargest = greaterThan(indexOfLargest, index) ? indexOfLargest : index;
            if (index == indexOfLargest) {
                break;
            }
            swap(index, indexOfLargest);
            index = indexOfLargest;
            leftChildIndex = index * 2 + 1;
        }
    }

    private void bubbleUp(int index) {
        int cur = index;
        int parent = (cur - 1) / 2;
        while (greaterThan(cur, parent)) {
            swap(cur, parent);
            cur = parent;
            parent = (cur - 1) / 2;
        }
    }

    // compare values at index A and index B using the comparator
    // if comparator is not set, assume the 2 objects are comparable
    private boolean greaterThan(int indexA, int indexB) {
        if (comparator != null) {
            return comparator.compare(buffer.get(indexA), buffer.get(indexB)) > 0;
        } else {
            //noinspection unchecked
            return ((Comparable<T>) buffer.get(indexA)).compareTo(buffer.get(indexB)) > 0;
        }
    }

    private void swap(int indexA, int indexB) {
        T temp = buffer.get(indexA);
        buffer.set(indexA, buffer.get(indexB));
        buffer.set(indexB, temp);
        indexMap.put(indexKeyMapper.apply(buffer.get(indexA)), indexA);
        indexMap.put(indexKeyMapper.apply(buffer.get(indexB)), indexB);
    }

}
