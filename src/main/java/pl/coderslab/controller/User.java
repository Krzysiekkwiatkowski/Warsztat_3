package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/school/User")
public class User extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        pl.coderslab.model.User user = pl.coderslab.dao.UserDao.loadById(id);
        Solution[] solutions = SolutionDao.loadAllByUserId(id);
        request.setAttribute("user", user);
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/User.jsp")
                .forward(request, response);
    }
}
