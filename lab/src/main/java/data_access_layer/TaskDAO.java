package data_access_layer;

import classes.Task;
import database_connection.DataBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskDAO implements DAO<Task> {

    static final Logger LOGGER = Logger.getLogger(TaskDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    @Override
    public void add(Task task) {
        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"Tasks\" " +
                    "VALUES (DEFAULT, " +
                    "'" + task.getName() + "', " +
                    "'" + task.getDescription() + "', " +
                    "'" + task.getAlert_time() + "', " +
                    "'" + task.getAlert_received() + "')" +
                    " RETURNING id";
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                Field field = task.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(task, resultSet.getInt(1));
                field.setAccessible(false);
                LOGGER.info("Insert new element in DataBase was successful");
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        } catch (NoSuchFieldException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void remove(Task task) {
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"Tasks\" WHERE id = " + task.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Delete element from DataBase was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Task task) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "UPDATE \"Tasks\" " +
                    "SET name = '" + task.getName() + "', "
                    +"description = '" + task.getDescription() + "', "
                    + "alert_time = '" + task.getAlert_time() + "', "
                    + "alert_received = '" + task.getAlert_received() + "'"
                    + " WHERE id = " + task.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Modification current element was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Task readId(int id) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"Tasks\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            LOGGER.info("Element read was successful");
            if (resultSet.next()) {
                return new Task(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime().withNano(0),
                        resultSet.getBoolean(5));
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public ArrayList<Task> searchByName(String name) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<Task> listOfTasks= new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"Tasks\" WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                listOfTasks.add(new Task(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4).toLocalDateTime().withNano(0),
                        resultSet.getBoolean(5)));
            }
            return listOfTasks;

        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
