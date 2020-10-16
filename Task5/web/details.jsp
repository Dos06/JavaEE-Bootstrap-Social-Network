<%@ page import="javaee.db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Details</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="col-sm-10 row mx-auto">

        <%
            Post post = (Post) request.getAttribute("post");
            String success = request.getParameter("success");
            String fail = request.getParameter("fail");

            if (post != null) {
                if (success != null) {
        %>
        <div class="container col-sm-8 offset-2">
            <div class="row mt-4 alert alert-success alert-dismissible fade show" role="alert">
                Success!
                <button type="button" class="close" data-dismiss="alert" aria-label="close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <%
                }
                else if (fail != null) {
        %>
        <div class="container col-sm-8 offset-2">
            <div class="row mt-4 alert alert-danger alert-dismissible fade show" role="alert">
                Something went wrong!
                <button type="button" class="close" data-dismiss="alert" aria-label="close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <%
            }
        %>
        <div class="col-sm-8 mt-4 mb-5 offset-2">
            <div class="card-deck mb-5">
                <div class="card news-item <%=theme%>">
                    <h1 class="newsitem title <%=theme%> mt-4 mb-4 ml-4"><%=post.getTitle()%></h1>
                    <h3 class="newsitem title <%=theme%> mb-4 ml-4">On <%=post.getPostDate()%> by <span style="color: steelblue"><%=post.getUser().getFullName()%></span></h3>
<%--                    <img class="card-img-top mx-auto <%=theme%>" src="<%=post.getPictureURL()%>" alt="Card image cap" style="max-width: 95%">--%>
                    <div class="card-body <%=theme%>">
                        <h4 class="mb-4"><%=post.getShortContent()%></h4>
                        <p class="mt-4 mb-4" style="font-size: 18px;"><%=post.getContent()%></p>

                        <%
                            if (post.getUser().getId() == currentUser.getId()) {
                        %>
                        <div class="row ml-1">
                            <button type="button" class="btn btn-danger btn-lg mr-4" data-toggle="modal" data-target="#delete_post">DELETE </button>
                            <button type="button" class="btn btn-warning btn-lg" data-toggle="modal" data-target="#edit_post">EDIT </button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>



        <div class="modal fade" id="delete_post" tabindex="-1" aria-labelledby="DeletePostModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="DeletePostModalLabel">Confirm to Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-footer">
                        <form action="${pageContext.request.contextPath}/edit?action=delete" method="post">
                            <input type="hidden" name="post" value="">
                            <input type="hidden" name="id" value="<%=post.getId()%>">
                            <input type="hidden" name="author_id" value="<%=post.getUser().getId()%>">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                            <button type="submit" class="btn btn-danger">DELETE</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="edit_post" tabindex="-1" aria-labelledby="EditPostModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="EditPostModalLabel">Edit a Post</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/edit?action=save" method="post">
                            <input type="hidden" name="post" value="">
                            <input type="hidden" name="id" value="<%=post.getId()%>">
                            <input type="hidden" name="author_id" value="<%=currentUser.getId()%>">
                            <div class="form-group">
                                <label>Post Title : </label>
                                <input type="text" name="post_title" class="form-control" required value="<%=post.getTitle()%>">
                            </div>
                            <div class="form-group">
                                <label>Short Content : </label>
                                <textarea name="post_shortcontent" class="form-control" rows="5" required><%=post.getShortContent()%></textarea>
                            </div>
                            <div class="form-group">
                                <label>Content : </label>
                                <textarea name="post_content" class="form-control" rows="15" required><%=post.getContent()%></textarea>
                            </div>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                            <button type="submit" class="btn btn-success">SAVE</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>



<%--        <div class="col-sm-4">--%>
<%--            <div class="row jumbotron mt-5 mb-5 news-item-publication <%=theme%>">--%>
<%--                <h1 class="newsitem-publication <%=theme%> h1 font-italic"><%=newsItem.getPublication().getName()%></h1>--%>
<%--                <p class="newsitem-publication <%=theme%> lead"><%=newsItem.getPublication().getDescription()%></p>--%>
<%--                <hr class="my-4">--%>
<%--                <p class="h4">Rating: <%=newsItem.getPublication().getRating()%> / 10</p>--%>
<%--            </div>--%>
<%--        </div>--%>


            <%

            } else {
            %>
                <h1 class="display-4 text-center">404 NOT FOUND</h1>
            <%
            }
            %>
    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>

