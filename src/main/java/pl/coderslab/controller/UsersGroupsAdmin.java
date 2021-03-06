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

@WebServlet("/school/panelAdmin/UsersGroupsAdmin")
public class UsersGroupsAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String name = request.getParameter("name");
        String idString = request.getParameter("id");
        if (idString != null) {
            int id = Integer.parseInt(idString);
            GroupDao groupDao = new GroupDao();
            Group group = GroupDao.loadById(id);
            group.setName(name);
            groupDao.updateGroup(group);
        } else {
            Group group = new Group(name);
            GroupDao groupDao = new GroupDao();
            groupDao.createGroup(group);
        }
        response.sendRedirect("/school/panelAdmin/UsersGroupsAdmin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Group[] groups = GroupDao.loadAll();
            request.setAttribute("groups", groups);
            getServletContext().getRequestDispatcher("/UsersGroupsAdmin.jsp")
                    .forward(request, response);
        } else if (action.equals("add")) {
            response.getWriter().append("<form action=\"http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=add\" method=\"post\">\n" +
                    "    Name: <input type=\"text\" name=\"name\">\n" +
                    "    <input type=\"submit\" value=\"Add\">\n" +
                    "</form>");
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = GroupDao.loadById(id).getName();
            response.getWriter().append("<form action=\"http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=edit\" method=\"post\">\n" +
                    "    <input type=\"hidden\" value=\"" + id + "\" name=\"id\">" +
                    "    Name: <input type=\"text\" name=\"name\" value=\"" + name + "\">" +
                    "    <input type=\"submit\" value=\"Edit\">\n" +
                    "</form>");
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            GroupDao groupDao = new GroupDao();
            groupDao.delete(id);
            response.sendRedirect("/school/panelAdmin/UsersGroupsAdmin");
        }
    }
}
