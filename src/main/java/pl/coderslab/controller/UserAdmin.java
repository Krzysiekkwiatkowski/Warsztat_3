package pl.coderslab.controller;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/school/panelAdmin/UserAdmin")
public class UserAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int userGroupId = Integer.parseInt(request.getParameter("userGroupId"));
        if(action.equals("add")){
            try {
                Connection connection = DbUtil.getConn();
                User user = new User(username, email, password, userGroupId);
                user.saveToDB(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else if(action.equals("edit")){
            try {
                long id = Long.parseLong(request.getParameter("id"));
                Connection connection = DbUtil.getConn();
                User user = User.loadById(connection, id);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserGroupId(userGroupId);
                user.saveToDB(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        response.sendRedirect("/school/panelAdmin/UserAdmin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if(action == null){
            try {
                Connection connection = DbUtil.getConn();
                User[] users = User.loadAll(connection);
                request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/UserAdmin.jsp")
                        .forward(request, response);
            } catch (SQLException e){
                e.printStackTrace();
            }

        } else if(action.equals("add")){
            response.getWriter().append("<form action=\"/school/panelAdmin/UserAdmin?action=add\" method=\"post\">\n" +
                    "    Username: <input type=\"text\" name=\"username\">\n" +
                    "    Email: <input type=\"text\" name=\"email\">\n" +
                    "    Password: <input type=\"text\" name=\"password\">\n" +
                    "    User's group id: <input type=\"number\" name=\"userGroupId\">\n" +
                    "    <input type=\"submit\" value=\"Add\">\n" +
                    "</form>");
        } else if(action.equals("edit")){
            try {
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(request.getParameter("id"));
                User user = User.loadById(connection, id);
                response.getWriter().append("<form action=\"/school/panelAdmin/UserAdmin?action=edit\" method=\"post\">\n" +
                        "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                        "   Username: <input type=\"text\" name=\"username\" value=\"" + user.getUsername() + "\">\n" +
                        "   Email: <input type=\"text\" name=\"email\" value=\"" + user.getEmail() + "\">\n" +
                        "   Password: <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\">\n" +
                        "   User's group id: <input type=\"number\" name=\"userGroupId\" value=\"" + user.getUserGroupId() + "\">\n" +
                        "    <input type=\"submit\" value=\"Edit\">\n" +
                        "</form>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(action.equals("delete")){
            try {
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(request.getParameter("id"));
                User user = User.loadById(connection, id);
                user.delete(connection);
                response.sendRedirect("/school/panelAdmin/UserAdmin");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
