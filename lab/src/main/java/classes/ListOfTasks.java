package classes;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfTasks {
    private int id = -1;
    private String name = "";

    public ListOfTasks(String name) {
        this.name = name;
    }

    public void setName(String name) {
        if (name.length() <= 30 && name.length() >= 5) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "NameOfList = '" + name + '\'' + '}';
    }
}
