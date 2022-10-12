package practice.leetcode;

import java.util.concurrent.CountDownLatch;

public class L1114PrintInOrder {

    public L1114PrintInOrder() {
    }

    private CountDownLatch firstLatch = new CountDownLatch(1);
    private CountDownLatch secondLatch = new CountDownLatch(1);



    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstLatch.wait();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondLatch.wait();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
