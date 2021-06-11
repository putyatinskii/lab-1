package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Users {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phonename;

}
