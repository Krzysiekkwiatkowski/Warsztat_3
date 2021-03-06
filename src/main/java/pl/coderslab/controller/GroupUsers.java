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

@WebServlet("/school/GroupUsers")
public class GroupUsers extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection connection = DbUtil.getConn();
            User[] users = UserDao.loadAllByGroupId(id);
            request.setAttribute("id", id);
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/GroupUsers.jsp")
                    .forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
