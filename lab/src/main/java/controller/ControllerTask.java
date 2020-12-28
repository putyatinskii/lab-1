package controller;

import business_logic_layer.ListOfTaskLogic;
import business_logic_layer.TaskLogic;
import business_logic_layer.WatcherForTaskLogic;
import classes.ListOfTasks;
import classes.Task;

import java.util.ArrayList;

public class ControllerTask {

    TaskLogic taskLogic = new TaskLogic();
    WatcherForTaskLogic watcherForTaskLogic = new WatcherForTaskLogic();
    ListOfTaskLogic listOfTaskLogic = new ListOfTaskLogic();

    public ArrayList<Task> searchTaskByName(String name) {
        return taskLogic.searchTaskByName(name);
    }

    public void selectTask(Task task, int userId) {
        watcherForTaskLogic.followTask(task, userId);
    }

}
