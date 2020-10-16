package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        String userID = request.getParameter("id");
        if (currentUser != null) {
            if (userID != null) {
                User user = DBManager.getUser(Integer.parseInt(userID));
                request.setAttribute("user", user);
                request.setAttribute("isfriend", DBManager.areFriends(currentUser.getId(), user.getId()));
                request.setAttribute("posts", DBManager.getPostsByUserID(user.getId()));
            }
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
