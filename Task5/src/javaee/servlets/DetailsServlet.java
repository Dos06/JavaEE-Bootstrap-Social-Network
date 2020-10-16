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

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Cookie[] cookies = request.getCookies();
        String theme = "theme-standard";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("theme")) {
                    theme = cookie.getValue();
                    break;
                }
            }
        }
        request.setAttribute("theme", theme);

        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        String postId = request.getParameter("post_id");
        if (currentUser != null && postId != null) {
            request.setAttribute("post", DBManager.getPost(Integer.parseInt(postId)));
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }
        else response.sendRedirect("/");
    }
}
