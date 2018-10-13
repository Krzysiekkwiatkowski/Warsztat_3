package pl.coderslab.dao;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    private static String CREATE_USER = "INSERT INTO users(username, email, password, user_group_id) VALUES (?,?,?,?)";
    private static String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ? WHERE id = ?";
    private static String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static String LOAD_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static String LOAD_ALL_USERS = "SELECT * FROM users";
    private static String LOAD_ALL_USERS_BY_GROUP_ID = "SELECT * FROM users WHERE user_group_id = ?";


    public User createUser(User user) {
        try {
            Connection connection = DbUtil.getConn();
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, generatedColumns);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            if (user.getUserGroupId() == 0) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, user.getUserGroupId());
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            if (user.getUserGroupId() == 0) {
                preparedStatement.setNull(4, Types.INTEGER);
            } else {
                preparedStatement.setInt(4, user.getUserGroupId());
            }
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User loadById(long id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.setId(resultSet.getLong("id"));
                loadedUser.setUsername(resultSet.getString("username"));
                loadedUser.setEmail(resultSet.getString("email"));
                loadedUser.setPassword(resultSet.getString("password"));
                if (resultSet.getInt("user_group_id") != Types.NULL) {
                    loadedUser.setUserGroupId(resultSet.getInt("user_group_id"));
                }
                return loadedUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User[] loadAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.setId(resultSet.getLong("id"));
                loadedUser.setUsername(resultSet.getString("username"));
                loadedUser.setEmail(resultSet.getString("email"));
                loadedUser.setPassword(resultSet.getString("password"));
                if (resultSet.getInt("user_group_id") != Types.NULL) {
                    loadedUser.setUserGroupId(resultSet.getInt("user_group_id"));
                }
                users.add(loadedUser);
            }
            User[] userTable = new User[users.size()];
            userTable = users.toArray(userTable);
            return userTable;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User[] loadAllByGroupId(int id) {
        ArrayList<User> groupUsers = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_USERS_BY_GROUP_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.setId(resultSet.getLong("id"));
                loadedUser.setUsername(resultSet.getString("username"));
                loadedUser.setEmail(resultSet.getString("email"));
                loadedUser.setPassword(resultSet.getString("password"));
                if (resultSet.getInt("user_group_id") != Types.NULL) {
                    loadedUser.setUserGroupId(resultSet.getInt("user_group_id"));
                }
                groupUsers.add(loadedUser);
            }
            User[] userTable = new User[groupUsers.size()];
            userTable = groupUsers.toArray(userTable);
            return userTable;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void showAll() throws SQLException {
        try {
            Connection connection = DbUtil.getConn();
            User[] users = UserDao.loadAll();
            for (User user : users) {
                System.out.println(user.getId() + " - " + user.getUsername() + " - " + user.getEmail() + " - " + user.getPassword() + " - " + user.getUserGroupId());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
