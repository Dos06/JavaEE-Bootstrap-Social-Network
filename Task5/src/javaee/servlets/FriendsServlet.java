package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/friends")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            request.setAttribute("friends", DBManager.getFriendsByUserID(currentUser.getId()));
            request.setAttribute("friend_requests", DBManager.getFriendRequestByUserID(currentUser.getId()));
            request.getRequestDispatcher("/friends.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
