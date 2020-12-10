package Classes;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    //private int id;
    private String name;
    private String description;
    private LocalDateTime alert_time;
    private Boolean alert_received;
}
