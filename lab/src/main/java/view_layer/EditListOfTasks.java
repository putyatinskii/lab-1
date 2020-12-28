package view_layer;

import classes.ListOfTasks;
import controller.ControllerListOfTasks;
import controller.ControllerTask;
import controller.ControllerUser;

import java.util.ArrayList;
import java.util.Scanner;

public class EditListOfTasks {

    ControllerUser controllerUser = new ControllerUser();
    ControllerListOfTasks controllerListOfTasks = new ControllerListOfTasks();
    int id;
    ArrayList<ListOfTasks> listOfTasks;

    public void ListsOfTasksMenu(int id) {
        this.id = id;
        Scanner sc = new Scanner(System.in);

        System.out.println("Press 1 create new list");
        System.out.println("Press 2 remove list");
        System.out.println("Press 3 update list");
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

    private void showLists() {
        listOfTasks = controllerListOfTasks.getListsOfTasks(id);
        int i = 0;
        for (ListOfTasks listsOfTask : listOfTasks) {
            System.out.println(++i + ": " + listsOfTask);
        }
    }

    private void createList() {
        Scanner sc = new Scanner(System.in);
        ListOfTasks listOfTasks = new ListOfTasks();
        do {
            listOfTasks.setUserId(id);
            if (listOfTasks.getName() == "") {
                System.out.print("Enter list's name (no more than 50 characters): ");
                listOfTasks.setName(sc.nextLine());
            }
            if (listOfTasks.getTaskId() == -1) {
                System.out.print("Enter firstname (no more than 20 characters): ");
                //listOfTasks.setTaskId(sc.nextLine());
            }
            //controller.
            if (id == -1)
                System.out.println("invalid data format");
        } while (id == -1);
    }

    private void removeList() {

    }

    private void updateList() {

    }
}
