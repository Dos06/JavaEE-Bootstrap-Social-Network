package javaee.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/session")
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionValue = request.getParameter("session_value");
        HttpSession session = request.getSession();
        session.setAttribute("session_value", sessionValue);
        response.sendRedirect("/session");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionValue = (String) session.getAttribute("session_value");
        System.out.println(sessionValue);
        request.setAttribute("text", "Your session value is " + sessionValue);
        request.getRequestDispatcher("/session.jsp").forward(request, response);
    }
}

//String text = (String) request.getAttribute("text");