package business_logic_layer;

import classes.ListOfTasks;
import classes.Task;
import data_access_layer.ListsOfTaskDAO;
import data_access_layer.TaskDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ListOfTaskLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    ListsOfTaskDAO listsOfTaskDAO = new ListsOfTaskDAO();
    TaskDAO taskDAO = new TaskDAO();

    public ArrayList<ListOfTasks> getListsOfTasks(int id) {
        return listsOfTaskDAO.getList(id);
    }

    public void deleteByUserId(int idUser) {
        listsOfTaskDAO.deleteByUserId(idUser);
    }

    public void deleteByTaskId(int idTask) {
        listsOfTaskDAO.deleteByTaskId(idTask);
    }

    public ArrayList<Task> getTasks(int idUser) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Integer> ListOfTasksId = listsOfTaskDAO.getTasksId(idUser);
        for (Integer id : ListOfTasksId) {
            listOfTasks.add(taskDAO.readId(id));
        }
        return listOfTasks;
    }

    public ArrayList<String> getNameOfLists(int idUser) {
        return listsOfTaskDAO.getNameOfLists(idUser);
    }

    public ArrayList<Task> getTasksOfList(int idUser, String nameOfList) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Integer> ListOfTasksId = listsOfTaskDAO.getTasksOfList(idUser, nameOfList);
        for (Integer id : ListOfTasksId) {
            listOfTasks.add(taskDAO.readId(id));
        }
        return listOfTasks;
    }
}
