package classes;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListOfTasks {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private int userId;
    private int taskId;
    private String name;

    public ListOfTasks(int userId, int taskId, String name) {
        this.userId = userId;
        this.taskId = taskId;
        this.name = name;
    }
}
