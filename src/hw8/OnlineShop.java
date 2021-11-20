package hw8;

import java.util.Scanner;

public class OnlineShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Auth authUser = new Auth();

        String login;
        String password;
        String confirmPassword;

        System.out.println("Для входа Вам необходимо зарегистрироваться на сайте");
        while (true) {
            System.out.println("Введите логин: ");
            login = scanner.nextLine();
            System.out.println("Введите пароль: ");
            password = scanner.nextLine();
            System.out.println("Подтвердите пароль: ");
            confirmPassword = scanner.nextLine();

            if (authUser.signUp(login, password, confirmPassword)) {
                break;
            }
            System.out.println("Вы не смогли зарегистрироваться на сайте, повторите попытку");
        }

        System.out.println("Поздравляем, Вы успешно зарегистрировались на сайте, теперь Вам необходимо пройти авторизациию");
        while (true) {
            System.out.println("Введите логин: ");
            login = scanner.nextLine();
            System.out.println("Введите пароль: ");
            password = scanner.nextLine();

            if (authUser.signIn(login, password)) {
                break;
            }
            System.out.println("Вы не смогли войти на сайт, повторите вашу попытку");
        }

        System.out.println("Поздравляем, Вы авторизированы на сайте");
    }
}
