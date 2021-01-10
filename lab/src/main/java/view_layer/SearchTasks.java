package view_layer;

import classes.Task;
import controller.ControllerTask;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchTasks {

    private ControllerTask controllerTask = new ControllerTask();
    private int userId;
    private ArrayList<Task> listOfTasks;

    public void searchTask(int id) {
        this.userId = id;
        Scanner sc = new Scanner(System.in);
        int res = 0;
        do {
            System.out.println("Press 1 for search task by name");
            System.out.println("Press 2 for search task by description");
            System.out.println("Press 3 for show observable tasks");
            System.out.println("Press 0 for exit");
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
                case 3:
                    showWatcherTasks();
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
        System.out.println("Return in MainMenu");

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
                controllerTask.followTask(listOfTasks.get(num), userId);
            } else {
                System.out.println("Incorrect number. Try again");
            }
        } while (num < 0 || num > listOfTasks.size());
        System.out.println("Now you follow this task");
    }

    private void showWatcherTasks() {
        ArrayList<Task> taskArrayList = controllerTask.getWatcherTasks(userId);
        if (taskArrayList.size() != 0) {
            for (Task task : taskArrayList) {
                System.out.println("1: " + task);
            }
        }
        else {
            System.out.println("You aren't watch for any tasks");
        }
    }

    public void getOverdueObservableTasks(int id) {
        this.userId = id;
        int i = 0;
        listOfTasks = controllerTask.getOverdueTasks(id);
        if (listOfTasks.size() != 0) {
            System.out.println("Your overdue observable tasks: ");
            for (Task task : listOfTasks) {
                System.out.println(++i + ": " + task);
            }
            unfollowOverdueTasks();
        }
        else {
            System.out.println("You haven't any overdue observable tasks");
        }
        listOfTasks = null;
    }

    private void unfollowOverdueTasks() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("You can unfollow to observable tasks");
        int num;
        do {
            System.out.print("Enter number of task or enter 0 for exit: ");
            num = sc.nextInt();
            --num;
            if (num >= 0 && num < listOfTasks.size()) {
                tasks.add(listOfTasks.get(num));
            } else if (num != -1) {
                System.out.println("Incorrect number. Try again");
            }
        } while (num != -1);
        controllerTask.unfollowTask(tasks, userId);
        System.out.println("You unfollowing of tasks successfully");
    }

}
