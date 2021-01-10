package business_logic_layer;

import classes.Task;
import data_access_layer.TaskDAO;
import data_access_layer.WatcherForTasksDAO;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    TaskDAO taskDAO = new TaskDAO();

    public ArrayList<Task> searchTaskByName(String name) {
        return taskDAO.searchByName(name);
    }

    public ArrayList<Task> searchTaskByDescription(String description) {
        return null;
    }

    public void add(Task task) {
        taskDAO.add(task);
    }

    public void remove(Task task) {
        taskDAO.remove(task);
    }

    public void update(Task task) {
        if (task.getName() != "" && task.getDescription() != "" && !LocalDateTime.MIN.equals(task.getAlert_time()))
        taskDAO.update(task);
    }

    public Task readId(int id) {
        return taskDAO.readId(id);
    }

    public void closingTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            task.setAlert_received(false);
            taskDAO.update(task);
        }
    }
}
