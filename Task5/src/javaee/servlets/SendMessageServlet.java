package javaee.servlets;

import javaee.db.Chat;
import javaee.db.DBManager;
import javaee.db.Friend;
import javaee.db.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/sendmessage")
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int chatId = Integer.parseInt(request.getParameter("chat_id"));
        int requestSenderId = Integer.parseInt(request.getParameter("request_sender_id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String messageText = request.getParameter("message_text");

        Chat chat = DBManager.getChatByUserIdOpponentId(userId, requestSenderId);
        if (chat != null && chatId != 0) {
            chat = DBManager.getChat(chatId);
        }
        else if (chat == null && chatId == 0) {
            chat = new Chat(0, new Timestamp(System.currentTimeMillis()), messageText, new Timestamp(System.currentTimeMillis()), DBManager.getUser(userId), DBManager.getUser(requestSenderId));
            if (DBManager.addChat(chat)) {
                chat = DBManager.getChatByUserIdOpponentId(userId, requestSenderId);
            }
        }

        Message message = new Message(0, messageText, false, new Timestamp(System.currentTimeMillis()), chat, DBManager.getUser(userId), DBManager.getUser(requestSenderId));


        chat.setLatestMessageText(messageText);
        chat.setLatestMessageTime(message.getSentTime());
        if (DBManager.addMessage(message) && DBManager.saveChat(chat)) {
            response.sendRedirect("/chats?id=" + chat.getId());
        } else
            response.sendRedirect("/chats");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
