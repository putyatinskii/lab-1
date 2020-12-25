package classes;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Setter(value = AccessLevel.NONE)
    private int id;
    private String name;
    private String description;
    private LocalDateTime alert_time;
    private Boolean alert_received;

    public Task(String name, String description, LocalDateTime alert_time, Boolean alert_received) {
        this.name = name;
        this.description = description;
        this.alert_time = alert_time;
        this.alert_received = alert_received;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", alert_time=" + alert_time +
                ", alert_received=" + alert_received +
                '}';
    }
}
