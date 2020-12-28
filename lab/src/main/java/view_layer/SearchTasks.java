package view_layer;

import classes.Task;
import controller.ControllerTask;
import controller.ControllerUser;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchTasks {

    ControllerTask controllerTask = new ControllerTask();
    int id;
    ArrayList<Task> listOfTasks;

    public void searchTask(int id) {
        this.id = id;
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
        listOfTasks = new ArrayList<>(controllerTask.searchTaskByName(name));
        int i = 0;
        if (listOfTasks.size() != 0) {
            for (Task listOfTask : listOfTasks) {
                System.out.println(++i + ": " + listOfTask);
            }
            System.out.println("Enter 'y' for select task");
            String flag = sc.nextLine();
            if ("y".equals(flag)) {
                followTasks();
            }
        } else {
            System.out.println("Tasks with this name no exist");
        }
        System.out.println("Return in MainMenu");
    }

    private void searchByDescription() {
        Scanner sc = new Scanner(System.in);
    }

    private void followTasks() {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            System.out.print("Enter number of task: ");
            num = sc.nextInt();
            --num;
            if (num >= 0 && num < listOfTasks.size()) {
                controllerTask.selectTask(listOfTasks.get(num), id);
            } else {
                System.out.println("Incorrect number. Try again");
            }
        } while (num < 0 || num > listOfTasks.size());
        System.out.println("Now you follow this task");
    }
}
