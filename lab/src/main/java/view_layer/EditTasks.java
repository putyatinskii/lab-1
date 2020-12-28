package view_layer;

import classes.Task;
import controller.ControllerUser;

import java.util.Scanner;

public class EditTasks {
    ControllerUser controllerUser = new ControllerUser();

    public void createTask() {
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
            if (task.getAlert_time() != null) {

            }
            //controller.
            if (id == -1)
                System.out.println("invalid data format");
        } while (id == -1);
    }

    public void updateTask() {

    }

    public void removeTask() {

    }

    public void showWatchersTasks() {

    }
}
