package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school/SolutionDetail")
public class SolutionDetail extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
            Solution solution = SolutionDao.loadById(id);
            request.setAttribute("solution", solution);
            getServletContext().getRequestDispatcher("/SolutionDetail.jsp")
                    .forward(request, response);
    }
}
