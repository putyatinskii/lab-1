package business_logic_layer;

import classes.Task;
import data_access_layer.TaskDAO;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class TaskLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    TaskDAO taskDAO = new TaskDAO();

    public ArrayList<Task> searchTaskByName(String name) {
        return taskDAO.searchByName(name);
    }

    public void searchTaskByDescription(String description) {

    }


}
