package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.FriendRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/deletefriendrequest")
public class DeleteFriendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestSenderId = Integer.parseInt(request.getParameter("request_sender_id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        if (DBManager.deleteFriendRequest(requestSenderId, userId)) {
            response.sendRedirect("/friends");
        } else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
