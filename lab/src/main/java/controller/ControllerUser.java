package controller;

import business_logic_layer.TaskLogic;
import business_logic_layer.TasksInListsLogic;
import business_logic_layer.UserLogic;
import business_logic_layer.WatcherForTaskLogic;
import classes.ListOfTasks;
import classes.Task;
import classes.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class ControllerUser {
    private UserLogic userLogic = new UserLogic();
    private WatcherForTaskLogic watcherForTaskLogic = new WatcherForTaskLogic();
    private TasksInListsLogic tasksInListsLogic = new TasksInListsLogic();

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
            watcherForTaskLogic.deleteByUserId(id);
            tasksInListsLogic.deleteByUserId(id);
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
