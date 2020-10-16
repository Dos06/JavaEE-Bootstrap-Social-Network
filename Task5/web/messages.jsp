<%@ page import="java.util.ArrayList" %>
<%@ page import="javaee.db.Message" %>
<%@ page import="javaee.db.DBManager" %>
<%@ page import="javaee.db.Chat" %>
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

                <%
                Chat chat = (Chat) request.getAttribute("chat");
                ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messages");
                String userFullName = DBManager.getChat(Integer.parseInt(request.getParameter("id"))).getUser().getFullName();
                String opponentFullName = DBManager.getChat(Integer.parseInt(request.getParameter("id"))).getOpponent().getFullName();
                if (messages != null && messages.size() > 0) {
                %>
                <h4 class="text-center">Dialogue with
                    <%=userFullName.equals(currentUser.getFullName()) ? opponentFullName : userFullName%>
                </h4>
                <div class="row card mt-4">
                <%
                    for (Message message : messages) {
                        if (currentUser.getId() != message.getUser().getId()) {
                            DBManager.saveMessageSetReadByReceiver(message.getId());
                        }
                %>
                    <div class="row no-gutters p-2" style="background-color: aliceblue">
                        <div class="col-md-3 my-auto px-1">
                            <img src="<%=message.getUser().getPictureURL()%>" class="card-img rounded-circle" style="height: 100px; width: 100px; max-width: 100px; max-height: 100px;">
                        </div>
                        <div class="col-md-9">
                            <div class="card-body">
                                <a href="/profile?id=<%=message.getUser().getId()%>" class="row justify-content-between">
                                    <h5 class="card-title"><%=message.getUser().getFullName()%></h5>
                                    <h6 class="card-title"><%=message.getSentTime()%></h6>
                                </a>
                                <p class="card-text"><%=message.getMessageText()%></p>
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

                <form action="/sendmessage" method="post" class="row form-group mt-4 justify-content-center" style="padding: 20px 10px; border: 1px solid lightgrey; border-radius: 5px;">
                    <div class="form-row col-sm-12">
                        <input type="hidden" name="chat_id" value="<%=chat.getId()%>">
                        <input type="hidden" name="request_sender_id" value="<%=currentUser.getId() == chat.getUser().getId() ? chat.getOpponent().getId() : chat.getUser().getId()%>">
                        <input type="hidden" name="user_id" value="<%=currentUser.getId() == chat.getUser().getId() ? chat.getUser().getId() : chat.getOpponent().getId()%>">
                        <input type="text" class="form-control col-sm-9 mr-sm-2" name="message_text" placeholder="Message" aria-label="Send" required>
                        <button class="btn btn-md btn-outline-primary col"><b>Send</b></button>
                    </div>
                </form>


<%--                <%--%>
<%--                    if (messages != null) {--%>
<%--                        for (Message message : messages) {--%>
<%--                %>--%>

<%--                <div class="row card mt-4">--%>
<%--                    <div class="row no-gutters p-2">--%>
<%--                        <div class="col-md-3 my-auto px-1">--%>
<%--                            <img src="<%=chat.getOpponent().getPictureURL()%>" class="card-img rounded-circle">--%>
<%--                        </div>--%>
<%--                        <div class="col-md-9">--%>
<%--                            <div class="card-body">--%>
<%--                                <a href="/chats?id=<%=chat.getId()%>" class="row justify-content-between">--%>
<%--                                    <h5 class="card-title"><%=chat.getOpponent().getFullName()%></h5>--%>
<%--                                    <h6 class="card-title"><%=chat.getLatestMessageTime()%></h6>--%>
<%--                                </a>--%>
<%--                                <p class="card-text"><%=chat.getLatestMessageText()%></p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <%--%>
<%--                        }--%>
<%--                    }--%>
<%--                %>--%>
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
