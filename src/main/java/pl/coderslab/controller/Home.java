package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school")
public class Home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int number = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        Solution[] solutions = SolutionDao.loadAll(number);
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }
}
