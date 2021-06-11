package view_layer;

import classes.ListOfTasks;
import classes.Task;
import controller.ControllerListOfTasks;
import controller.ControllerTask;
import controller.ControllerTasksInList;

import java.util.ArrayList;
import java.util.Scanner;

public class EditorListsOfTasks {

    private ControllerListOfTasks controllerListOfTasks = new ControllerListOfTasks();
    private ControllerTasksInList controllerTasksInList = new ControllerTasksInList();
    private EditorTasks editorTasks = new EditorTasks();
    private ArrayList<Task> tasks;
    private static int userId;
    private int listId;
    private int taskId = -1;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void ListsOfTasksMenu(int listId) {
        this.listId = listId;
        System.out.println("Your tasks: ");
        showTasks();
        Scanner sc = new Scanner(System.in);
        String res;
        do {
            System.out.println("Press 1 for update this list");
            System.out.println("Press 2 for remove this list");
            System.out.println("Press 3 for show tasks in this list");
            System.out.println("Press 4 for select task from this list");
            System.out.println("Press 5 for add task in list");
            System.out.println("Press 6 for remove task from list");
            System.out.println("Press 7 for remove current task");
            System.out.println("Press 8 for update current task");
            System.out.println("Press 0 for exit");
            res = sc.nextLine();
            switch (res) {
                case "1":
                    updateList();
                    break;
                case "2":
                    removeList();
                    res = "-1";
                    break;
                case "3":
                    showTasks();
                    break;
                case "4":
                    selectTask();
                    break;
                case "5":
                    addTaskInList();
                    break;
                case "6":
                    if (taskId != -1) {
                        removeTaskFromList();
                    }
                    else {
                        System.out.println("Task not select");
                    }
                    break;
                case "7":
                    if (taskId != -1) {
                        editorTasks.setUserId(userId);
                        editorTasks.removeTask(taskId);
                    }
                    else {
                        System.out.println("Task not select");
                    }
                    break;
                case "8":
                    if (taskId != -1) {
                        editorTasks.setUserId(userId);
                        editorTasks.updateTask(taskId);
                    }
                    else {
                        System.out.println("Task not select");
                    }
                    break;
                case "0":
                    res = "-1";
                    break;
                default:
                    res = "-10";
                    System.out.println("incorrect value. Try again");
                    break;
            }
        } while (res != "-1");
        System.out.println("Return in ListsOfTasksMenu");
    }

    private void removeList() {
        ListOfTasks listOfTasks = controllerListOfTasks.getListById(listId);
        controllerListOfTasks.removeThisList(listOfTasks);
        System.out.println("This list was remove successful");
    }

    private void updateList() {
        Scanner sc = new Scanner(System.in);
        ListOfTasks listOfTasks = controllerListOfTasks.getListById(listId);
        boolean f = false;
        do {
            System.out.println("Enter new name: ");
            String name = sc.nextLine();
            listOfTasks.setName(name);
            if (!listOfTasks.getName().equals(name))
                System.out.println("Incorrect name");
            else {
                f = true;
            }
        } while (!f);
        controllerListOfTasks.updateThisList(listId);
        System.out.println("This list was update successful");
    }

    private void showTasks() {
        tasks = controllerTasksInList.getTasksOfList(userId, listId);
        if (tasks.size() != 0) {
            int i = 0;
            for (Task task : tasks) {
                System.out.println(++i + ": " + task);
            }
        }
        else {
            System.out.println("You haven't any tasks");
        }

    }

    private void selectTask() {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            System.out.print("Enter number of task: ");
            try {
                num = Integer.parseInt(sc.nextLine());
                --num;
            }
            catch (NumberFormatException ex){
                num = -1;
            }
            if (num >= 0 && num < tasks.size()) {
                this.taskId = tasks.get(num).getId();
            } else {
                System.out.println("Incorrect number. Try again");
            }
        } while (num < 0 || num > tasks.size());
        System.out.println("You select task");
    }

    private void removeTaskFromList() {
        controllerTasksInList.removeTaskFromList(listId, userId, taskId);
        taskId = -1;
        System.out.println("task removed from list successful");
    }

    private void addTaskInList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 if you want add new task");
        System.out.println("Press 2 if you want add already existing task");
        String res;
        do {
            res = sc.nextLine();
            switch (res) {
                case "1":
                    taskId = editorTasks.createTask();
                    controllerTasksInList.addTaskOnList(listId, userId, taskId);
                    System.out.println("task was created successfully");
                    res = "-1";
                    break;
                case "2":
                    editorTasks.showMyTasks();
                    this.taskId = editorTasks.selectTask();
                    controllerTasksInList.addTaskOnList(listId, userId, taskId);
                    res = "-1";
                    break;
                default:
                    System.out.println("Incorrect data. Try again");
                    break;
            }
        } while (res != "-1");
    }

}
