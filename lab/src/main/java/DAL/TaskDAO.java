package DAL;

import Classes.Task;
import Classes.User;
import Db.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskDAO implements DAO<Task> {
    @Override
    public void Add(Task task) {
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"Tasks\" " +
                    "VALUES (DEFAULT, " +
                    "'" + task.getName() + "', " +
                    "'" + task.getDescription() + "', " +
                    "'" + task.getAlert_time() + "', " +
                    "'" + task.getAlert_received() + "')";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(int id) {
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"Tasks\" WHERE id = " + id;
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Task task) {

    }

    @Override
    public Task Read(int id) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"Tasks\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                return new Task(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime().withNano(0),
                        resultSet.getBoolean(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
