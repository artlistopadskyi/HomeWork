package hw13;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        Thread producer = new Thread(new Producer(stack));
        Thread consumer = new Thread(new Consumer(stack));

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}