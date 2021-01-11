package controller;

import business_logic_layer.TasksInListsLogic;
import classes.ListOfTasks;
import classes.Task;

import java.util.ArrayList;

public class ControllerTasksInList {

    private TasksInListsLogic tasksInListsLogic = new TasksInListsLogic();

    public void addTaskOnList(int listId, int userId, int taskId) {
        tasksInListsLogic.addTask(listId, userId, taskId);
    }

    public void removeTaskFromList(int listId, int userId, int taskId) {
        tasksInListsLogic.removeTask(listId, userId, taskId);
    }

    public ArrayList<Task> getAllTasksThisUser(int idUser) {
        return tasksInListsLogic.getTasks(idUser);
    }

    public ArrayList<Task> getTasksOfList(int idUser, int idList) {
        return tasksInListsLogic.getTasksOfList(idUser, idList);
    }

    public ArrayList<ListOfTasks> getNamesOfLists(int idUser) {
        return tasksInListsLogic.getNamesOfLists(idUser);
    }

    public ArrayList<Task> getOverdueTasks(int idUser) {
        return tasksInListsLogic.FindOverdueTasks(idUser);
    }
}
