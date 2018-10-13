package pl.coderslab.dao;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Solution;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SolutionDao {

    private static String CREATE_SOLUTION = "INSERT INTO solution (created, description, exercise_id, users_id) VALUES (?,?,?,?)";
    private static String UPDATE_SOLUTION = "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, users_id = ? WHERE id = ?";
    private static String DELETE_SOLUTION = "DELETE FROM solution WHERE id = ?";
    private static String LOAD_SOLUTION_BY_ID = "SELECT * FROM solution WHERE id = ?";
    private static String LOAD_SOLUTION_BY_EXERCISE_AND_USER_ID = "SELECT * FROM solution WHERE exercise_id = ? AND users_id = ?";
    private static String LOAD_ALL_SOLUTIONS = "SELECT * FROM solution";
    private static String LOAD_LIMIT_SOLUTIONS = "SELECT * FROM solution ORDER BY id DESC LIMIT ?";
    private static String LOAD_ALL_SOLUTIONS_BY_USER_ID = "SELECT * FROM solution WHERE users_id = ?";
    private static String LOAD_ALL_SOLUTIONS_BY_EXERCISE_ID = "SELECT * FROM solution WHERE exercise_id = ?";

    public Solution createSolution(Solution solution) {
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SOLUTION, generatedColumns);
            preparedStatement.setDate(1, Date.valueOf(solution.getCreated().toLocalDate()));
            if (solution.getDescription() != null) {
                preparedStatement.setString(2, solution.getDescription());
            } else {
                preparedStatement.setNull(2, Types.VARCHAR);
            }
            if (solution.getExercise_id() != 0) {
                preparedStatement.setInt(3, solution.getExercise_id());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            if (solution.getUser_id() != 0) {
                preparedStatement.setLong(4, solution.getUser_id());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSolution(Solution solution) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SOLUTION);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            if (solution.getDescription() != null) {
                preparedStatement.setString(2, solution.getDescription());
            } else {
                preparedStatement.setNull(2, Types.VARCHAR);
            }
            if (solution.getExercise_id() != 0) {
                preparedStatement.setInt(3, solution.getExercise_id());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            if (solution.getUser_id() != 0) {
                preparedStatement.setLong(4, solution.getUser_id());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setInt(5, solution.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOLUTION);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Solution loadById(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_SOLUTION_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.setId(resultSet.getInt("id"));
                loadedSolution.setCreated(resultSet.getDate("created"));
                loadedSolution.setUpdated(resultSet.getDate("updated"));
                loadedSolution.setDescription(resultSet.getString("description"));
                if (resultSet.getInt("exercise_id") != Types.NULL) {
                    loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
                }
                if (resultSet.getLong("users_id") != Types.NULL) {
                    loadedSolution.setUser_id(resultSet.getLong("users_id"));
                }
                return loadedSolution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Solution[] loadAll() {
        ArrayList<Solution> solutions = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_SOLUTIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.setId(resultSet.getInt("id"));
                loadedSolution.setCreated(resultSet.getDate("created"));
                loadedSolution.setUpdated(resultSet.getDate("updated"));
                loadedSolution.setDescription(resultSet.getString("description"));
                if (resultSet.getInt("exercise_id") != 0) {
                    loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
                }
                if (resultSet.getInt("users_id") != 0) {
                    loadedSolution.setUser_id(resultSet.getLong("users_id"));
                }
                solutions.add(loadedSolution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] solutionTable = new Solution[solutions.size()];
        solutionTable = solutions.toArray(solutionTable);
        return solutionTable;
    }

    public static Solution[] loadAll(int number) {
        ArrayList<Solution> solutions = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_LIMIT_SOLUTIONS);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.setId(resultSet.getInt("id"));
                loadedSolution.setCreated(resultSet.getDate("created"));
                loadedSolution.setUpdated(resultSet.getDate("updated"));
                loadedSolution.setDescription(resultSet.getString("description"));
                if (resultSet.getInt("exercise_id") != 0) {
                    loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
                }
                if (resultSet.getInt("users_id") != 0) {
                    loadedSolution.setUser_id(resultSet.getLong("users_id"));
                }
                solutions.add(loadedSolution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] solutionTable = new Solution[solutions.size()];
        solutionTable = solutions.toArray(solutionTable);
        return solutionTable;
    }

    public static Solution[] loadAllByUserId(long id) {
        ArrayList<Solution> userSolutions = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_SOLUTIONS_BY_USER_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.setId(resultSet.getInt("id"));
                loadedSolution.setCreated(resultSet.getDate("created"));
                loadedSolution.setUpdated(resultSet.getDate("updated"));
                loadedSolution.setDescription(resultSet.getString("description"));
                loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
                loadedSolution.setUser_id(resultSet.getLong("users_id"));
                userSolutions.add(loadedSolution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] solutionTable = new Solution[userSolutions.size()];
        solutionTable = userSolutions.toArray(solutionTable);
        return solutionTable;
    }

    public static Solution[] loadAllByExerciseId(int id) {
        ArrayList<Solution> exerciseSolutions = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_SOLUTIONS_BY_EXERCISE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.setId(resultSet.getInt("id"));
                loadedSolution.setCreated(resultSet.getDate("created"));
                loadedSolution.setUpdated(resultSet.getDate("updated"));
                loadedSolution.setDescription(resultSet.getString("description"));
                loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
                loadedSolution.setUser_id(resultSet.getLong("users_id"));
                exerciseSolutions.add(loadedSolution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Solution[] solutionTable = new Solution[exerciseSolutions.size()];
        solutionTable = exerciseSolutions.toArray(solutionTable);
        return solutionTable;
    }

    public static void showAll() {
        Solution[] solutions = SolutionDao.loadAll();
        for (Solution solution : solutions) {
            System.out.println(solution.getId() + " - " + solution.getCreated() + " - " + solution.getUpdated() + " - " + solution.getDescription() + " - " + solution.getExercise_id() + " - " + solution.getUser_id());
        }
    }

    public static Solution loadByUserAndExerciseId(int exerciseId, long users_id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_SOLUTION_BY_EXERCISE_AND_USER_ID);
            preparedStatement.setInt(1, exerciseId);
            preparedStatement.setLong(2, users_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                if (resultSet.getDate("updated") != null) {
                    solution.setUpdated(resultSet.getDate("updated"));
                }
                solution.setExercise_id(exerciseId);
                solution.setUser_id(users_id);
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
