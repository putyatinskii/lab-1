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
    ArrayList<Task> tasks;
    private int userId;
    private int listId;
    private int taskId = -1;

    public void ListsOfTasksMenu(int userId, int listId) {
        this.userId = userId;
        this.listId = listId;
        System.out.println("Your tasks: ");
        showTasks();
        Scanner sc = new Scanner(System.in);
        int res;
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
            res = sc.nextInt();
            switch (res) {
                case 1:
                    updateList();
                    break;
                case 2:
                    removeList();
                    res = -1;
                    break;
                case 3:
                    showTasks();
                    break;
                case 4:
                    selectTask();
                    break;
                case 5:
                    addTaskInList();
                    break;
                case 6:
                    if (taskId != -1) {
                        removeTaskFromList();
                    }
                    else {
                        System.out.println("Task not select");
                    }
                    break;
                case 7:
                    if (taskId != -1) {
                        editorTasks.setUserId(userId);
                        editorTasks.removeTask(taskId);
                    }
                    else {
                        System.out.println("Task not select");
                    }
                    break;
                case 8:
                    if (taskId != -1) {
                        editorTasks.setUserId(userId);
                        editorTasks.updateTask(taskId);
                    }
                    else {
                        System.out.println("Task not select");
                    }
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
        System.out.println("Tasks: ");
        tasks = controllerTasksInList.getTasksOfList(userId, listId);
        int i = 0;
        for (Task task : tasks) {
            System.out.println(++i + ": " + task);
        }

    }

    private void selectTask() {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            System.out.print("Enter number of task: ");
            num = sc.nextInt();
            --num;
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
                    res = "ok";
                    break;
                case "2":
                    editorTasks.setUserId(userId);
                    editorTasks.showMyTasks();
                    this.taskId = editorTasks.selectTask();
                    controllerTasksInList.addTaskOnList(listId, userId, taskId);
                    res = "ok";
                    break;
                default:
                    System.out.println("Incorrect data. Try again");
                    break;
            }
        } while (res != "ok");
    }

}
