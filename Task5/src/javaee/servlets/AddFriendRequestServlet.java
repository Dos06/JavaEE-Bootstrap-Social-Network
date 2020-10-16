package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.Friend;
import javaee.db.FriendRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/addfriendrequest")
public class AddFriendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestSenderId = Integer.parseInt(request.getParameter("request_sender_id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        FriendRequest friendRequest = new FriendRequest(0, new Timestamp(System.currentTimeMillis()), DBManager.getUser(userId), DBManager.getUser(requestSenderId));
        if (DBManager.addFriendRequest(friendRequest)) {
            response.sendRedirect("/friends");
        } else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
