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

@WebServlet(value = "/editProfile")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");

        String email = request.getParameter("email");
        String oldPassword = request.getParameter("old_password");
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

        String redirect = "/editProfile?emailerror";
        User user = DBManager.getUserByEmail(email);
        boolean updated = false;
        if (user != null && currentUser.getEmail().equals(email)) {
            if (!email.equals("")) {
                assert birthdate != null;
                if (DBManager.saveUserInfo(currentUser.getId(), email, fullName, birthdate)) {
                    updated = true;
                    redirect = "/editProfile?success";
                }
            }
        }
        if (user == null) {
            if (email != null && !email.equals("")) {
                assert birthdate != null;
                if (DBManager.saveUserInfo(currentUser.getId(), email, fullName, birthdate)) {
                    updated = true;
                    redirect = "/editProfile?success";
                }
            }
            else if (pictureURL != null && !pictureURL.equals("")) {
                if (DBManager.saveUserPicture(currentUser.getId(), pictureURL)) {
                    updated = true;
                    redirect = "/editProfile?success";
                }
            }
            else if (password != null && !password.equals("")) {
                if (password.equals(rePassword) && oldPassword.equals(currentUser.getPassword())) {
                    if (DBManager.saveUserPassword(currentUser.getId(), password)) {
                        updated = true;
                        redirect = "/editProfile?success";
                    }
                }
                else redirect = "/editProfile?passworderror";
            }
        }
        if (updated)
            request.getSession().setAttribute("CURRENT_USER", DBManager.getUser(currentUser.getId()));
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser != null) {
            request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
