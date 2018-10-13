package pl.coderslab.dao;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDao {

    private static String CREATE_EXERCISE = "INSERT INTO exercise(title, description) VALUES (?,?)";
    private static String UPDATE_EXERCISE = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
    private static String DELETE_EXERCISE = "DELETE FROM exercise WHERE id = ?";
    private static String LOAD_EXERCISE_BY_ID = "SELECT * FROM exercise WHERE id = ?";
    private static String LOAD_ALL_EXERCISES = "SELECT * FROM exercise";
    private static String LOAD_ALL_EXERCISE_WITHOUT_SOLUTION = "select exercise.id, exercise.title, exercise.description, solution.description, solution.exercise_id, solution.users_id from solution join exercise on solution.exercise_id = exercise.id where users_id = ? AND solution.description is null";

    public Exercise createExercise(Exercise exercise) {
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXERCISE, generatedColumns);
            preparedStatement.setString(1, exercise.getTitle());
            preparedStatement.setString(2, exercise.getDescription());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateExercise(Exercise exercise) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXERCISE);
            preparedStatement.setString(1, exercise.getTitle());
            preparedStatement.setString(2, exercise.getDescription());
            preparedStatement.setInt(3, exercise.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXERCISE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Exercise loadById(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_EXERCISE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise loadedExercise = new Exercise();
                loadedExercise.setId(resultSet.getInt("id"));
                loadedExercise.setTitle(resultSet.getString("title"));
                loadedExercise.setDescription(resultSet.getString("description"));
                return loadedExercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Exercise[] loadAll() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_EXERCISES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise loadedExercise = new Exercise();
                loadedExercise.setId(resultSet.getInt("id"));
                loadedExercise.setTitle(resultSet.getString("title"));
                loadedExercise.setDescription(resultSet.getString("description"));
                exercises.add(loadedExercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Exercise[] exerciseTable = new Exercise[exercises.size()];
        exerciseTable = exercises.toArray(exerciseTable);
        return exerciseTable;
    }

    public static void showAll() {
        Exercise[] exercises = ExerciseDao.loadAll();
        for (Exercise exercise : exercises) {
            System.out.println(exercise.getId() + " - " + exercise.getTitle() + " - " + exercise.getDescription());
        }
    }

    public static Exercise[] loadAllWithoutSolution(long id) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_EXERCISE_WITHOUT_SOLUTION);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise loadedExercise = new Exercise();
                loadedExercise.setId(resultSet.getInt("id"));
                loadedExercise.setTitle(resultSet.getString("title"));
                loadedExercise.setDescription(resultSet.getString("description"));
                exercises.add(loadedExercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Exercise[] exercisesTable = new Exercise[exercises.size()];
        exercisesTable = exercises.toArray(exercisesTable);
        return exercisesTable;
    }
}
