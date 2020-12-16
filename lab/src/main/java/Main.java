import Classes.Task;
import Classes.User;
import DAL.DAO;
import DAL.TaskDAO;
import DAL.UserDAO;
import Db.DataBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

//        User user = new User("wasd",
//                "qwerty",
//                "Ivan",
//                "Ivanov",
//                "12345678");
//        User user1 = new User("Roflan",
//                "qwertydsa",
//                "Petr",
//                "Ivanov",
//                "943237834");
//
//
          DAO dao = new UserDAO();
//        dao.Add(user1);
//        dao.Remove(1);
//        User readUser = (User)dao.Read(3);
//        System.out.println(readUser);
//
//        readUser.setUsername("IVAN123456");
//        readUser.setPassword("ytrewq");
//        dao.Update(readUser);



//        LocalDateTime date = LocalDateTime.now();
//        Task task = new Task("Cur_Task",
//                "description for Cur_task",
//                date.withNano(0).withSecond(0),
//                true);


        dao = new TaskDAO();
//        dao.Add(task);
//        //dao.Remove(5);
        Task readTask = (Task) dao.Read(4);
        System.out.println(readTask);
//
//
//        readTask.setAlert_time(LocalDateTime.now().withNano(0).withSecond(0));
//        readTask.setName("Set_task");
//        dao.Update(readTask);

    }

}
