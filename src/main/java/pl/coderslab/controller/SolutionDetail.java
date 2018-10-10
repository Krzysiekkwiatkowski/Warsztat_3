package pl.coderslab.controller;

import pl.coderslab.model.DbUtil;
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

@WebServlet("/school/SolutionDetail")
public class SolutionDetail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection connection = DbUtil.getConn();
            Solution solution = Solution.loadById(connection, id);
            request.setAttribute("solution", solution);
            getServletContext().getRequestDispatcher("/SolutionDetail.jsp")
                    .forward(request, response);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
