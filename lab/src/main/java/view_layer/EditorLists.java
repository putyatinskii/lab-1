package view_layer;

import classes.ListOfTasks;
import controller.ControllerListOfTasks;
import controller.ControllerTasksInList;
import controller.ControllerUser;

import java.util.ArrayList;
import java.util.Scanner;

public class EditorLists {

    private EditorListsOfTasks editorListsOfTasks = new EditorListsOfTasks();
    private ControllerListOfTasks controllerListOfTasks = new ControllerListOfTasks();
    private ControllerTasksInList controllerTasksInList = new ControllerTasksInList();
    private static int userId;
    private ArrayList<ListOfTasks> namesOfTasks;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void ListsOfTasksMenu() {
        Scanner sc = new Scanner(System.in);
        showLists();
        editorListsOfTasks.setUserId(userId);
        String res;
        int num;
        do {
            System.out.println("Press 1 for select some list");
            System.out.println("Press 2 for create new list");
            System.out.println("Press 3 for refresh lists of tasks");
            System.out.println("Press 0 for exit");
            res = sc.nextLine();
            switch (res) {
                case "1":
                    num = selectList();
                    if (num != -1) {
                        editorListsOfTasks.ListsOfTasksMenu(namesOfTasks.get(num).getId());
                        res = "-1";
                    }
                    else {
                        System.out.println("Incorrect number");
                    }
                    break;
                case "2":
                    editorListsOfTasks.ListsOfTasksMenu(createList());
                    break;
                case "3":
                    showLists();
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

    private void showLists() {
        namesOfTasks = controllerTasksInList.getNamesOfLists(userId);
        int i = 0;
        if (namesOfTasks.size() == 0) {
            System.out.println("You haven't any lists");
        }
        else {
            System.out.println("Your lists of tasks: ");
            for (ListOfTasks listsOfTask : namesOfTasks) {
                System.out.println(++i + ": " + listsOfTask);
            }
        }
    }

    private int selectList() {
        if (namesOfTasks.size() != 0) {
            Scanner sc = new Scanner(System.in);
            int num;
            do {
                System.out.print("Enter number of list: ");
                try {
                    num = Integer.parseInt(sc.nextLine());
                    --num;
                }
                catch (NumberFormatException ex){
                    num = -1;
                }
                if (num >= 0 && num < namesOfTasks.size()) {
                    System.out.println("You select list");
                    return num;
                } else {
                    System.out.println("Incorrect number. Try again");
                }
            } while (num < 0 || num > namesOfTasks.size());
        }
        else {
            System.out.println("You haven't any list");
        }
        return -1;
    }

    private int createList() {
        ListOfTasks listOfTasks = new ListOfTasks();
        Scanner sc = new Scanner(System.in);
        do {
            if (listOfTasks.getName() == "") {
                System.out.print("Enter name of list (no more than 30 characters): ");
                listOfTasks.setName(sc.nextLine());
            }
            controllerListOfTasks.addNewList(listOfTasks);
            if (listOfTasks.getId() == -1)
                System.out.println("invalid data format");
        } while (listOfTasks.getId() == -1);
        System.out.println("New list created successful");
        return listOfTasks.getId();
    }
}
