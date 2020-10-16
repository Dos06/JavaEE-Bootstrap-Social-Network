<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container pb-4">
        <%
            if (ONLINE) {
        %>
        <div class="col-sm-12 row mt-4">
            <div class="col-sm-3">
                <div class="row">
                    <img src="<%=currentUser.getPictureURL()%>" class="container-fluid" style="padding-left: 0px;">
                </div>
                <div class="row mt-4">
                    <ul class="list-group col-sm-12">
                        <li class="list-group-item"><h4 class="mt-4"><%=currentUser.getFullName() + "<br>" + "Age " + currentUser.getAge()%></h4></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/editProfile"><b>My Profile</b></a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/settings"><b>Settings</b></a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout" style="color: crimson"><b>Log Out</b></a></li>
                    </ul>
                </div>
            </div>



<%--            TODO: MAIN BLOCK NEWS--%>
            <div class="col-sm-6">
                <div class="row">
                    <button type="button" class="btn btn-primary btn-lg" style="background-color: #031c6e;" data-toggle="modal" data-target="#add_post">+ ADD NEW </button>


                    <div class="modal fade" id="add_post" tabindex="-1" aria-labelledby="AddPostModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="AddPostModalLabel">Add a Post</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/edit?action=add" method="post">
                                        <input type="hidden" name="post" value="">
                                        <input type="hidden" name="author_id" value="<%=currentUser.getId()%>">
                                        <div class="form-group">
                                            <label>Post Title : </label>
                                            <input type="text" name="post_title" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Short Content : </label>
                                            <textarea name="post_shortcontent" class="form-control" rows="5" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Content : </label>
                                            <textarea name="post_content" class="form-control" rows="15" required></textarea>
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                        <button type="submit" class="btn btn-success">ADD</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



                <%
                    String success = request.getParameter("success");
                    if (success != null) {
                %>
                <div class="row mt-4 alert alert-success alert-dismissible fade show" role="alert">
                    Success!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>



                <%
                    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
                    if (posts != null) {
                        for (Post post : posts) {
                %>
                <div class="row mt-4">
                    <div class="card w-100">
                        <div class="card-body">
                            <h5 class="card-title"><%=post.getTitle()%></h5>
                            <p class="card-text"><%=post.getShortContent()%></p>
                            <a href="/details?post_id=<%=post.getId()%>" class="btn btn-outline-primary">Read More</a>
                        </div>
                        <div class="card-footer text-muted">
                            Posted on <%=post.getPostDate().getDate() + "." + (post.getPostDate().getMonth()+1) + "." + (post.getPostDate().getYear() + 1900)%> by
                            <a href="/profile?id=<%=post.getUser().getId()%>"><b style="color: #031c6e"><%=post.getUser().getFullName()%></b></a>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>


            <%@include file="assets/sidebar-col-3.jsp"%>

        </div>
        <%
            }
        %>
    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>
