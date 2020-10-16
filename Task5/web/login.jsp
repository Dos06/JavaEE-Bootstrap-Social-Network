<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container pb-4">
        <div class="row mt-5">
            <div class="col-sm-6 offset-3">
                <h2 class="mb-4 text-center">LOGIN TO SYSTEM</h2>
                <%
                    String passwordError = request.getParameter("passworderror");
                    if (passwordError != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show mt-5" role="alert">
                    Incorrect password!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>
                <%
                    String emailError = request.getParameter("emailerror");
                    if (emailError != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show mt-5" role="alert">
                    Incorrect email!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>


                <form action="/auth" class="form-group" method="post">
                    <div class="form-group">
                        <label>Email : </label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <div class="form-group">
                        <label>Password : </label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary">LOGIN</button>
                        <a href="/register" class="btn btn-lg btn-secondary float-right">REGISTER</a>
                    </div>
                </form>
            </div>
        </div>

    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>
