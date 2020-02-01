package wsg.java.advanced.thread.fork;

import java.util.concurrent.ForkJoinPool;

/**
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;

            ForkJoinPool forkJoinPool = new ForkJoinPool();
            int sum = forkJoinPool.invoke(new DemoTask(arr));
            System.out.println(sum);
        }
    }
}
