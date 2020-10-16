<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style><%@include file="assets/style.css"%></style>
    <%@include file="assets/bootstrap.jsp"%>
</head>

<% String theme = (String) request.getAttribute("theme"); %>
<body class="<%=theme%>">
    <%@include file="assets/topnav.jsp"%>
    <div class="container pb-4">
        <div class="row mt-5">
            <div class="col-sm-6 offset-3">
                <h2 class="mb-4 text-center">CREATE NEW ACCOUNT</h2>
                <%
                    String success = request.getParameter("success");
                    if (success != null) {
                %>
                <div class="alert alert-success alert-dismissible fade show mt-5" role="alert">
                    A user registered successfully!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>
                <%
                    String passwordError = request.getParameter("passworderror");
                    if (passwordError != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show mt-5" role="alert">
                    Passwords are not same!
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
                    A user is already exists!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>


                <form action="${pageContext.request.contextPath}/register" class="form-group" method="post">
                    <div class="form-group">
                        <label>Email : </label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    <div class="form-group">
                        <label>Password : </label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>Re-Password : </label>
                        <input type="password" class="form-control" name="re_password" required>
                    </div>
                    <div class="form-group">
                        <label>Full Name : </label>
                        <input type="text" class="form-control" name="full_name" required>
                    </div>
                    <div class="form-group">
                        <label>Picture URL : </label>
<%--                        <input type="text" class="form-control" name="picture_url" required>--%>
                        <input type="text" class="form-control" name="picture_url" value="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1.png" required>
                    </div>
                    <div class="form-group">
                        <label>Date of Birth : </label>
                        <input type="date" class="form-control" name="birthdate" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary">REGISTER</button>
                        <a href="/login" class="btn btn-lg btn-secondary float-right">LOGIN</a>
                    </div>
                </form>
            </div>
        </div>

    </div>


<%--    <%@include file="assets/footer.jsp"%>--%>
</body>
</html>
