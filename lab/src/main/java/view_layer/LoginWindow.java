package view_layer;

import business_logic_layer.UserLogic;
import classes.User;
import controller.Controller;

import java.util.Scanner;

public class LoginWindow {

    Controller controller = new Controller();
    User user = new User();
    Menu menu = new Menu();
    int id = -1;

    public void signInOrSignUp() {
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
                id = controller.trySignIn(login, password);
                if (id == -1)
                    System.out.println("incorrect login or password. Try again");
            } while (id == -1);
            menu.showMenu(id);
        }
    }

    private void signUp() {
        try(Scanner sc = new Scanner(System.in)) {
            do {
                if (user.getUsername() == "") {
                    System.out.print("Enter login (no more than 20 characters): ");
                    user.setUsername(sc.nextLine());
                }
                if (user.getPassword() == "") {
                    System.out.print("Enter password: ");
                    user.setPassword(sc.nextLine());
                }
                if (user.getFirstname() == "") {
                    System.out.print("Enter firstname (no more than 20 characters): ");
                    user.setFirstname(sc.nextLine());
                }
                if (user.getLastname() == "") {
                    System.out.print("Enter lastname (no more than 20 characters): ");
                    user.setLastname(sc.nextLine());
                }
                if (user.getPhone() == "") {
                    System.out.print("Enter phone (10 characters without +7): ");
                    user.setPhone(sc.nextLine());
                }
                id = controller.trySignUp(user);
                if (id == -1)
                    System.out.println("invalid data format");
            } while (id == -1);
            menu.showMenu(id);
        }
    }
}