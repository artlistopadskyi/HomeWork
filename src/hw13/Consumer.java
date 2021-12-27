package hw13;

import java.util.List;

public class Consumer implements Runnable {
    final List<Integer> numbers;

    public Consumer(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void consume() {
        synchronized (numbers) {
            while (numbers.isEmpty()) {
                try {
                    System.out.println("Список чисел пуст!");
                    numbers.wait();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            numbers.remove(0);
            System.out.println("- Потребление из списка " + numbers);
            numbers.notifyAll();
        }
    }

    @Override
    public void run() {
        System.out.println("Потребитель чисел начал работу");
        while (true) {
            consume();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
