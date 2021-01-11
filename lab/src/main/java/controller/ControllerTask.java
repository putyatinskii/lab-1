package controller;

import business_logic_layer.TaskLogic;
import business_logic_layer.TasksInListsLogic;
import business_logic_layer.WatcherForTaskLogic;
import classes.ListOfTasks;
import classes.Task;

import java.util.ArrayList;

public class ControllerTask {

    private TaskLogic taskLogic = new TaskLogic();
    private WatcherForTaskLogic watcherForTaskLogic = new WatcherForTaskLogic();
    private TasksInListsLogic tasksInListsLogic = new TasksInListsLogic();

    public ArrayList<Task> searchTaskByName(String name) {
        return taskLogic.searchTaskByName(name);
    }

    public ArrayList<Task> searchTaskByDescription(String description) {
        return taskLogic.searchTaskByDescription(description);
    }

    public Task getTaskById(int id) {
        return taskLogic.readId(id);
    }

    public void addTask(Task task) {
        taskLogic.add(task);
    }

    public void removeTask(Task task) {
        watcherForTaskLogic.deleteByTaskId(task.getId());
        tasksInListsLogic.deleteByTaskId(task.getId());
        taskLogic.remove(task);
    }

    public void updateTask(Task task) {
        taskLogic.update(task);
    }

    public void followTask(Task task, int userId) {
        watcherForTaskLogic.followTask(task, userId);
    }

    public ArrayList<Task> getWatcherTasks(int idUser) {
        return watcherForTaskLogic.getTasks(idUser);
    }

    public ArrayList<Task> getOverdueTasks(int idUser) {
        return watcherForTaskLogic.FindOverdueTasks(idUser);
    }

    public void closingTasks(ArrayList<Task> tasks) {
        taskLogic.closingTasks(tasks);
    }

    public void unfollowTask(ArrayList<Task> tasks, int userId) {
        watcherForTaskLogic.unfollowTask(tasks, userId);
    }

}
