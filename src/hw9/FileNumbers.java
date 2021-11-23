package hw9;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

public class FileNumbers {
    private static final int RANDOM_NUMBER_MIN = 1;
    private static final int RANDOM_NUMBER_MAX = 99;

    private static final int COUNT_NUMBERS_IN_STRING = 10;
    private static final int COUNT_STRINGS = 10;

    public static void main(String[] args) {
        createNumbersFile();
        createOddNumbersFile();
    }

    public static void createNumbersFile() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < COUNT_STRINGS; i++) {
            for (int j = 0; j < COUNT_NUMBERS_IN_STRING; j++) {
                result.append(getRandomNumber());
                if (j < (COUNT_NUMBERS_IN_STRING - 1)) {
                    result.append(" ");
                }
            }
            if (i < (COUNT_STRINGS - 1)) {
                result.append("\n");
            }
        }

        writeInFile(result.toString(), "src/hw9/numbers.txt");

        System.out.println("Create numbers: ");
        System.out.println(result);
    }

    public static void createOddNumbersFile() {
        StringBuilder result = new StringBuilder();

        try (Reader reader = new FileReader("src/hw9/numbers.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                result.append(resetEventNumbersInString(scanner.nextLine()));
                if (scanner.hasNext()) {
                    result.append("\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        writeInFile(result.toString(), "src/hw9/odd-numbers.txt");

        System.out.println("Create odd numbers: ");
        System.out.println(result);
    }

    private static int getRandomNumber() {
        Random random = new Random();

        return random.nextInt(RANDOM_NUMBER_MAX - RANDOM_NUMBER_MIN) + RANDOM_NUMBER_MIN;
    }

    private static String resetEventNumbersInString(String lineWithNumbers) {
        StringBuilder result = new StringBuilder();
        String[] lineNumbers = lineWithNumbers.split(" ");
        int i = lineNumbers.length;
        for (String lineNumber: lineNumbers) {
            i--;
            result.append(resetEventNumberInString(lineNumber));
            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    private static int resetEventNumberInString(String number) {
        try {
            if (Integer.parseInt(number) % 2 != 0) {
                return Integer.parseInt(number);
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private static void writeInFile(String result, String path) {
        try (Writer writer = new FileWriter(path)) {
            writer.write(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
