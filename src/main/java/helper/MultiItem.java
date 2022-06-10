package helper;

public class MultiItem extends Item {
    public int count;

    public MultiItem(int count, int value, int weight) {
        super(value, weight);
        this.count = count;
    }
}
