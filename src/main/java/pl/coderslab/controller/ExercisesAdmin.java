package pl.coderslab.controller;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/school/panelAdmin/ExercisesAdmin")
public class ExercisesAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        if(action.equals("add")){
            try {
                Connection connection = DbUtil.getConn();
                Exercise exercise = new Exercise(title, description);
                exercise.saveToDB(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else if(action.equals("edit")){
            try {
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(request.getParameter("id"));
                Exercise exercise = Exercise.loadById(connection, id);
                exercise.setTitle(title);
                exercise.setDescription(description);
                exercise.saveToDB(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if(action == null) {
            try {
                Connection connection = DbUtil.getConn();
                Exercise[] exercises = Exercise.loadAll(connection);
                request.setAttribute("exercises", exercises);
                getServletContext().getRequestDispatcher("/ExercisesAdmin.jsp")
                        .forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(action.equals("add")){
            response.getWriter().append("<form action=\"/school/panelAdmin/ExercisesAdmin?action=add\" method=\"post\">\n" +
                    "    Title: <input type=\"text\" name=\"title\">\n" +
                    "    Description: <input type=\"text\" name=\"description\">\n" +
                    "    <input type=\"submit\" value=\"Add\">\n" +
                    "</form>");
        } else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Connection connection = DbUtil.getConn();
                Exercise exercise = Exercise.loadById(connection, id);
                String title = exercise.getTitle();
                String description = exercise.getDescription();
                response.getWriter().append("<form action=\"/school/panelAdmin/ExercisesAdmin?action=edit\" method=\"post\">\n" +
                        "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                        "   Title: <input type=\"text\" name=\"title\" value=\"" + title +"\">" +
                        "   Description: <input type=\"text\" name=\"description\" value=\"" + description + "\">\n" +
                        "    <input type=\"submit\" value=\"Edit\">\n" +
                        "</form>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(action.equals("delete")){
            try {
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(request.getParameter("id"));
                Exercise exercise = Exercise.loadById(connection, id);
                exercise.delete(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
