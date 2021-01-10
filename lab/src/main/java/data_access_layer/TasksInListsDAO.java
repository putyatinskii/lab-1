package data_access_layer;

import classes.ListOfTasks;
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

public class TasksInListsDAO {

    static final Logger LOGGER = Logger.getLogger(TasksInListsDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    public void add(int listId, int userId, int taskId) {
        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"ListsOfTasks\" " +
                    "VALUES (" + listId + ", " + userId + ", " + taskId + ")";
            LOGGER.info("Insert new element in DataBase was successful");
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void remove(int listId, int userId, int taskId) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" " +
                    "WHERE \"userId\" = " + userId +
                    "AND \"taskId\" = " + taskId +
                    "AND \"listId\" = " + listId;
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
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" " +
                    "WHERE \"userId\" = " + idUser;
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void deleteByListId(int idList) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" " +
                    "WHERE \"listId\" = " + idList;
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
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" " +
                    "WHERE \"taskId\" = " + idTask;
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
            String sqlCommand = "SELECT DISTINCT \"taskId\" FROM \"ListsOfTasks\" WHERE \"userId\" = " + idUser;
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

    public ArrayList<Integer> getTasksOfList(int idUser, int idList) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<Integer> listOfTasksId = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT \"taskId\" FROM \"ListsOfTasks\" WHERE \"userId\" = " + idUser
                    + "AND \"listId\" = " + idList;
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

    public ArrayList<Integer> getNamesOfLists(int idUser) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<Integer> listsId = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT DISTINCT \"listId\" FROM \"ListsOfTasks\" WHERE \"userId\" = " + idUser;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                listsId.add(resultSet.getInt(1));
            }
            return listsId;

        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
