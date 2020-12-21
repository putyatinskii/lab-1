package Classes;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id = -1;
    private String username = "";
    private String password = "";
    private String firstname = "";
    private String lastname = "";
    private String phone = "";

    public User(String username, String password, String firstname, String lastname, String phone) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public void setUsername(String username) {
        if (username.length() <= 20 && username.length() >= 5) {
            this.username = username;
        }
        else {
            username = "";
        }
    }

    public void setPassword(String password) {
        if (password.length() <= 20 && password.length() >= 5) {
            this.password = password;
        }
        else {
            password = "";
        }
    }

    public void setFirstname(String firstname) {
        if (firstname.length() <= 20 && firstname.length() >= 4) {
            this.firstname = firstname;
        }
        else {
            firstname = "";
        }
    }

    public void setLastname(String lastname) {
        if (lastname.length() <= 20 && firstname.length() >= 4) {
            this.lastname = lastname;
        }
        else {
            lastname = "";
        }
    }

    public void setPhone(String phone) {
        if (phone.length() == 12) {
            this.phone = phone;
        }
        else {
            phone = "";
        }
    }
}
