package business_logic_layer;

import classes.Task;
import data_access_layer.TaskDAO;
import data_access_layer.WatcherForTasksDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class WatcherForTaskLogic {
    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    WatcherForTasksDAO watcherForTasksDAO = new WatcherForTasksDAO();
    TaskDAO taskDAO = new TaskDAO();

    public void followTask(Task task, int idUser) {
        watcherForTasksDAO.followTask(task.getId(), idUser);
    }

    public void deleteByUserId(int idUser) {
        watcherForTasksDAO.deleteByUserId(idUser);
    }

    public void deleteByTaskId(int idTask) {
        watcherForTasksDAO.deleteByTaskId(idTask);
    }

    public ArrayList<Task> getTasks(int idUser) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Integer> ListOfTasksId = watcherForTasksDAO.getTasksId(idUser);
        for (Integer id : ListOfTasksId) {
            listOfTasks.add(taskDAO.readId(id));
        }
        return listOfTasks;
    }
}
