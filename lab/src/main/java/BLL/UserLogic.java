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

    public int trySignUp(String username, String password, String firstname, String lastname, String phone) {
        User user = new User();
        if (user.getUsername() == "") {
            user.setUsername(username);
        }
        if (user.getPassword() == "") {
            user.setPassword(DigestUtils.sha256Hex(password));
        }
        if (user.getFirstname() == "") {
            user.setFirstname(firstname);
        }
        if (user.getLastname() == "") {
            user.setLastname(lastname);
        }
        if (user.getPhone() == "") {
            user.setPhone(phone);
        }

        if (user.getUsername() != "" && user.getPassword() != "" && user.getFirstname() != ""
                && user.getFirstname() != "" && user.getPhone() != "") {
            userDAO.add(user);
            if (user.getId() == -1)
                username = "";
            user.setUsername(username);
        }
        return user.getId();

    }

}
