package javaee.servlets;

import javaee.db.DBManager;
import javaee.db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");
        String pictureURL = request.getParameter("picture_url");
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String redirect = "/register?passworderror";
        if (password.equals(rePassword)) {
            redirect = "/register?emailerror";
            User user = DBManager.getUserByEmail(email);
            if (user == null) {
                User userNew = new User(0, email, password, fullName, pictureURL, birthdate);
                if (DBManager.addUser(userNew))
                redirect = "/register?success";
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
