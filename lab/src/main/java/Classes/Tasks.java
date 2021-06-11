package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Tasks {
    private int id;
    private String name;
    private String description;
    private Date alert_time;
    private Boolean alert_received;
}
