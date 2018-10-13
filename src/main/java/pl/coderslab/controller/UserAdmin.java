package pl.coderslab.controller;

import pl.coderslab.dao.UserDao;
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
        if (action.equals("add")) {
            UserDao userDao = new UserDao();
            User user = new User(username, email, password, userGroupId);
            userDao.createUser(user);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(request.getParameter("id"));
            UserDao userDao = new UserDao();
            User user = UserDao.loadById(id);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserGroupId(userGroupId);
            userDao.updateUser(user);
        }
        response.sendRedirect("/school/panelAdmin/UserAdmin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if (action == null) {
            User[] users = UserDao.loadAll();
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/UserAdmin.jsp")
                    .forward(request, response);
        } else if (action.equals("add")) {
            response.getWriter().append("<form action=\"/school/panelAdmin/UserAdmin?action=add\" method=\"post\">\n" +
                    "    Username: <input type=\"text\" name=\"username\">\n" +
                    "    Email: <input type=\"text\" name=\"email\">\n" +
                    "    Password: <input type=\"text\" name=\"password\">\n" +
                    "    User's group id: <input type=\"number\" name=\"userGroupId\">\n" +
                    "    <input type=\"submit\" value=\"Add\">\n" +
                    "</form>");
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = UserDao.loadById(id);
            response.getWriter().append("<form action=\"/school/panelAdmin/UserAdmin?action=edit\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                    "   Username: <input type=\"text\" name=\"username\" value=\"" + user.getUsername() + "\">\n" +
                    "   Email: <input type=\"text\" name=\"email\" value=\"" + user.getEmail() + "\">\n" +
                    "   Password: <input type=\"text\" name=\"password\" value=\"" + user.getPassword() + "\">\n" +
                    "   User's group id: <input type=\"number\" name=\"userGroupId\" value=\"" + user.getUserGroupId() + "\">\n" +
                    "    <input type=\"submit\" value=\"Edit\">\n" +
                    "</form>");
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            UserDao userDao = new UserDao();
            userDao.delete(id);
            response.sendRedirect("/school/panelAdmin/UserAdmin");
        }

    }
}
