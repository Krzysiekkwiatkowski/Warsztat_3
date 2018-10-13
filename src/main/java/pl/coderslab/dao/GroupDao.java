package pl.coderslab.dao;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDao {

    private static String CREATE_GROUP = "INSERT INTO user_group (name) VALUES (?)";
    private static String UPDATE_GROUP = "UPDATE user_group SET name = ? WHERE id = ?";
    private static String DELETE_GROUP = "DELETE FROM user_group WHERE id = ?";
    private static String LOAD_GROUP_BY_ID = "SELECT * FROM user_group WHERE id = ?";
    private static String LOAD_ALL_GROUPS = "SELECT * FROM user_group";

    public Group createGroup(Group group) {
        try {
            Connection connection = DbUtil.getConn();
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GROUP, generatedColumns);
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGroup(Group group) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Group loadById(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_GROUP_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group loadedGroup = new Group();
                loadedGroup.setId(resultSet.getInt("id"));
                loadedGroup.setName(resultSet.getString("name"));
                return loadedGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Group[] loadAll() {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_GROUPS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group loadedGroup = new Group();
                loadedGroup.setId(resultSet.getInt("id"));
                loadedGroup.setName(resultSet.getString("name"));
                groups.add(loadedGroup);
            }
            Group[] groupTable = new Group[groups.size()];
            groupTable = groups.toArray(groupTable);
            return groupTable;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showAll() {
        try {
            Connection connection = DbUtil.getConn();
            Group[] groups = GroupDao.loadAll();
            for (Group group : groups) {
                System.out.println(group.getId() + " - " + group.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
