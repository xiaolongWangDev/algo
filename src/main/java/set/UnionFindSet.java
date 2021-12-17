package set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFindSet<T> {
    public static class Element<T> {
        public T value;

        public Element(T value) {
            this.value = value;
        }
    }

    private Map<T, Element<T>> data = new HashMap<>();
    private Map<Element<T>, Element<T>> parent = new HashMap<>();
    private Map<Element<T>, Integer> size = new HashMap<>();

    public UnionFindSet(List<T> elements) {
        elements.forEach(e -> {
            Element<T> wrapper = new Element<>(e);
            data.put(e, wrapper);
            parent.put(wrapper, wrapper);
            size.put(wrapper, 1);
        });
    }

    public T findHead(T element) {
        return findHead(data.get(element)).value;
    }

    private Element<T> findHead(Element<T> element) {
        List<Element<T>> elementsAlongTheWay = new ArrayList<>();
        while (element != parent.get(element)) {
            elementsAlongTheWay.add(element);
            element = parent.get(element);
        }
        for (Element<T> o : elementsAlongTheWay) {
            parent.put(o, element);
        }
        return element;
    }

    public boolean isInSameSet(T a, T b) {
        if (data.containsKey(a) && data.containsKey(b)) {
            return findHead(data.get(a)) == findHead(data.get(b));
        }

        return false;
    }

    public void union(T a, T b) {
        if (data.containsKey(a) && data.containsKey(b)) {
            Element<T> aHead = findHead(data.get(a));
            Element<T> bHead = findHead(data.get(b));

            if (aHead != bHead) {
                Element<T> bigger = size.get(aHead) > size.get(bHead) ? aHead : bHead;
                Element<T> smaller = bigger == aHead ? bHead : aHead;
                parent.put(smaller, bigger);
                size.put(bigger, size.get(bigger) + size.get(smaller));
                size.remove(smaller);
            }
        }
    }
}
