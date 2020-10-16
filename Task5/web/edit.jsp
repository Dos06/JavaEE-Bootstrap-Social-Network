<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body  class="<%=theme%>">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">WORLD NEWS PORTAL</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="btn btn-danger btn-md ml-auto" href="${pageContext.request.contextPath}/">Sign Out</a>
    </nav>

    <div class="col-sm-12 d-flex h-100">
        <div class="col-sm-2 d-inline-block" style="background-color: gainsboro">
            <h2 class="mt-4">ADMIN PANEL</h2>
            <ul class="list-group mt-4">
                <li class="list-group-item" style="background-color: gainsboro"><a href="${pageContext.request.contextPath}/edit?languages" style="color: black;"><b>Languages</b></a></li>
                <li class="list-group-item" style="background-color: gainsboro"><a href="${pageContext.request.contextPath}/edit?publications" style="color: black;"><b>Publications</b></a></li>
                <li class="list-group-item" style="background-color: gainsboro"><a href="${pageContext.request.contextPath}/edit?news" style="color: black;"><b>News</b></a></li>
            </ul>
        </div>
        <div class="col-sm-10 d-inline-block">

            <%
                String success = request.getParameter("success");
                String fail = request.getParameter("fail");
                if (success != null) {
            %>
                    <div class="alert alert-success alert-dismissible fade show mt-5" role="alert">
                        Successfully!
                        <button type="button" class="close" data-dismiss="alert" aria-label="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
            <%
                }
                else if (fail != null) {
            %>
                    <div class="alert alert-danger alert-dismissible fade show mt-5" role="alert">
                        Something went wrong!
                        <button type="button" class="close" data-dismiss="alert" aria-label="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
            <%
                }
            %>





            <%
                if (request.getParameter("languages") != null) {
            %>
                    <div class="row col-sm-12 justify-content-between mt-5">
                        <h4>List of all <span style="color: crimson">Languages</span></h4>
                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#add_language">+ ADD NEW </button>
                    </div>

                    <div class="modal fade" id="add_language" tabindex="-1" aria-labelledby="AddLanguageModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="AddLanguageModalLabel">Add a Language</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/edit?action=add" method="post">
                                        <input type="hidden" name="language" value="">
                                        <div class="form-group">
                                            <label>Language Name : </label>
                                            <input type="text" name="language_name" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Language Code : </label>
                                            <input type="text" name="language_code" class="form-control" required>
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                        <button type="submit" class="btn btn-success">ADD</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <hr class="my-4">

                    <div class="row mt-5 mb-5">
                        <div class="col-sm-12">
                            <table class="table" style="background-color: aliceblue">
                                <thead class="font-weight-bold" style="background-color: lightblue">
                                <tr>
                                    <td>ID</td>
                                    <td>LANGUAGE NAME</td>
                                    <td>CODE</td>
                                    <td>OPERATIONS</td>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    ArrayList<Language> languages = (ArrayList<Language>) request.getAttribute("languages");
                                    if (languages != null) {
                                        for (Language language : languages) {
                                %>
                                        <tr>
                                            <td><%=language.getId()%></td>
                                            <td><%=language.getName()%></td>
                                            <td><%=language.getCode()%></td>
                                            <td>
                                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delete_language<%=language.getId()%>">DELETE</button>
                                                <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#edit_language<%=language.getId()%>">EDIT</button>
<%--                                                <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/edit?city&league_id=<%=language.getId()%>">DETAILS</a>--%>
                                            </td>
                                        </tr>

                                        <div class="modal fade" id="delete_language<%=language.getId()%>" tabindex="-1" aria-labelledby="DeleteLanguageModalLabel<%=language.getId()%>" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="DeleteLanguageModalLabel<%=language.getId()%>">Confirm to Delete <%=language.getName()%></h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form action="${pageContext.request.contextPath}/edit?action=delete" method="post">
                                                            <input type="hidden" name="language" value="">
                                                            <input type="hidden" name="id" value="<%=language.getId()%>">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                            <button type="submit" class="btn btn-danger">DELETE</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal fade" id="edit_language<%=language.getId()%>" tabindex="-1" aria-labelledby="EditLanguageModalLabel<%=language.getId()%>" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="EditLanguageModalLabel<%=language.getId()%>">Change the Values <%=language.getName()%></h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/edit?action=save" method="post">
                                                            <input type="hidden" name="language" value="">
                                                            <input type="hidden" name="id" value="<%=language.getId()%>">
                                                            <div class="form-group">
                                                                <label>Language Name : </label>
                                                                <input type="text" name="language_name<%=language.getId()%>" class="form-control" value="<%=language.getName()%>" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Language Code : </label>
                                                                <input type="text" name="language_code<%=language.getId()%>" class="form-control" value="<%=language.getCode()%>" required>
                                                            </div>
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                            <button type="submit" class="btn btn-success">SAVE</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>

            <%
                }
                else if (request.getParameter("publications") != null) {
            %>
                    <div class="row col-sm-12 justify-content-between mt-5">
                        <h4>List of all <span style="color: crimson">Publications</span></h4>
                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#add_publication">+ ADD NEW </button>
                    </div>

                    <div class="modal fade" id="add_publication" tabindex="-1" aria-labelledby="AddPublicationModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="AddPublicationModalLabel">Add a Publication</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/edit?action=add" method="post">
                                        <input type="hidden" name="publication" value="">
                                        <div class="form-group">
                                            <label>Publication Name : </label>
                                            <input type="text" name="publication_name" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Publication Rating : </label>
                                            <input type="number" step="0.1" name="publication_rating" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Publication Description : </label>
                                            <textarea name="publication_description" class="form-control" rows="15" required></textarea>
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                        <button type="submit" class="btn btn-success">ADD</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <hr class="my-4">

                    <div class="row mt-5 mb-5">
                        <div class="col-sm-12">
                            <table class="table" style="background-color: aliceblue">
                                <thead class="font-weight-bold" style="background-color: lightblue">
                                    <tr>
                                        <td>ID</td>
                                        <td>NAME</td>
                                        <td>DESCRIPTION</td>
                                        <td>RATING</td>
                                        <td>OPERATIONS</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                    ArrayList<Publication> publications = (ArrayList<Publication>) request.getAttribute("publications");
                                    if (publications != null) {
                                        for (Publication publication : publications) {
                                %>
                                    <tr>
                                        <td><%=publication.getId()%></td>
                                        <td><%=publication.getName()%></td>
                                        <td><%=publication.getDescription()%></td>
                                        <td><%=publication.getRating()%></td>
                                        <td class="row">
                                            <button type="button" class="btn btn-danger btn-sm mr-1" data-toggle="modal" data-target="#delete_publication<%=publication.getId()%>">DELETE</button>
                                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#edit_publication<%=publication.getId()%>">EDIT</button>
                                        </td>
                                    </tr>

                                <div class="modal fade" id="delete_publication<%=publication.getId()%>" tabindex="-1" aria-labelledby="DeletePublicationModalLabel<%=publication.getId()%>" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="DeletePublicationModalLabel<%=publication.getId()%>">Confirm to Delete <%=publication.getName()%></h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="${pageContext.request.contextPath}/edit?action=delete" method="post">
                                                    <input type="hidden" name="publication" value="">
                                                    <input type="hidden" name="id" value="<%=publication.getId()%>">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                    <button type="submit" class="btn btn-danger">DELETE</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal fade" id="edit_publication<%=publication.getId()%>" tabindex="-1" aria-labelledby="EditPublicationModalLabel<%=publication.getId()%>" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="EditPublicationModalLabel<%=publication.getId()%>">Change the Values <%=publication.getName()%></h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.request.contextPath}/edit?action=save" method="post">
                                                    <input type="hidden" name="publication" value="">
                                                    <input type="hidden" name="id" value="<%=publication.getId()%>">
                                                    <div class="form-group">
                                                        <label>Publication Name : </label>
                                                        <input type="text" name="publication_name<%=publication.getId()%>" class="form-control" value="<%=publication.getName()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Publication Rating : </label>
                                                        <input type="number" step="0.1" name="publication_rating<%=publication.getId()%>" class="form-control" value="<%=publication.getRating()%>" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Publication Description : </label>
                                                        <textarea name="publication_description<%=publication.getId()%>" class="form-control" rows="15" required><%=publication.getDescription()%></textarea>
                                                    </div>
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                    <button type="submit" class="btn btn-success">SAVE</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
            <%
                }
                else if (request.getParameter("news") != null) {
            %>
            <div class="row col-sm-12 justify-content-between mt-5">
                <h4>List of all <span style="color: crimson">News</span></h4>
                <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#add_news">+ ADD NEW </button>
            </div>

            <div class="modal fade" id="add_news" tabindex="-1" aria-labelledby="AddNewsItemModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="AddNewsItemModalLabel">Add a News Item</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/edit?action=add" method="post">
                                <input type="hidden" name="newsitem" value="">
                                <div class="form-group">
                                    <label>Title : </label>
                                    <input type="text" name="newsitem_title" class="form-control" required>
                                </div>
<%--                                <div class="form-group">--%>
<%--                                    <label>Post Date : </label>--%>
<%--                                    <input type="datetime-local" name="newsitem_postdate" class="form-control" required>--%>
<%--                                </div>--%>
                                <div class="form-group">
                                    <label>Language : </label>
                                    <select class="form-control" name="language_id" required>
                                        <option disabled selected>Choose</option>
                                        <%
                                            ArrayList<Language> languages = (ArrayList<Language>) request.getAttribute("languages");
                                            if (languages != null) {
                                                for (Language language : languages) {
                                        %>
                                        <option value="<%=language.getId()%>"><%=language.getName()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Publication : </label>
                                    <select class="form-control" name="publication_id" required>
                                        <option disabled selected>Choose</option>
                                        <%
                                            ArrayList<Publication> publications = (ArrayList<Publication>) request.getAttribute("publications");
                                            if (publications != null) {
                                                for (Publication publication : publications) {
                                        %>
                                        <option value="<%=publication.getId()%>"><%=publication.getName()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Picture URL : </label>
                                    <input type="text" name="newsitem_pictureurl" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Short Content : </label>
                                    <textarea name="newsitem_shortcontent" class="form-control" rows="5" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Content : </label>
                                    <textarea name="newsitem_content" class="form-control" rows="15" required></textarea>
                                </div>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                <button type="submit" class="btn btn-success">ADD</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <hr class="my-4">

            <div class="row mt-5 mb-5">
                <div class="col-sm-12">
                    <table class="table" style="background-color: aliceblue">
                        <thead class="font-weight-bold" style="background-color: lightblue">
                            <tr>
                                <td>ID</td>
                                <td>TITLE</td>
                                <td>POST DATE</td>
                                <td>LANGUAGE</td>
                                <td>PUBLICATION</td>
                                <td>OPERATIONS</td>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<NewsItem> newsItems = (ArrayList<NewsItem>) request.getAttribute("newsitems");
                            if (newsItems != null) {
                                for (NewsItem newsItem : newsItems) {
                        %>
                        <tr>
                            <td><%=newsItem.getId()%></td>
                            <td><%=newsItem.getTitle()%></td>
                            <td><%=newsItem.getPostDate()%></td>
                            <td><%=newsItem.getLanguage().getName()%></td>
                            <td><%=newsItem.getPublication().getName()%></td>
                            <td class="row">
                                <button type="button" class="btn btn-danger btn-sm mr-1" data-toggle="modal" data-target="#delete_news<%=newsItem.getId()%>">DELETE</button>
                                <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#edit_news<%=newsItem.getId()%>">EDIT</button>
                            </td>
                        </tr>

                        <div class="modal fade" id="delete_news<%=newsItem.getId()%>" tabindex="-1" aria-labelledby="DeleteNewsItemModalLabel<%=newsItem.getId()%>" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="DeleteNewsItemModalLabel<%=newsItem.getId()%>">Confirm to Delete <%=newsItem.getTitle()%></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="${pageContext.request.contextPath}/edit?action=delete" method="post">
                                            <input type="hidden" name="newsitem" value="">
                                            <input type="hidden" name="id" value="<%=newsItem.getId()%>">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                            <button type="submit" class="btn btn-danger">DELETE</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="edit_news<%=newsItem.getId()%>" tabindex="-1" aria-labelledby="EditNewsItemModalLabel<%=newsItem.getId()%>" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="EditNewsItemModalLabel<%=newsItem.getId()%>">Change the Values <%=newsItem.getTitle()%></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/edit?action=save" method="post">
                                            <input type="hidden" name="newsitem" value="">
<%--                                            <input type="hidden" name="language_id<%=newsItem.getId()%>" value="<%=newsItem.getLanguage().getId()%>">--%>
<%--                                            <input type="hidden" name="publication_id<%=newsItem.getId()%>" value="<%=newsItem.getPublication().getId()%>">--%>
                                            <input type="hidden" name="id" value="<%=newsItem.getId()%>">
                                            <div class="form-group">
                                                <label>Title : </label>
                                                <input type="text" name="newsitem_title<%=newsItem.getId()%>" class="form-control" required value="<%=newsItem.getTitle()%>">
                                            </div>
                                            <div class="form-group">
                                                <label>Post Date : <%=newsItem.getPostDate()%></label>
<%--                                                <input type="datetime-local" name="newsitem_postdate<%=newsItem.getId()%>" class="form-control" required value="<%=newsItem.getPostDate()%>">--%>
                                            </div>
                                            <div class="form-group">
                                                <label>Language : </label>
                                                <select class="form-control" name="language_id<%=newsItem.getId()%>" required>
                                                    <%
                                                        if (languages != null) {
                                                            for (Language language : languages) {
                                                    %>
                                                    <option value="<%=language.getId()%>" <%=(language.getId() == newsItem.getLanguage().getId() ? "selected" : "") %> ><%=language.getName()%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Publication : </label>
                                                <select class="form-control" name="publication_id<%=newsItem.getId()%>" required>
                                                    <%
                                                        if (publications != null) {
                                                            for (Publication publication : publications) {
                                                    %>
                                                    <option value="<%=publication.getId()%>" <%=(publication.getId() == newsItem.getPublication().getId() ? "selected" : "")%>><%=publication.getName()%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Picture URL : </label>
                                                <input type="text" name="newsitem_pictureurl<%=newsItem.getId()%>" class="form-control" required value="<%=newsItem.getPictureURL()%>">
                                            </div>
                                            <div class="form-group">
                                                <label>Short Content : </label>
                                                <textarea name="newsitem_shortcontent<%=newsItem.getId()%>" class="form-control" rows="5" required><%=newsItem.getShortContent()%></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label>Content : </label>
                                                <textarea name="newsitem_content<%=newsItem.getId()%>" class="form-control" rows="15" required><%=newsItem.getContent()%></textarea>
                                            </div>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                            <button type="submit" class="btn btn-success">SAVE</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
            <%
            }
            %>
        </div>
    </div>

</body>
</html>
