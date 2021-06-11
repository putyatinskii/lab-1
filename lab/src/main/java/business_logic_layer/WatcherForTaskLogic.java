package business_logic_layer;

import classes.Task;
import data_access_layer.TaskDAO;
import data_access_layer.WatcherForTasksDAO;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WatcherForTaskLogic {
    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    private WatcherForTasksDAO watcherForTasksDAO = new WatcherForTasksDAO();
    private TaskDAO taskDAO = new TaskDAO();

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

    public ArrayList<Task> FindOverdueTasks(int idUser) {
        ArrayList<Task> tasks = getTasks(idUser);
        ArrayList<Task> resTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (LocalDateTime.now().isAfter(task.getAlert_time()) && task.getAlert_received() == true) {
                resTasks.add(task);
            }
        }
        return resTasks;
    }

    public void unfollowTask(ArrayList<Task> tasks, int idUser) {
        for (Task task : tasks) {
            watcherForTasksDAO.unfollowTask(task.getId(), idUser);
        }
    }
}
