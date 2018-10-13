package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/school/Groups")
public class Groups extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        Group[] groups = GroupDao.loadAll();
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/Groups.jsp")
                .forward(request, response);
    }
}
