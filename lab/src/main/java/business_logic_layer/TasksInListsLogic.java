package business_logic_layer;

import classes.ListOfTasks;
import classes.Task;
import data_access_layer.ListOfTasksDAO;
import data_access_layer.TaskDAO;
import data_access_layer.TasksInListsDAO;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TasksInListsLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    private TasksInListsDAO tasksInListsDAO = new TasksInListsDAO();
    private ListOfTasksDAO listOfTasksDAO = new ListOfTasksDAO();
    private TaskDAO taskDAO = new TaskDAO();

    public void addTask(int listId, int userId, int taskId) {
        tasksInListsDAO.add(listId, userId, taskId);
    }

    public void removeTask(int listId, int userId, int taskId) {
        tasksInListsDAO.remove(listId, userId, taskId);
    }

    public void deleteByUserId(int idUser) {
        tasksInListsDAO.deleteByUserId(idUser);
    }

    public void deleteByTaskId(int idTask) {
        tasksInListsDAO.deleteByTaskId(idTask);
    }

    public void deleteByListId(int idList) {
        tasksInListsDAO.deleteByListId(idList);
    }

    public ArrayList<Task> getTasks(int idUser) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Integer> listOfTasksId = tasksInListsDAO.getTasksId(idUser);
            for (Integer id : listOfTasksId) {
                listOfTasks.add(taskDAO.readId(id));
            }
        return listOfTasks;
    }

    public ArrayList<Task> getTasksOfList(int idUser, int idList) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Integer> listOfTasksId = tasksInListsDAO.getTasksOfList(idUser, idList);
        for (Integer id : listOfTasksId) {
            listOfTasks.add(taskDAO.readId(id));
        }
        return listOfTasks;
    }

    public ArrayList<ListOfTasks> getNamesOfLists(int idUser) {
        ArrayList<ListOfTasks> listsOfTasks = new ArrayList<>();
        ArrayList<Integer> listOfId = tasksInListsDAO.getNamesOfLists(idUser);
        for (Integer id : listOfId) {
            listsOfTasks.add(listOfTasksDAO.readId(id));
        }
        return listsOfTasks;
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

}
