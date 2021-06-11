package classes;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id = -1;
    private String name = "";
    private String description = "";
    private LocalDateTime alert_time = LocalDateTime.MIN;
    private Boolean alert_received = true;

    public Task(String name, String description, LocalDateTime alert_time, Boolean alert_received) {
        this.name = name;
        this.description = description;
        this.alert_time = alert_time;
        this.alert_received = alert_received;
    }

    public void setName(String name) {
        if (name.length() <= 20 && name.length() >= 5) {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        if (description.length() <= 1000 && description.length() >= 5) {
            this.description = description;
        }
    }

    public void setAlert_time(LocalDateTime alert_time) {
        if (!LocalDateTime.now().isAfter(alert_time)) {
            this.alert_time = alert_time;
        }
    }

    public void setAlert_received(Boolean alert_received) {
        this.alert_received = alert_received;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", alert_time=" + alert_time +
                '}';
    }
}
