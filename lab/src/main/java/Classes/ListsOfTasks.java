package Classes;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListsOfTasks {
    private int id;
    private int userId;
    private int taskId;
    private String name;

}
