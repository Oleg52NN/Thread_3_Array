package com.company;


import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;


public class Main {
    final static int ARRAY_SIZE = 200_000_000;
    static int[] array = new int[ARRAY_SIZE];

    public static void main(String[] args) {
        array = arrayCreate();
        long timeMillis = System.currentTimeMillis();
        Instant t1 = Instant.now();
        System.out.println(oneThreadSum(array));
        System.out.println("Время выполнения по первому счётчику: " + (System.currentTimeMillis() - timeMillis));
        Instant t2 = Instant.now();
        long time = Duration.between(t1, t2).toMillis();
        System.out.println("Время выполнения по второму счётчику: " + time);
        ArraySum threadSum = new ArraySum(0, ARRAY_SIZE / 2);
        t1 = Instant.now();
        timeMillis = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(new ArraySum(0, ARRAY_SIZE)));
        t2 = Instant.now();
        System.out.println("Время выполнения по первому счётчику: " + (System.currentTimeMillis() - timeMillis));
        time = Duration.between(t1, t2).toMillis();
        System.out.println("Время выполнения по второму счётчику: " + time);
    }

    static int[] arrayCreate() {
        int[] arrayInt = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            arrayInt[i] = (int) (Math.random() * ARRAY_SIZE);
        }
        return arrayInt;
    }

    static long oneThreadSum(int[] arrayInt) {
        long sum = 0;
        for (long i : arrayInt
        ) {
            sum += i;
        }
        return sum;
    }

}
