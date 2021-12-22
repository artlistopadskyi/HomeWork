package hw12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static final String PATH_TO_FILE = "src/com/pb/hw12/phoneBook.json";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        boolean endProgram = false;
        while (true) {
            System.out.println("Доступные команды");
            System.out.println("\"add\" - добавить контакт");
            System.out.println("\"del\" - удалить контакт");
            System.out.println("\"search\" - поиск контакта");
            System.out.println("\"print\" - вывод всех записей");
            System.out.println("\"edit\" - редактировать контакт");
            System.out.println("\"write\" - запись в файл всех данных");
            System.out.println("\"read\" - загрузка из файла всех данных");
            System.out.println("\"end\" - завершить работу приложения");
            System.out.println("Введите команду: ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    addPerson(phoneBook);
                    break;
                case "del":
                    delPerson(phoneBook);
                    break;
                case "search":
                    searchPerson(phoneBook);
                    break;
                case "print":
                    printPeople(phoneBook);
                    break;
                case "edit":
                    editPerson(phoneBook);
                    break;
                case "write":
                    writeFile(phoneBook);
                    break;
                case "read":
                    readFile(phoneBook);
                    break;
                case "end":
                    endProgram = true;
                    break;
                default:
                    System.out.println("Введенная команда не найдена! Повторите попытку");
            }
            if (endProgram) {
                System.out.println("Завершение работы.");
                break;
            }
        }
    }

    private static void addPerson(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        int birthdayYear;
        int birthdayMonth;
        int birthdayDay;

        Person person = new Person();

        addFio(person);
        if (person.getFio() == null) {
            return;
        }

        System.out.println("Введите адрес: ");
        person.setAddress(scanner.nextLine());

        boolean endAddedPhone = false;
        while (true) {
            System.out.println("Если Вы хотите добавить пользователю номер телефона введите \"y\" если хотите продолжить введите \"n\"");
            System.out.println("Введите команду: ");
            String command = scanner.nextLine();
            switch (command) {
                case "y":
                    addPhone(person);
                    break;
                case "n":
                    endAddedPhone = true;
                    break;
                default:
                    System.out.println("Вы ввели неизвесную команду!");
            }
            if (endAddedPhone) {
                break;
            }
        }

        try {
            System.out.println("Введите дату рождения");
            System.out.println("Год: ");
            birthdayYear = scanner.nextInt();
            System.out.println("Месяц: ");
            birthdayMonth = scanner.nextInt();
            System.out.println("День: ");
            birthdayDay = scanner.nextInt();

            person.setBirthday(LocalDate.of(birthdayYear, birthdayMonth, birthdayDay));
        } catch (Exception e) {
            System.out.println("Неверно введенная дата рождения!");
            return;
        }

        person.setUpdatedAt(LocalDateTime.now());
        phoneBook.addPerson(person);

        System.out.println("Новый пользователь добавлен!");
    }

    private static void delPerson(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО пользователя: ");
        int index = phoneBook.searchPerson(scanner.nextLine());
        if (indexFind(index)) {
            try {
                phoneBook.removePerson(index);
                System.out.println("Пользователь удален!");
            } catch (Exception e) {
                System.out.println("Во время удаления произошла ошибка, повторите пожалуйста позже.");
            }
        } else {
            System.out.println("Пользователь c таким именем не найден!");
        }
    }

    private static void searchPerson(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО пользователя: ");
        int index = phoneBook.searchPerson(scanner.nextLine());
        if (indexFind(index)) {
            try {
                System.out.println("Пользователь найден: ");
                System.out.println(phoneBook.printPerson(index));
            } catch (Exception e) {
                System.out.println("Во время поиска произошла ошибка, повторите пожалуйста позже.");
            }
        } else {
            System.out.println("Пользователь c таким именем не найден!");
        }
    }

    private static void printPeople(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для вывода доступны сортировки ");
        System.out.println("\"fio\" - соритровка по ФИО");
        System.out.println("\"address\" - соритровка по адресу");
        System.out.println("\"birthday\" - соритровка по дню рождения");
        System.out.println("Введите команду: ");
        try {
            switch (scanner.nextLine()) {
                case "fio":
                    phoneBook.sortedByFio();
                    break;
                case "address":
                    phoneBook.sortedByAddress();
                    break;
                case "birthday":
                    phoneBook.sortedByBirthday();
                    break;
                default:
                    System.out.println("Вы ввели неизвесную команду! Вывод данных по умолчанию!");
            }
            System.out.println(phoneBook.printPeople());
        } catch (Exception e) {
            System.out.println("Во время вывода записей произошла ошибка, повторите пожалуйста позже.");
        }
    }

    private static void editPerson(PhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО пользователя: ");
        int index = phoneBook.searchPerson(scanner.nextLine());
        if (!indexFind(index)) {
            System.out.println("Пользователь c таким именем не найден!");
            return;
        }

        Person person = phoneBook.getPeople().get(index);
        System.out.println("Для редактирования доступны поля ");
        System.out.println("\"fio\" - ФИО");
        System.out.println("\"address\" - адрес");
        System.out.println("\"birthday\" - днь рождения");
        System.out.println("\"phones\" - номера телефонов");
        System.out.println("Введите поле: ");
        String field = scanner.nextLine();
        switch (field) {
            case "fio":
                updateFio(person);
                break;
            case "address":
                updateAddress(person);
                break;
            case "birthday":
                updateBirthday(person);
                break;
            case "phones":
                updatePhones(person);
                break;
            default:
                System.out.println("Вы ввели неизвесную команду!");
        }

        person.setUpdatedAt(LocalDateTime.now());

        try {
            phoneBook.updatePerson(index, person);
            System.out.println("Редактирование завершено!");
        } catch (Exception e) {
            System.out.println("Во время редактирования произошла ошибка, повторите пожалуйста позже.");
        }
    }

    private static void writeFile(PhoneBook phoneBook) {
        try {
            phoneBook.writeFile(PATH_TO_FILE);
            System.out.println("Запись в файл всех данных завершена!");
        } catch (Exception e) {
            System.out.println("Во время записи произошла ошибка, повторите пожалуйста позже.");
        }
    }

    private static void readFile(PhoneBook phoneBook) {
        try {
            phoneBook.setPeople(phoneBook.readFile(PATH_TO_FILE));
            System.out.println("Загрузка из файла всех данных завершена!");
        } catch (Exception e) {
            System.out.println("Во время загрузки произошла ошибка, повторите пожалуйста позже.");
        }
    }

    private static void addFio(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО: ");
        String fio = scanner.nextLine();
        if (fio.isEmpty()) {
            System.out.println("Поле ФИО обязательно для заполнения!");
        } else {
            person.setFio(fio);
        }
    }

    private static void updateFio(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО: ");
        String fio = scanner.nextLine();
        if (fio.isEmpty()) {
            System.out.println("Поле ФИО обязательно для заполнения!");
        } else {
            person.setFio(fio);
            System.out.println("Поле ФИО отредактировано!");
        }
    }

    private static void updateAddress(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес для пользователя: ");
        person.setAddress(scanner.nextLine());
        System.out.println("Поле адрес отредактировано!");
    }

    private static void updateBirthday(Person person) {
        Scanner scanner = new Scanner(System.in);
        int birthdayYear;
        int birthdayMonth;
        int birthdayDay;
        try {
            System.out.println("Введите дату рождения");
            System.out.println("Год: ");
            birthdayYear = scanner.nextInt();
            System.out.println("Месяц: ");
            birthdayMonth = scanner.nextInt();
            System.out.println("День: ");
            birthdayDay = scanner.nextInt();
            person.setBirthday(LocalDate.of(birthdayYear, birthdayMonth, birthdayDay));
            System.out.println("Поле дата рождения отредактировано!");
        } catch (Exception e) {
            System.out.println("Неверно введенная дата рождения!");
        }
    }

    private static void addPhone(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер телефона: ");
        if (!scanner.hasNext()) {
            System.out.println("Телефон не добавлен, вы не ввкли номер телефона!");
        } else {
            person.addPhone(scanner.nextLine());
            System.out.println("Номер добавлен!");
        }
    }

    private static void updatePhones(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для управления полем доступны команды ");
        System.out.println("\"add\" - добавить номер телефона");
        System.out.println("\"edit\" - отредактировать существующий");
        System.out.println("\"del\" - удалить номер телефона");
        System.out.println("Введите команду: ");
        String command = scanner.nextLine();
        switch (command) {
            case "add":
                addPhone(person);
                break;
            case "edit":
                System.out.println("Введите номер телефона: ");
                int edit = person.searchPhone(scanner.nextLine());
                if (indexFind(edit)) {
                    try {
                        System.out.println("Введите новый номер телефона: ");
                        person.updatePhone(edit, scanner.nextLine());
                        System.out.println("Номер телефона отредактирован!");
                    } catch (Exception e) {
                        System.out.println("Во время редактирования произошла ошибка, повторите пожалуйста позже.");
                    }
                } else {
                    System.out.println("Номер телефона не найден!");
                }
                break;
            case "del":
                System.out.println("Введите номер телефона: ");
                int remove = person.searchPhone(scanner.nextLine());
                if (indexFind(remove)) {
                    try {
                        person.removePhone(remove);
                        System.out.println("Номер телефона удален!");
                    } catch (Exception e) {
                        System.out.println("Во время удаления произошла ошибка, повторите пожалуйста позже.");
                    }
                } else {
                    System.out.println("Номер телефона не найден!");
                }
                break;
            default:
                System.out.println("Вы ввели неизвесную команду!");
        }
    }

    public static Boolean indexFind(int index) {
        Predicate<Integer> indexFind = i -> i >= 0;
        return indexFind.test(index);
    }
}
