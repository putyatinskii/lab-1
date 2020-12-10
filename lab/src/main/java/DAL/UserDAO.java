package DAL;

import Classes.User;
import Db.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements DAO<User> {
    @Override
    public void Add(User user) {
        try(Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "INSERT INTO \"Users\" " +
                    "VALUES (DEFAULT, " +
                    "'" + user.getUsername() + "', " +
                    "'" + user.getPassword() + "', " +
                    "'" + user.getFirstname() + "', " +
                    "'" + user.getLastname() + "', " +
                    "'" + user.getPhone() + "')";
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
            String sqlCommand = "DELETE FROM \"Users\" WHERE id = " + id;
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void Update(User user) {

    }

    @Override
    public User Read(int id) {
        try (Connection con = DataBase.connectDB()) {
            Statement statement = con.createStatement();
            String sqlCommand = "SELECT * FROM \"Users\" WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            if (resultSet.next()) {
                return new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
