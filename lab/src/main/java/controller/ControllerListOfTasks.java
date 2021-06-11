package controller;

import business_logic_layer.ListOfTasksLogic;
import business_logic_layer.TasksInListsLogic;
import classes.ListOfTasks;

import java.util.ArrayList;

public class ControllerListOfTasks {

    private ListOfTasksLogic listOfTasksLogic = new ListOfTasksLogic();
    private TasksInListsLogic tasksInListsLogic = new TasksInListsLogic();

    public void addNewList(ListOfTasks listOfTasks) {
        listOfTasksLogic.addNewList(listOfTasks);
    }
    public void removeThisList(ListOfTasks listOfTasks) {
        tasksInListsLogic.deleteByListId(listOfTasks.getId());
        listOfTasksLogic.removeList(listOfTasks);
    }

    public void updateThisList(int listId) {
        ListOfTasks listOfTasks = listOfTasksLogic.getList(listId);
        listOfTasksLogic.updateList(listOfTasks);
    }

    public ListOfTasks getListById(int id) {
        return listOfTasksLogic.getList(id);
    }
}
