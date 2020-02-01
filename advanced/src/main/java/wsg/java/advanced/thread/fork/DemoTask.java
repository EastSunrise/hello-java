package wsg.java.advanced.thread.fork;

import java.util.concurrent.RecursiveTask;

/**
 * 10000个数求和
 *
 * @author Kingen
 */
public class DemoTask extends RecursiveTask<Integer> {

    private static final int MAX = 100;

    private Integer start;

    private Integer end;

    private int[] array;

    DemoTask(int[] array) {
        this.start = 0;
        this.end = array.length;
        this.array = array;
    }

    private DemoTask(int[] array, Integer start, Integer end) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            System.out.println("left: " + start + ", right: " + end + ", sum: " + sum);
        } else {
            int mid = (start + end) / 2;
            DemoTask leftTask = new DemoTask(array, start, mid);
            DemoTask rightTask = new DemoTask(array, mid, end);
            leftTask.fork();
            rightTask.fork();
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }
}
