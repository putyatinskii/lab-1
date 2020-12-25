import view_layer.LoginWindow;
import view_layer.Menu;

public class Main {

    public static void main(String[] args) {

       LoginWindow loginWindow = new LoginWindow();
       loginWindow.signInOrSignUp();
//        Menu menu = new Menu();
//        menu.showMenu(1);
//        User user = new User("wasd",
//                "qwerty",
//                "Ivan",
//                "Ivanov",
//                "12345678");
//        User user1 = new User("Roflan",
//                DigestUtils.sha256Hex("qwertydsa"),
//                "Petr",
//                "Ivanov",
//                "943237834");
//

//        DAO dao = new UserDAO();
//        dao.add(user1);
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


//        dao = new TaskDAO();
//        dao.Add(task);
//        //dao.Remove(5);
//        Task readTask = (Task) dao.readId(4);
//        System.out.println(readTask);
//
//
//        readTask.setAlert_time(LocalDateTime.now().withNano(0).withSecond(0));
//        readTask.setName("Set_task");
//        dao.Update(readTask);



        //dao = new ListsOfTaskDAO();
        //ListsOfTasks listsOfTasks = new ListsOfTasks(1, 3, "list1");
        //dao.add(listsOfTasks);

        //ListsOfTasks a = (ListsOfTasks) dao.readId(3);
        //a.setName("task0");
        //a.setUserId(5);
        //dao.update(a);
        //dao.remove(a);
    }

}
