<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Chat" %>
<%@ page import="javaee.db.DBManager" %>
<%@ page import="javaee.db.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Friends</title>
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
                        <div class="form-row col-sm-12">
                            <input type="search" class="form-control col-sm-9 mr-sm-2" name="full_name" placeholder="Search Friends" aria-label="Search" required>
                            <button class="btn btn-md btn-outline-primary col-sm-2"><b>Search</b></button>
                        </div>
                    </form>



                <%
                    ArrayList<Chat> chats = (ArrayList<Chat>) request.getAttribute("chats");
                %>

                <%
                    if (chats != null) {
                        for (Chat chat : chats) {
                            String userFullName = chat.getUser().getFullName();
                            String opponentFullName = chat.getOpponent().getFullName();
                            Message latestMessage = DBManager.getMessageByChatIdMessageTextSentTime(chat.getId(), chat.getLatestMessageText(), chat.getLatestMessageTime());
                %>

                <div class="row card mt-4" style="background-color: <%=( currentUser.getId() == latestMessage.getOpponent().getId()  && !latestMessage.isReadByReceiver() ) ? "#d1e1ff" : "white"%>">
                    <div class="row no-gutters p-2">
                        <div class="col-md-3 my-auto px-1">
                            <img src="<%=currentUser.getFullName().equals(userFullName) ? chat.getOpponent().getPictureURL() : chat.getUser().getPictureURL()%>" class="card-img rounded-circle" style="height: 100px; width: 100px; max-width: 100px; max-height: 100px;">
                        </div>
                        <div class="col-md-9">
                            <div class="card-body">
                                <a href="/chats?id=<%=chat.getId()%>" class="row justify-content-between">
                                    <h5 class="card-title"><%=currentUser.getFullName().equals(userFullName) ? opponentFullName : userFullName%></h5>
                                    <h6 class="card-title"><%=chat.getLatestMessageTime()%></h6>
                                </a>
                                <p class="card-text" style="font-weight: <%=( currentUser.getId() == latestMessage.getOpponent().getId()  && !latestMessage.isReadByReceiver() ) ? "bold" : "normal"%>"><%=chat.getLatestMessageText()%></p>
                            </div>
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
