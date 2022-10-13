package datastructure;

import java.util.*;

public class UnionFind<T> {
    private class SetElement {
        private final T data;

        public SetElement(T data) {
            this.data = data;
        }
    }

    private final Map<T, SetElement> dataToElement = new HashMap<>();
    private final Map<SetElement, SetElement> elementParent = new HashMap<>();
    private final Map<SetElement, Integer> setSize = new HashMap<>();

    public UnionFind(Collection<T> allData) {
        for (T data : allData) {
            SetElement setElement = new SetElement(data);
            dataToElement.put(data, setElement);
            elementParent.put(setElement, setElement);
            setSize.put(setElement, 1);
        }
    }

    public boolean inTheSameSet(T data1, T data2) {
        if (dataToElement.containsKey(data1) && dataToElement.containsKey(data2)) {
            return findHead(data1) == findHead(data2);
        }
        return false;
    }

    public void union(T data1, T data2) {
        if (dataToElement.containsKey(data1) && dataToElement.containsKey(data2)) {
            SetElement head1 = findHead(data1);
            SetElement head2 = findHead(data2);

            if (head1 != head2) {
                SetElement bigger = setSize.get(head1) > setSize.get(head2) ? head1 : head2;
                SetElement smaller = head1 == bigger ? head2 : head1;
                setSize.put(bigger, setSize.get(head1) + setSize.get(head2));
                setSize.remove(smaller);
                elementParent.put(smaller, bigger);
            }
        }
    }

    private SetElement findHead(T data) {
        SetElement cur = elementParent.get(dataToElement.get(data));
        List<SetElement> elementsToFlatten = new ArrayList<>();
        while (cur != elementParent.get(cur)) {
            elementsToFlatten.add(cur);
            cur = elementParent.get(cur);
        }
        for (var element : elementsToFlatten) {
            elementParent.put(element, cur);
        }
        return cur;
    }
}
