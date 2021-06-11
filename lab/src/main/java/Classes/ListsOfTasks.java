package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListsOfTasks {
    private int id;
    private int userId;
    private int taskId;
    private String name;

}
