package business_logic_layer;

import classes.User;
import data_access_layer.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class UserLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    private UserDAO userDAO = new UserDAO();

    public int trySignIn(String username, String password) {
        String password_sha256hex = DigestUtils.sha256Hex(password);
        int response = userDAO.checkUser(username, password_sha256hex);
        if (response == -1) {
            LOGGER.info("authentication was failed");
        }
        else {
            LOGGER.info("authentication was successful");
        }
        return response;


    }

    public int trySignUp(User user) {
        if (user.getUsername() != "" && user.getPassword() != "" && user.getFirstname() != ""
                && user.getFirstname() != "" && user.getPhone() != "") {
            userDAO.add(user);
            if (user.getId() == -1)
            user.setUsername("");
        }
        return user.getId();

    }

    public User getUser(int id) {
        return userDAO.readId(id);
    }

    public void removeUser(User user) {
        userDAO.remove(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }
}
