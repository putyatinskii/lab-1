package view_layer;

import classes.User;
import controller.Controller;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class Menu {

    SearchTasks searchTasks = new SearchTasks();
    Controller controller = new Controller();

    public void showMenu(int id) {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("Press 1 for edit task");
            System.out.println("Press 2 for edit list of tasks");
            System.out.println("Press 3 for search tasks");
            System.out.println("Press 4 for update user");
            System.out.println("Press 5 for remove user");
            System.out.println("Press 0 for exit");
            int res;
            do {
                res = sc.nextInt();
                switch (res) {
                    case 1:
                        break;
                    case 2:

                        break;
                    case 3:
                        searchTasks.searchTask();
                        break;
                    case 4:
                        updateThisUser(id);
                        break;
                    case 5:
                        res = removeThisUser(id);;
                        break;
                    case 0:
                        res = -1;
                        break;
                    default:
                        res = -10;
                        System.out.println("incorrect value. Try again");
                        break;
                }
            } while (res != -1);
        }
    }

    private void updateThisUser(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for change password");
        System.out.println("Press 2 for change firstname");
        System.out.println("Press 3 for change lastname");
        System.out.println("Press 4 for change phone");
        System.out.println("Press 0 for exit");
        User user = controller.getUserById(id);
        int res;
        do {
            res = sc.nextInt();
            sc.nextLine();
            switch (res) {
                case 1:
                    String password = sc.nextLine();
                    user.setPassword(password);
                    if (!user.getPassword().equals(DigestUtils.sha256Hex(password)))
                        System.out.println("Incorrect password");
                    break;
                case 2:
                    String firstname = sc.nextLine();
                    user.setFirstname(firstname);
                    if (!user.getFirstname().equals(firstname))
                        System.out.println("Incorrect firstname");
                    break;
                case 3:
                    String lastname = sc.nextLine();
                    user.setLastname(lastname);
                    if (!user.getLastname().equals(lastname))
                        System.out.println("Incorrect lastname");
                    break;
                case 4:
                    String phone = sc.nextLine();
                    user.setPhone(phone);
                    if (!user.getPhone().equals("+7" + phone))
                        System.out.println("Incorrect phone");
                    break;
                case 0:
                    res = -1;
                    break;
                default:
                    res = -10;
                    System.out.println("incorrect value. Try again");
                    break;
            }
        } while (res != -1);
        controller.tryUpdateThisUser(user);
        System.out.println("Update was successful");
    }

    private int removeThisUser(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (controller.tryRemoveUser(id, password)) {
            System.out.println("Remove user was successful");
            return -1;
        }
        else {
            System.out.println("Incorrect password");
            return 0;
        }
    }
}
