package DAL;

import Classes.ListsOfTasks;
import Classes.User;
import Db.DataBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListsOfTaskDAO implements DAO<ListsOfTasks> {

    static final Logger LOGGER = Logger.getLogger(ListsOfTaskDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    @Override
    public void add(ListsOfTasks listsOfTasks) {
        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"ListsOfTasks\" " +
                    "VALUES (DEFAULT, " +
                    "'" + listsOfTasks.getUserId() + "', " +
                    "'" + listsOfTasks.getTaskId() + "', " +
                    "'" + listsOfTasks.getName() + "')" +
                    " RETURNING id";
            LOGGER.info("Insert new element in DataBase was successful");
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                Field field = listsOfTasks.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(listsOfTasks, resultSet.getInt(1));
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
    public void remove(ListsOfTasks listsOfTasks) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"ListsOfTasks\" WHERE id = " + listsOfTasks.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Delete element from DataBase was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void update(ListsOfTasks listsOfTasks) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "UPDATE \"ListsOfTasks\" " +
                    "SET userId = '" + listsOfTasks.getUserId() + "', "
                    +"taskId = '" + listsOfTasks.getTaskId() + "', "
                    + "name = '" + listsOfTasks.getName() + "'"
                    + " WHERE id = " + listsOfTasks.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Modification current element was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ListsOfTasks readId(int id) {
        try (Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"ListsOfTasks\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            LOGGER.info("Element read was successful");
            if (resultSet.next()) {
                return new ListsOfTasks(resultSet.getInt(1),
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

    @Override
    public ListsOfTasks read(ListsOfTasks listsOfTasks) {
        return null;
    }
}
