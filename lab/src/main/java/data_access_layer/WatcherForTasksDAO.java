package data_access_layer;

import classes.ListOfTasks;
import database_connection.DataBase;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WatcherForTasksDAO {

    static final Logger LOGGER = Logger.getLogger(TaskDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    public void followTask(int idTask, int idUser) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"WatcherForTasks\" " +
                    "VALUES (" + idTask + ", " + idUser + ")";
            statement.executeUpdate(sqlCommand);

        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void deleteByUserId(int idUser) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE INTO \"WatcherForTasks\" " +
                    "WHERE userid = " + idUser;
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void deleteByTaskId(int idTask) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE INTO \"WatcherForTasks\" " +
                    "WHERE taskid = " + idTask;
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public ArrayList<Integer> getTasksId(int idUser) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<Integer> listOfTasksId = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT taskid FROM \"WatcherForTasks\" WHERE userid = " + idUser;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                listOfTasksId.add(resultSet.getInt(1));
            }
            return listOfTasksId;

        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
