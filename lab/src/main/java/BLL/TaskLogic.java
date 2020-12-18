package BLL;

import Classes.Task;
import DAL.TaskDAO;
import DAL.UserDAO;
import org.apache.log4j.Logger;

public class TaskLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    TaskDAO taskDAO = new TaskDAO();

    public void searchTaskByName(String name) {
        Task task = taskDAO.searchByName(name);
        if (task != null) {
            
        }
        else {

        }
    }

    public void searchTaskByDescription(String description) {

    }


}
