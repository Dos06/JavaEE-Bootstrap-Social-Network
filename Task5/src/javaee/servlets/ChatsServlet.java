package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/chats")
public class ChatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        String chatId = request.getParameter("id");
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            if (chatId == null || chatId.equals("")) {
                request.setAttribute("chats", DBManager.getChatsByUserID(currentUser.getId()));
                request.getRequestDispatcher("/chats.jsp").forward(request, response);
            } else {
                request.setAttribute("chat", DBManager.getChat(Integer.parseInt(chatId)));
                request.setAttribute("messages", DBManager.getMessagesByChatId(Integer.parseInt(chatId)));
                request.getRequestDispatcher("/messages.jsp?id=" + chatId).forward(request, response);
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
