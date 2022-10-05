package com.company;

import java.util.concurrent.RecursiveTask;
import static com.company.Main.array;


public class ArraySum extends RecursiveTask<Long> {

    private int start;
    private int end;

    public ArraySum(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        final int diff = end - start;
        switch (diff) {
            case 0: {
                return 0L;
            }
            case 1:
                return (long) array[start];
            case 2:
                return (long) array[start] + array[start + 1];
            default:
                return forkTasksAndGetResult();
        }
    }

    private long forkTasksAndGetResult() {

        final int middle = (end - start) / 2 + start;

        ArraySum task1 = new ArraySum(start, middle);
        ArraySum task2 = new ArraySum(middle, end);

        task1.fork();
        task2.fork();

        return task1.join() + task2.join();
    }

}


