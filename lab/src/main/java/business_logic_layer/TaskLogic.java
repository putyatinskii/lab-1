package business_logic_layer;

import classes.Task;
import data_access_layer.TaskDAO;
import data_access_layer.WatcherForTasksDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class TaskLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    TaskDAO taskDAO = new TaskDAO();
    WatcherForTasksDAO watcherForTasksDAO = new WatcherForTasksDAO();

    public ArrayList<Task> searchTaskByName(String name) {
        return taskDAO.searchByName(name);
    }

    public void searchTaskByDescription(String description) {

    }

    public void add(Task task) {
        taskDAO.add(task);
    }


    public void remove(Task task) {
        taskDAO.remove(task);
    }


    public void update(Task task) {
        taskDAO.update(task);
    }

    public Task readId(int id) {
        return taskDAO.readId(id);
    }
}
