package javaee.servlets;

import javaee.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deletefriend")
public class DeleteFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestSenderId = Integer.parseInt(request.getParameter("request_sender_id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        if (DBManager.deleteFriend(requestSenderId, userId)) {
            response.sendRedirect("/friends?success");
        } else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
