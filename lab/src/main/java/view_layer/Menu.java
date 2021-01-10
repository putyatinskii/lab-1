package view_layer;

import classes.Task;
import classes.User;
import controller.ControllerUser;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    private ControllerUser controllerUser = new ControllerUser();
    private SearchTasks searchTasks = new SearchTasks();
    private EditorLists editorLists = new EditorLists();
    private EditorTasks editorTasks = new EditorTasks();
    private ArrayList<Task> ObservableTasks = new ArrayList<>();
    private ArrayList<Task> MyTasks = new ArrayList<>();
    int id;
    public void showMenu(int id) {
        this.id = id;
        try(Scanner sc = new Scanner(System.in)) {
            int res;
            searchTasks.getOverdueObservableTasks(id);
            editorTasks.getOverdueMyTasks(id);
            do {
                System.out.println("Press 1 for edit task");
                System.out.println("Press 2 for edit list of tasks");
                System.out.println("Press 3 for search tasks");
                System.out.println("Press 4 for update user");
                System.out.println("Press 5 for remove user");
                System.out.println("Press 0 for exit");
                res = sc.nextInt();
                switch (res) {
                    case 1:
                        editorTasks.editTasksMenu(id);
                        break;
                    case 2:
                        editorLists.ListsOfTasksMenu(id);
                        break;
                    case 3:
                        searchTasks.searchTask(id);
                        break;
                    case 4:
                        updateThisUser();
                        break;
                    case 5:
                        res = removeThisUser();;
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

    private void updateThisUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for change password");
        System.out.println("Press 2 for change firstname");
        System.out.println("Press 3 for change lastname");
        System.out.println("Press 4 for change phone");
        System.out.println("Press 0 for exit");
        User user = controllerUser.getUserById(id);
        int res;
        do {
            res = sc.nextInt();
            sc.nextLine();
            switch (res) {
                case 1:
                    System.out.print("Enter new password: ");
                    String password = sc.nextLine();
                    user.setPassword(password);
                    if (!user.getPassword().equals(DigestUtils.sha256Hex(password)))
                        System.out.println("Incorrect password");
                    break;
                case 2:
                    System.out.print("Enter new firstname: ");
                    String firstname = sc.nextLine();
                    user.setFirstname(firstname);
                    if (!user.getFirstname().equals(firstname))
                        System.out.println("Incorrect firstname: ");
                    break;
                case 3:
                    System.out.print("Enter new lastname: ");
                    String lastname = sc.nextLine();
                    user.setLastname(lastname);
                    if (!user.getLastname().equals(lastname))
                        System.out.println("Incorrect lastname: ");
                    break;
                case 4:
                    System.out.print("Enter new phone: ");
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
        controllerUser.tryUpdateThisUser(user);
        System.out.println("Update was successful");
        System.out.println("Return in MainMenu");
    }

    private int removeThisUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (controllerUser.tryRemoveUser(id, password)) {
            System.out.println("Remove user was successful");
            System.out.println("Return in MainMenu");
            return -1;
        }
        else {
            System.out.println("Incorrect password");
            return 0;
        }
    }
}
