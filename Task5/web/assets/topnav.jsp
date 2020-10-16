<style><%@include file="/assets/style.css"%></style>
<%@include file="user.jsp"%>
<script src="https://cdn.tiny.cloud/1/w84xzg4ab3e6insllhbg3eue66xej8h4y6gu7rok0jaifre8/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<%--<script>tinymce.init({selector: 'textarea'});--%>
<%--</script>--%>
<%--<nav class="navbar navbar-expand-lg navbar-dark sticky-top <%=theme%>">--%>
<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: #031c6e;">
    <div class="container justify-content-between">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><img src="https://i.pinimg.com/originals/dd/c0/84/ddc08492cc7689f8665b9f74d3500ac8.png" alt="logo" width="30" height="30"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#publications" aria-controls="publications" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <%
                    if (!ONLINE) {
                %>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/register" class="nav-link">Register</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/login" class="nav-link">Login</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/faq" class="nav-link">FAQ</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/about" class="nav-link">About</a></li>
                <%
                    }
                    else {
                %>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/profile" class="nav-link"><%=currentUser.getFullName()%></a></li>--%>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/logout" class="nav-link">Log Out</a></li>--%>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link">Feed</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/friends" class="nav-link">Friends</a></li>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/groups" class="nav-link">Groups</a></li>--%>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/my_posts" class="nav-link">My Posts</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/chats" class="nav-link">Messages</a></li>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/pictures" class="nav-link">Pictures</a></li>--%>
<%--                <li class="nav-item"><a href="${pageContext.request.contextPath}/videos" class="nav-link">Videos</a></li>--%>
                <%
                    }
                %>
            </ul>
        </div>
    </div>

</nav>
