package View;

import BLL.UserLogic;

import java.util.Scanner;

public class LoginWindow {

    UserLogic userLogic = new UserLogic();
    int id = -1;

    public void SignInOrSignUp() {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Press 1 for sign up");
            System.out.println("Press 2 for sign in");
            int res;
            do {
                res = scanner.nextInt();
                switch (res) {
                    case 1:
                        signUp();
                        res = -1;
                        break;
                    case 2:
                        signIn();
                        res = -1;
                        break;
                    default:
                        res = 0;
                        System.out.println("incorrect value. Try again");
                        break;
                }
            } while (res != -1);
        }
    }

    private void signIn() {
        String login = "";
        String password = "";
        try(Scanner sc = new Scanner(System.in)) {
            do {
                System.out.print("Enter login: ");
                login = sc.nextLine();
                System.out.print("Enter password: ");
                password = sc.nextLine();
                id = userLogic.trySignIn(login, password);
                if (id == -1)
                    System.out.println("incorrect login or password. Try again");
            } while (id == -1);
        }
        Menu menu = new Menu();
        menu.ShowMenu(id);
    }

    private void signUp() {
        String login = "";
        String password = "";
        String firstname = "";
        String lastname = "";
        String phone = "";
        int id = -1;
        UserLogic userLogic = new UserLogic();
        try(Scanner sc = new Scanner(System.in)) {
            do {
                if (login == "") {
                    System.out.print("Enter login (no more than 20 characters): ");
                    login = sc.nextLine();
                }
                if (password == "") {
                    System.out.print("Enter password: ");
                    password = sc.nextLine();
                }
                if (firstname == "") {
                    System.out.print("Enter firstname (no more than 20 characters): ");
                    firstname = sc.nextLine();
                }
                if (lastname == "") {
                    System.out.print("Enter lastname (no more than 20 characters): ");
                    lastname = sc.nextLine();
                }
                if (phone == "") {
                    System.out.print("Enter phone (12 characters): ");
                    phone = sc.nextLine();
                }
                id = userLogic.trySignUp(login, password, firstname, lastname, phone);
                if (id == -1)
                    System.out.println("invalid data format");
            } while (id == -1);
        }
        Menu menu = new Menu();
        menu.ShowMenu(id);
    }
}
