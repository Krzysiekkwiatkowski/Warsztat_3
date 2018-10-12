package pl.coderslab.controller;

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
        if(idString != null){
            try {
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(idString);
                Group group = Group.loadById(connection, id);
                group.setName(name);
                group.saveToDB(connection);
                response.sendRedirect("/school/panelAdmin/UsersGroupsAdmin");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            try {
                Connection connection = DbUtil.getConn();
                Group group = new Group(name);
                group.saveToDB(connection);
                response.sendRedirect("/school/panelAdmin/UsersGroupsAdmin");
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
                Group[] groups = Group.loadAll(connection);
                request.setAttribute("groups", groups);
                getServletContext().getRequestDispatcher("/UsersGroupsAdmin.jsp")
                        .forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(action.equals("add")){
            response.getWriter().append("<form action=\"http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=add\" method=\"post\">\n" +
                    "    Name: <input type=\"text\" name=\"name\">\n" +
                    "    <input type=\"submit\" value=\"Add\">\n" +
                    "</form>");
        } else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Connection connection = DbUtil.getConn();
                String name = Group.loadById(connection, id).getName();
                response.getWriter().append("<form action=\"http://localhost:8080/school/panelAdmin/UsersGroupsAdmin?action=edit\" method=\"post\">\n" +
                        "    <input type=\"hidden\" value=\"" + id + "\" name=\"id\">" +
                        "    Name: <input type=\"text\" name=\"name\" value=\"" + name + "\">" +
                        "    <input type=\"submit\" value=\"Edit\">\n" +
                        "</form>");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else if(action.equals("delete")){
            try{
                Connection connection = DbUtil.getConn();
                int id = Integer.parseInt(request.getParameter("id"));
                Group group = Group.loadById(connection, id);
                group.delete(connection);
                response.sendRedirect("/school/panelAdmin/UsersGroupsAdmin");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
