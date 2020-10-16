package javaee.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/setCookieTheme")
public class SetCookieThemeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theme = request.getParameter("theme");
        Cookie cookie = new Cookie("theme", theme);
        cookie.setMaxAge(3600 * 24 * 365);
        response.addCookie(cookie);

        String newsItem = request.getParameter("newsitem");
        if (newsItem != null && !newsItem.equals(""))
            response.sendRedirect("/details?newsitem=" + newsItem);
        else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
