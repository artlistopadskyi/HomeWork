package hw8;

import java.util.Arrays;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class Auth {
    private static final int MIN_LANG_LOGIN = 5;
    private static final int MAX_LANG_LOGIN = 20;

    private static final int MIN_LANG_PASSWORD = 5;
    private static final int MAX_LANG_PASSWORD = 128;

    private String login;
    private String password;
    private String confirmPassword;

    public void setLogin(String login) throws WrongLoginException {
        if (MIN_LANG_LOGIN <= login.length() && login.length() <= MAX_LANG_LOGIN) {
            if (isCorrectCharacters(login)) {
                this.login = login;
                return;
            }
        }

        throw new WrongLoginException("Вы ввели недопустимый логин");
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String password) throws WrongPasswordException {
        if (MIN_LANG_PASSWORD <= password.length() && password.length() <= MAX_LANG_PASSWORD) {
            if (isCorrectCharacters(password)) {
                this.password = password;
                return;
            }
        }

        throw new WrongPasswordException();
    }

    public String getPassword() {
        return this.password;
    }

    public void setConfirmPassword(String confirmPassword) throws WrongPasswordException {
        if (MIN_LANG_PASSWORD <= confirmPassword.length() && confirmPassword.length() <= MAX_LANG_PASSWORD) {
            if (isCorrectCharacters(confirmPassword)) {
                this.confirmPassword = confirmPassword;
                return;
            }
        }

        throw new WrongPasswordException();
    }


    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public boolean signUp(String login, String password, String confirmPassword) {
        try {
            if (isStringMatch(password, confirmPassword)) {
                this.setLogin(login);
                this.setPassword(password);
                this.setConfirmPassword(confirmPassword);

                return true;
            } else {
                System.out.println("Введённые пароли не совпадают");
            }
        } catch (WrongLoginException | WrongPasswordException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public boolean signIn(String login, String password) {
        return isStringMatch(login, this.getLogin()) && isStringMatch(password, this.getPassword());

    }

    private boolean isCorrectCharacters(String login) {
        return Pattern.compile("^[a-zA-Z0-9]+$").matcher(login).find();
    }

    private boolean isStringMatch(String string, String confirmString) {
        char[] lineString = string.toCharArray();
        char[] lineConfirmString = confirmString.toCharArray();

        return Arrays.equals(lineString, lineConfirmString);
    }
}

