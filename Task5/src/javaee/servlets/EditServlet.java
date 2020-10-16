package javaee.servlets;

import javaee.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                if (request.getParameter("post") != null) {
                    try {
                        Post post = getPostFromFields(request, response);
                        if (DBManager.addPost(post))
                            response.sendRedirect("/?success");
                        else
                            response.sendRedirect("/?fail");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                int authorId = Integer.parseInt(request.getParameter("author_id"));
                if (request.getParameter("post") != null) {
                    if (DBManager.deletePost(id, authorId))
                        response.sendRedirect("/?success");
                    else
                        response.sendRedirect("/?post_id=" + id + "&fail");
                }
                break;
            }
            case "save": {
                int id = Integer.parseInt(request.getParameter("id"));
                if (request.getParameter("post") != null) {
                    Post post = DBManager.getPost(id);
                    if (post != null){
                        try {
                            post = getPostFromFields(request, response);
                            post.setId(id);
                            if (DBManager.savePost(post)) {
                                response.sendRedirect("/details?post_id=" + id + "&success");
                            }
                            else
                                response.sendRedirect("/details?post_id=" + id + "&fail");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
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

        ArrayList<Language> languages = DBManager.getLanguages();
        ArrayList<Publication> publications = DBManager.getPublications();
        ArrayList<NewsItem> newsItems = DBManager.getNewsItems();
        request.setAttribute("languages", languages);
        request.setAttribute("publications", publications);
        request.setAttribute("newsitems", newsItems);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private Language getLanguageFromFields(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String name = request.getParameter("language_name");
        String code = request.getParameter("language_code");
        return new Language(0, name, code);
    }

    private Language getLanguageIDFromFields(int id, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String name = request.getParameter("language_name" + id);
        String code = request.getParameter("language_code" + id);
        return new Language(0, name, code);
    }

    private Publication getPublicationFromFields(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String name = request.getParameter("publication_name");
        String description = request.getParameter("publication_description");
        double rating = Double.parseDouble(request.getParameter("publication_rating"));
        return new Publication(0, name, description, rating);
    }

    private Publication getPublicationIDFromFields(int id, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String name = request.getParameter("publication_name" + id);
        String description = request.getParameter("publication_description" + id);
        double rating = Double.parseDouble(request.getParameter("publication_rating" + id));
        return new Publication(0, name, description, rating);
    }

    private NewsItem getNewsItemFromFields(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String title = request.getParameter("newsitem_title");
        String shortContent = request.getParameter("newsitem_shortcontent");
        String content = request.getParameter("newsitem_content");
        String pictureURL = request.getParameter("newsitem_pictureurl");
//        String pd = request.getParameter("newsitem_postdate");

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp postDate = new Timestamp(now.getTime());
//        Timestamp postDate = Timestamp.from(Instant.parse(request.getParameter("newsitem_postdate")));

        int languageId = Integer.parseInt(request.getParameter("language_id"));
        int publicationId = Integer.parseInt(request.getParameter("publication_id"));
        return new NewsItem(0, title, shortContent, content, pictureURL, postDate, DBManager.getLanguage(languageId), DBManager.getPublication(publicationId));
    }

    private NewsItem getNewsItemIDFromFields(int id, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String title = request.getParameter("newsitem_title" + id);
        String shortContent = request.getParameter("newsitem_shortcontent" + id);
        String content = request.getParameter("newsitem_content" + id);
        String pictureURL = request.getParameter("newsitem_pictureurl" + id);
//        Timestamp postDate = new Timestamp(1);
        int languageId = Integer.parseInt(request.getParameter("language_id" + id));
        int publicationId = Integer.parseInt(request.getParameter("publication_id" + id));
        return new NewsItem(0, title, shortContent, content, pictureURL, null, DBManager.getLanguage(languageId), DBManager.getPublication(publicationId));
    }

    private Post getPostFromFields(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String title = request.getParameter("post_title");
        String shortContent = request.getParameter("post_shortcontent");
        String content = request.getParameter("post_content");

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp postDate = new Timestamp(now.getTime());
//        Timestamp postDate = Timestamp.from(Instant.parse(request.getParameter("post_postdate")));

        int authorId = Integer.parseInt(request.getParameter("author_id"));
        return new Post(0, title, shortContent, content, postDate, DBManager.getUser(authorId));
    }

    private Post getPostIDFromFields(int id, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String title = request.getParameter("post_title" + id);
        String shortContent = request.getParameter("post_shortcontent" + id);
        String content = request.getParameter("post_content" + id);
//        Timestamp postDate = new Timestamp(1);
        int authorId = Integer.parseInt(request.getParameter("author_id" + id));
        return new Post(0, title, shortContent, content, null, DBManager.getUser(authorId));
    }
}
