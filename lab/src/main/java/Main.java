import Classes.Task;
import Classes.User;
import DAL.DAO;
import DAL.TaskDAO;
import DAL.UserDAO;
import Db.DataBase;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        User user = new User("11Van11",
                "qwerty",
                "Ivan",
                "Ivanov",
                "12345678");
        User user1 = new User("222Van222",
                "qwerty",
                "Ivan",
                "Ivanov",
                "12345678");
        DAO dao = new UserDAO();
        //dao.Add(user1);
        //dao.Remove(2);
        //User readUser = (User)dao.Read(3);
        //System.out.println(readUser);


        LocalDateTime date = LocalDateTime.now();
        Task task = new Task("New_Task",
                "description this task",
                date.withNano(0).withSecond(0),
                true);
        dao = new TaskDAO();
        //dao.Add(task);
        //dao.Remove(2);
        Task readTask = (Task) dao.Read(4);
        System.out.println(readTask);
    }

}
