<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.FriendRequest" %>
<%@ page import="javaee.db.DBManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Page</title>
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



            <div class="col-sm-6">
                    <form action="/search" class="row form-group" style="padding: 20px 10px; border: 1px solid lightgrey; border-radius: 5px;">
                        <div class="form-row col-sm-12 justify-content-time">
                            <input type="search" class="form-control col-sm-9 mr-sm-2" name="full_name" placeholder="Search Friends" aria-label="Search" required value="<%=request.getParameter("full_name")%>">
                            <button class="btn btn-md btn-outline-primary col"><b>Search</b></button>
                        </div>
                    </form>



                <%
                    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
                    if (users != null && users.size() > 0) {
                %>
                <div class="row card mt-4">
                    <div class="card-header">
                        <h4 class="card-title"><%=users.size()%> users were found</h4>
                    </div>
                <%
                        for (User user : users) {
                %>
                    <div class="row no-gutters p-2" style="background-color: aliceblue">
                        <div class="col-md-3 my-auto px-1">
                            <img src="<%=user.getPictureURL()%>" class="card-img rounded-circle" style="height: 100px; width: 100px; max-width: 100px; max-height: 100px;">
                        </div>
                        <div class="col-md-9">
                            <div class="card-body">
                                <a href="/profile?id=<%=user.getId()%>"><h5 class="card-title"><%=user.getFullName()%></h5></a>
                                <p class="card-text"><%=user.getAge() + " years old"%></p>
                                <%
                                if (DBManager.isFriendRequestSent(currentUser.getId(), user.getId())) {
                                %>
<%--                                <a href="/messages" class="btn btn-sm btn-outline-warning">Request Sent</a>--%>
                                <form action="/deletefriendrequest" method="post" class="ml-1">
                                    <input type="hidden" name="request_sender_id" value="<%=currentUser.getId()%>">
                                    <input type="hidden" name="user_id" value="<%=user.getId()%>">
                                    <button class="btn btn-sm btn-outline-warning">Request Sent</button>
                                </form>
                                <%
                                } else if (DBManager.isFriendRequestSent(user.getId(), currentUser.getId())) {
                                %>
                                <div class="d-flex">
                                    <form action="/addfriend" method="post">
                                        <input type="hidden" name="request_sender_id" value="<%=user.getId()%>">
                                        <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                                        <button class="btn btn-sm btn-outline-primary">Accept</button>
                                    </form>
                                    <form action="/deletefriendrequest" method="post" class="ml-1">
                                        <input type="hidden" name="request_sender_id" value="<%=user.getId()%>">
                                        <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                                        <button class="btn btn-sm btn-outline-danger">Reject</button>
                                    </form>
                                </div>
                                <%
                                } else if (DBManager.areFriends(currentUser.getId(), user.getId())) {
                                %>
<%--                                <a href="/deletefriend" class="btn btn-sm btn-outline-danger">Delete From Friends</a>--%>
                                <form action="/deletefriend" method="post">
                                    <input type="hidden" name="request_sender_id" value="<%=user.getId()%>">
                                    <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                                    <button class="btn btn-sm btn-outline-danger">Delete From Friends</button>
                                </form>
                                <%
                                } else {
                                %>
<%--                                <a href="/addfriendrequest" class="btn btn-sm btn-outline-primary">Add To Friends</a>--%>
                                <form action="/addfriendrequest" method="post">
                                    <input type="hidden" name="request_sender_id" value="<%=currentUser.getId()%>">
                                    <input type="hidden" name="user_id" value="<%=user.getId()%>">
                                    <button class="btn btn-sm btn-outline-primary">Add To Friends</button>
                                </form>
                                <%
                                }
                                %>
                            </div>
                        </div>
                    </div>
                <%
                        }
                %>
                </div>
                <%
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
