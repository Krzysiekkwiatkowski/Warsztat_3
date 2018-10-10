package pl.coderslab.controller;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/")
public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        try {
            Connection connection = DbUtil.getConn();
            int number = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
            Solution[] solutions = Solution.loadAll(connection, number);
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
