package data_access_layer;

import classes.ListOfTasks;
import database_connection.DataBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListOfTasksDAO implements DAO<ListOfTasks> {

    static final Logger LOGGER = Logger.getLogger(ListOfTasksDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    @Override
    public void add(ListOfTasks listOfTasks) {
        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"NamesOfListsWithTasks\" " +
                    "VALUES (DEFAULT, " +
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
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"NamesOfListsWithTasks\" WHERE id = " + listOfTasks.getId();
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
            Statement statement = con.createStatement();
            String sqlCommand = "UPDATE \"NamesOfListsWithTasks\" " +
                    "SET name = '" + listOfTasks.getName() + "'"
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
            String sqlCommand = "SELECT * FROM \"NamesOfListsWithTasks\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            LOGGER.info("Element read was successful");
            if (resultSet.next()) {
                return new ListOfTasks(resultSet.getInt(1),
                        resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
