package controller;

import business_logic_layer.ListOfTaskLogic;
import business_logic_layer.TaskLogic;
import business_logic_layer.UserLogic;
import classes.ListOfTasks;
import classes.Task;
import classes.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class ControllerUser {
    UserLogic userLogic = new UserLogic();
    TaskLogic taskLogic = new TaskLogic();
    ListOfTaskLogic listOfTaskLogic = new ListOfTaskLogic();

    public int trySignUp(User user) {
        return userLogic.trySignUp(user);
    }

    public int trySignIn(String username, String password) {
        return userLogic.trySignIn(username, password);
    }

    public User getUserById(int id) {
        return userLogic.getUser(id);
    }

    public Boolean tryRemoveUser(int id, String password) {
        User user = userLogic.getUser(id);
        if (user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            userLogic.removeUser(user);
            return true;
        }
        else
            return false;
    }

    public void tryUpdateThisUser(User user) {
        userLogic.updateUser(user);
    }


}
