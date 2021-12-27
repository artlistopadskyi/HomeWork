package hw13;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {
    private static final int SIZE_LIST_NUMBERS = 5;
    private static final int RANDOM_NUMBER_MIN = 0;
    private static final int RANDOM_NUMBER_MAX = 100;
    Random rand = new Random();
    List<Integer> numbers;

    public Producer(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void produce() {
        synchronized (numbers) {
            while (numbers.size() >= SIZE_LIST_NUMBERS) {
                System.out.println("Список чисел полон!");
                try {
                    numbers.wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            numbers.add(getRandNumber(RANDOM_NUMBER_MIN, RANDOM_NUMBER_MAX));
            System.out.println("+ Пополнение списка " + numbers);

            numbers.notifyAll();
        }
    }

    @Override
    public void run() {
        System.out.println("Производитель чисел начал работу");
        while (true) {
            produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public int getRandNumber(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}