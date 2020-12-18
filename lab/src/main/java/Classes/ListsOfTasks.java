package Classes;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListsOfTasks {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private int userId;
    private int taskId;
    private String name;

    public ListsOfTasks(int userId, int taskId, String name) {
        this.userId = userId;
        this.taskId = taskId;
        this.name = name;
    }
}
