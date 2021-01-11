package view_layer;

import classes.Task;
import classes.User;
import controller.ControllerTask;
import controller.ControllerTasksInList;
import controller.ControllerUser;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorTasks {
    private ControllerTask controllerTask = new ControllerTask();
    private ControllerTasksInList controllerTasksInList = new ControllerTasksInList();
    private static int userId;
    private ArrayList<Task> tasks;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void editTasksMenu() {
        int taskId = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Your tasks: ");
        showMyTasks();
        if (tasks.size() != 0) {
            System.out.println("Select a task from the list");
            taskId = selectTask();
            String res;
            do {
                System.out.println("Press 1 for remove task");
                System.out.println("Press 2 for update task");
                System.out.println("Press 0 for exit");
                res = sc.nextLine();
                switch (res) {
                    case "1":
                        removeTask(taskId);
                        break;
                    case "2":
                        updateTask(taskId);
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
            System.out.println("Return in MainMenu");
        }
    }

    public int createTask() {
        Scanner sc = new Scanner(System.in);
        Task task = new Task();
        int id =0;
        do {
            if (task.getName() == "") {
                System.out.print("Enter name of task (no more than 20 characters): ");
                task.setName(sc.nextLine());
            }
            if (task.getDescription() == "") {
                System.out.print("Enter description of task (no more than 1000 characters): ");
                task.setDescription(sc.nextLine());
            }
            if (LocalDateTime.MIN.equals(task.getAlert_time())) {
                System.out.print("Enter expiration date (format date: 2000-12-12): ");
                String date = sc.nextLine();
                System.out.print("Enter expiration time (format time: 12:00:00): ");
                String time = sc.nextLine();
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);
                    task.setAlert_time(dateTime);
                } catch (DateTimeParseException e) {

                }
            }
            controllerTask.addTask(task);
            id = task.getId();
            if (id == -1)
                System.out.println("invalid data format");
        } while (id == -1);
        return id;
    }

    public void updateTask(int taskId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 for change name");
        System.out.println("Press 2 for change description");
        System.out.println("Press 3 for change expiration date");
        System.out.println("Press 0 for exit");
        Task task = controllerTask.getTaskById(taskId);
        String res;
        do {
            res = sc.nextLine();
            //sc.nextLine();
            switch (res) {
                case "1":
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    task.setName(name);
                    if (!task.getName().equals(name))
                        System.out.println("Incorrect name");
                    break;
                case "2":
                    System.out.print("Enter new description: ");
                    String description = sc.nextLine();
                    task.setDescription(description);
                    if (!task.getDescription().equals(description))
                        System.out.println("Incorrect description");
                    break;
                case "3":
                    System.out.print("Enter expiration date (format date: 2000-12-12): ");
                    String date = sc.nextLine();
                    System.out.print("Enter expiration time (format time: 12:00:00): ");
                    String time = sc.nextLine();
                    String dateWithTime = date + "T" + time;
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dateWithTime);
                        task.setAlert_time(dateTime);
                    } catch (DateTimeParseException e) {

                    }
                    if (!task.getAlert_time().equals(dateWithTime))
                        System.out.println("Incorrect Date and time");
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
        controllerTask.updateTask(task);
        System.out.println("Task updated successful");
    }

    public void removeTask(int taskId) {
        Task task = controllerTask.getTaskById(taskId);
        controllerTask.removeTask(task);
        System.out.println("Task removed successful");
    }

    public void showMyTasks() {
        tasks = controllerTasksInList.getAllTasksThisUser(userId);
        int i = 0;
        if (tasks.size() != 0) {
            for (Task task : tasks) {
                System.out.println(++i + ": " + task);
            }
        }
        else {
            System.out.println("You haven't any tasks");
        }
    }

    public int selectTask() {
        Scanner sc = new Scanner(System.in);
        int num;
        int taskId = -1;
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
                taskId = tasks.get(num).getId();
            } else {
                System.out.println("Incorrect number. Try again");
            }
        } while (num < 0 || num > tasks.size());
        System.out.println("You select task");
        return taskId;
    }

    public void getOverdueMyTasks() {
        int i = 0;
        tasks = controllerTasksInList.getOverdueTasks(userId);
        if (tasks.size() != 0) {
            System.out.println("Your overdue tasks: ");
            for (Task task : tasks) {
                System.out.println(++i + ": " + task);
            }
            closeOverdueTasks();
        }
        else {
            System.out.println("You haven't any overdue tasks");
        }
        tasks = null;
    }

    private void closeOverdueTasks() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> closingtasks = new ArrayList<>();
        System.out.println("You can closing observable tasks");
        int num;
        do {
            System.out.print("Enter number of task or enter 0 for exit: ");
            num = sc.nextInt();
            --num;
            if (num >= 0 && num < tasks.size()) {
                closingtasks.add(tasks.get(num));
            } else if (num != -1) {
                System.out.println("Incorrect number. Try again");
            }
        } while (num != -1);
        if (closingtasks.size() > 0) {
            controllerTask.closingTasks(closingtasks);
            System.out.println("tasks closed successfully");
        }
    }

}
