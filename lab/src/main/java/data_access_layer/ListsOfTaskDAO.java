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

public class ListsOfTaskDAO implements DAO<ListOfTasks> {

    static final Logger LOGGER = Logger.getLogger(ListsOfTaskDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    @Override
    public void add(ListOfTasks listOfTasks) {
        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"ListsOfTasks\" " +
                    "VALUES (DEFAULT, " +
                    "'" + listOfTasks.getUserId() + "', " +
                    "'" + listOfTasks.getTaskId() + "', " +
                    "'" + listOfTasks.getName() + "')" +
                    " RETURNING id";
            LOGGER.info("Insert new element in DataBase was successful");
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                Field field = listOfTasks.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(listOfTasks, resultSet.getInt(1));
                field.setAccessible(false);
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (NoSuchFieldException e) {
            LOGGER.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void remove(ListOfTasks listOfTasks) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" WHERE id = " + listOfTasks.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Delete element from DataBase was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void update(ListOfTasks listOfTasks) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "UPDATE \"ListsOfTasks\" " +
                    "SET userId = '" + listOfTasks.getUserId() + "', "
                    +"taskId = '" + listOfTasks.getTaskId() + "', "
                    + "name = '" + listOfTasks.getName() + "'"
                    + " WHERE id = " + listOfTasks.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Modification current element was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ListOfTasks readId(int id) {
        try (Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"ListsOfTasks\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            LOGGER.info("Element read was successful");
            if (resultSet.next()) {
                return new ListOfTasks(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public ArrayList<ListOfTasks> getList(int id) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<ListOfTasks> listOfTasks = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"ListsOfTasks\" WHERE userId = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                listOfTasks.add(new ListOfTasks(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(5)));
            }
            return listOfTasks;

        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public void deleteByUserId(int idUser) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE INTO \"ListsOfTasks\" " +
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
            String sqlCommand = "DELETE INTO \"ListsOfTasks\" " +
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
            String sqlCommand = "SELECT taskid FROM \"ListsOfTasks\" WHERE userid = " + idUser;
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

    public ArrayList<Integer> getTasksOfList(int idUser, String nameOfList) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<Integer> listOfTasksId = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT taskid FROM \"ListsOfTasks\" WHERE userid = " + idUser +
                    "AND name = '" + nameOfList + "'";
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

    public ArrayList<String> getNameOfLists(int idUser) {
        try (Connection con = DataBase.connectDB()) {
            ArrayList<String> listOfTasksId = new ArrayList<>();
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT DISTINCT name FROM \"ListsOfTasks\" WHERE userid = " + idUser;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                listOfTasksId.add(resultSet.getString(1));
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
