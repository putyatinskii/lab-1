package business_logic_layer;

import classes.ListOfTasks;
import data_access_layer.ListOfTasksDAO;
import data_access_layer.TaskDAO;
import data_access_layer.TasksInListsDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ListOfTasksLogic {
    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    private ListOfTasksDAO listOfTasksDAO = new ListOfTasksDAO();

    public void addNewList(ListOfTasks listOfTasks) {
        if (listOfTasks.getName() != "") {
            listOfTasksDAO.add(listOfTasks);
        }
    }

    public void removeList(ListOfTasks listOfTasks) {
        listOfTasksDAO.remove(listOfTasks);
    }

    public void updateList(ListOfTasks listOfTasks) {
        listOfTasksDAO.update(listOfTasks);
    }

    public ListOfTasks getList(int id) {
        return listOfTasksDAO.readId(id);
    }
}
