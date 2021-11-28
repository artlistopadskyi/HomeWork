package hw10;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test NumBox integer: ");
        NumBox<Integer> intArr = new NumBox<>(3);
        intArr.add(20);
        intArr.add(36);
        intArr.add(64);
        intArr.add(90);
        System.out.println("длина результата: " + intArr.length());
        System.out.println("итоговая сумма: " + intArr.sum());
        System.out.println("средний результат: " + intArr.average());
        System.out.println("максимальное значение: " + intArr.max());
        System.out.println("/*----------------------------------*/");
        System.out.println("Test NumBox float: ");
        NumBox<Float> floats = new NumBox<>(4);
        floats.add(2.27f);
        floats.add(1.5f);
        floats.add(3.5f);
        floats.add(0.73f);
        System.out.println("длина результата: " + floats.length());
        System.out.println("итоговая сумма: " + floats.sum());
        System.out.println("средний результат: " + floats.average());
        System.out.println("максимальное значение: " + floats.max());
    }
}
