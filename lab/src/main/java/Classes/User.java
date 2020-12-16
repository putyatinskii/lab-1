package Classes;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;

    public User(String username, String password, String firstname, String lastname, String phone) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
}
