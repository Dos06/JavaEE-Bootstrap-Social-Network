<%@ page import="javaee.db.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>
<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container pb-4">
        <%
            User user = (User) request.getAttribute("user");
            if (ONLINE && user != null) {
        %>
        <div class="col-sm-12 row mt-4">
            <div class="col-sm-3">
                <div class="row">
                    <img src="<%=user.getPictureURL()%>" class="container-fluid" style="padding-left: 0px;">
                </div>
                <div class="row mt-4">
                    <ul class="list-group col-sm-12">
                        <li class="list-group-item"><h4 class="mt-4"><%=user.getFullName() + "<br>" + "Age " + user.getAge()%></h4></li>
                        <%
                            boolean isFriend = (boolean) request.getAttribute("isfriend");
                            if (isFriend) {
                        %>
                        <li class="list-group-item">

                            <button type="button" class="btn btn-link p-0" data-toggle="modal" data-target="#delete_friend"><b>Remove from friends</b></button>

                            <div class="modal fade" id="delete_friend" tabindex="-1" aria-labelledby="DeleteFriendModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="DeleteFriendModalLabel">Are you sure to delete <%=user.getFullName()%>?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-footer">
                                            <form action="/deletefriend" method="post">
                                                <input type="hidden" name="request_sender_id" value="<%=user.getId()%>">
                                                <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                <button type="submit" class="btn btn-danger">DELETE</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </li>
                        <%
                            }
                        %>
                        <li class="list-group-item">
<%--                            <a href="${pageContext.request.contextPath}/settings"><b>Send Message</b></a>--%>
                            <button type="button" class="btn btn-link p-0" data-toggle="modal" data-target="#send_message"><b>Send Message</b></button>

                            <div class="modal fade" id="send_message" tabindex="-1" aria-labelledby="SendMessageModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="SendMessageModalLabel">Send Message to <%=user.getFullName()%>?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form action="/sendmessage" method="post">
                                            <div class="modal-body">
                                                <textarea name="message_text" cols="30" rows="5" class="form-control" required></textarea>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="hidden" name="chat_id" value="0">
                                                <input type="hidden" name="request_sender_id" value="<%=user.getId()%>">
                                                <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                                <button type="submit" class="btn btn-primary">SEND</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout" style="color: crimson"><b>Log Out</b></a></li>
                    </ul>
                </div>
            </div>



            <div class="col-sm-6">

                <h3>Profile of <%=user.getFullName()%></h3>
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
</body>
</html>
