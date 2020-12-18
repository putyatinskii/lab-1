package BLL;

import Classes.User;
import DAL.TaskDAO;
import DAL.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class UserLogic {

    static final Logger LOGGER = Logger.getLogger(UserLogic.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    UserDAO userDAO = new UserDAO();

    public void signIn(String username, String password) {
        String password_sha256hex = DigestUtils.sha256Hex(password);
        int response = userDAO.checkUser(username, password_sha256hex);
        if (response == -1) {
            LOGGER.info("authentication was failed");
        }
        else {
            LOGGER.info("authentication was successful");
        }


    }

    public void signUp(String username, String password, String firstname, String lastname, String phone) {
        String password_sha256hex;
        if (password.length() <= 20) {
            password_sha256hex = DigestUtils.sha256Hex(password);
        }

    }

}
