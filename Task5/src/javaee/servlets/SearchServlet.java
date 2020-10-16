package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            request.setAttribute("users", DBManager.getUsersByFullName(currentUser.getId(), request.getParameter("full_name")));
            request.setAttribute("friend_requests", DBManager.getFriendRequestByRequestSenderID(currentUser.getId()));
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
