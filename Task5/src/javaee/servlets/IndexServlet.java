package javaee.servlets;

import javaee.db.Chat;
import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Cookie[] cookies = request.getCookies();
        String languageCode = "eng", theme = "theme-standard";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("language")) {
                    languageCode = cookie.getValue();
//                    break;
                } else if (cookie.getName().equals("theme")) {
                    theme = cookie.getValue();
                }
            }
        }
        request.setAttribute("theme", theme);
        request.setAttribute("language", languageCode);

        request.setAttribute("posts", DBManager.getPosts());

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
//        boolean ONLINE = false;
        if (currentUser != null) {
//            ONLINE = true;
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
