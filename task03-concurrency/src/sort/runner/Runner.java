package sort.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by User-PC on 26.10.2014.
 */
public class Runner {

    private static final int SIZE = 10_000_000;
    private static final int PARTS = 4;
    private static final int PAUSE = 100;

    private static int sleeps = 0;

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int[] array = getArray();
        List<int[]> snippets = divide(array);
        int[] sortedResult = sort(snippets);
        long end = System.currentTimeMillis() - sleeps * PAUSE;
        System.out.println("\ncustom sort: " + sortedResult.length + " in " + (end - begin) + "ms");

        begin = System.currentTimeMillis();
        Arrays.sort(array);
        end = System.currentTimeMillis();
        System.out.println("java sort: " + array.length + " in " + (end - begin) + "ms");
    }

    private static int[] sort(List<int[]> snippets) throws InterruptedException {
        System.out.println("sorting");
        final ConcurrentLinkedQueue<int[]> preResults = prepare(snippets);
        ForkJoinPool executor = new ForkJoinPool();
        while (preResults.size() != 1 || preResults.peek().length < SIZE) {
            System.out.print(preResults.size() + " ");
            while (preResults.size() > 1) {
                runNewTask(preResults, executor);
            }
            Thread.sleep(PAUSE);
            sleeps++;
        }
        return preResults.poll();
    }

    private static void runNewTask(final ConcurrentLinkedQueue<int[]> preResults, ForkJoinPool executor) {
        final int[] a = preResults.poll();
        final int[] b = preResults.poll();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                preResults.add(unite(a, b));
            }
        });
    }

    private static ConcurrentLinkedQueue<int[]> prepare(List<int[]> snippets) {
        final ConcurrentLinkedQueue<int[]> preresults = new ConcurrentLinkedQueue<>();
        for (final int[] snippet : snippets) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Arrays.sort(snippet);
                    preresults.add(snippet);
                }
            }).start();
        }
        return preresults;
    }

    private static int[] unite(int[] a, int[] b) {
        System.out.println(Thread.currentThread() + " untie " + a.length + " and " + b.length);
        int sumSize = a.length + b.length;
        int[] result = new int[sumSize];
        int i = 0, j = 0, k = 0;
        while (k < sumSize) {
            result[k++] = i == a.length ? b[j++] : j == a.length ? a[i++] :
                    a[i] < b[j] ? a[i++] : b[j++];
        }
        return result;
    }

    private static List<int[]> divide(int[] array) {
        List<int[]> snippets = new ArrayList<>(PARTS);
        int partSize = SIZE / PARTS;
        for (int i = 0; i < PARTS - 1; i++) {
            snippets.add(Arrays.copyOfRange(array, i * partSize, (i + 1) * partSize));
        }
        snippets.add(Arrays.copyOfRange(array, (PARTS - 1) * partSize, SIZE));
        System.out.println("data divided on snippets");
        return snippets;
    }

    private static int[] getArray() {
        int[] array = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt();
        }
        System.out.println("data prepared");
        return array;
    }

}
