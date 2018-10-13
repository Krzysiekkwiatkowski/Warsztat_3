package pl.coderslab.model;

import pl.coderslab.model.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInitializer {

    private static String CREATE_USERS = "CREATE TABLE users(id BIGINT(20) AUTO_INCREMENT, username VARCHAR(255), email VARCHAR(255) UNIQUE, password VARCHAR(245), user_group_id INT(11), PRIMARY KEY(id), FOREIGN KEY(user_group_id) REFERENCES user_group(id))";
    private static String CREATE_USER_GROUP ="CREATE TABLE user_group(id INT(11) AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY(id))";
    private static String CREATE_EXERCISE ="CREATE TABLE exercise(id INT(11) AUTO_INCREMENT, title VARCHAR(255), description TEXT, PRIMARY KEY(id))";
    private static String CREATE_SOLUTION ="CREATE TABLE solution(id INT(11) AUTO_INCREMENT, created DATETIME, updated DATETIME, description TEXT, exercise_id INT(11), users_id BIGINT(20), PRIMARY KEY(id), FOREIGN KEY (exercise_id) REFERENCES exercise(id), FOREIGN KEY (users_id) REFERENCES users(id))";

    public static void main(String[] args) {
        try(Connection connection = DbUtil.getConn()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_USER_GROUP);
            statement.executeUpdate(CREATE_USERS);
            statement.executeUpdate(CREATE_EXERCISE);
            statement.executeUpdate(CREATE_SOLUTION);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
