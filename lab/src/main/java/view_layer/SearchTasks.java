package view_layer;

import classes.Task;
import controller.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchTasks {

    Controller controller = new Controller();

    public void searchTask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for search task by name");
        System.out.println("Press 2 for search task by description");
        System.out.println("Press 0 for exit");
        int res = 0;
        do {
            if (sc.hasNextInt()) {
                res = sc.nextInt();
            }
            switch (res) {
                case 1:
                    searchByName();
                    res = -1;
                    break;
                case 2:
                    searchByDescription();
                    res = -1;
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

    private void searchByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name of task: ");
        String name = sc.nextLine();
        ArrayList<Task> listOfTasks = new ArrayList<>(controller.searchTaskByName(name));
        for (Task listOfTask : listOfTasks) {
            System.out.println(listOfTask);
        }

    }

    private void searchByDescription() {
        Scanner sc = new Scanner(System.in);
    }
}
