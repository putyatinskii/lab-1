package DAL;

import Classes.User;
import Db.DataBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements DAO<User> {

    static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    static final String PATH = "lab/src/main/resources/log4j.properties";

    @Override
    public void Add(User user) {

        PropertyConfigurator.configure(PATH);
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"Users\" " +
                    "VALUES (DEFAULT, " +
                    "'" + user.getUsername() + "', " +
                    "'" + user.getPassword() + "', " +
                    "'" + user.getFirstname() + "', " +
                    "'" + user.getLastname() + "', " +
                    "'" + user.getPhone() + "')" +
                    " RETURNING id";
            LOGGER.info("Insert new element in DataBase was successful");
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                Field field = user.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(user, resultSet.getInt(1));
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
    public void Remove(int id) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "DELETE FROM \"Users\" WHERE id = " + id;
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Delete element from DataBase was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void Update(User user) {
        try(Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "UPDATE \"Users\" " +
                    "SET username = '" + user.getUsername() + "', "
                    +"password = '" + user.getPassword() + "', "
                    + "firstname = '" + user.getFirstname() + "', "
                    + "lastname = '" + user.getLastname() + "', "
                    + "phone = '" + user.getPhone() + "'"
                    + " WHERE id = " + user.getId();
            statement.executeUpdate(sqlCommand);
            LOGGER.info("Modification current element was successful");
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public User Read(int id) {
        try (Connection con = DataBase.connectDB()) {
            LOGGER.info("Connect with DataBase was successful");
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"Users\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            LOGGER.info("Element read was successful");
            if (resultSet.next()) {
                return new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            LOGGER.error(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}
