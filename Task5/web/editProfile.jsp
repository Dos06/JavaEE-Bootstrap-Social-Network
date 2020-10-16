<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
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
                    String success = request.getParameter("success");
                    if (success != null) {
                %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    A user updated successfully!
                    <button type="button" class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    }
                %>
                <h2>Edit Profile</h2>
                <form action="${pageContext.request.contextPath}/editProfile" class="form-group" method="post">
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" name="email" required value="<%=currentUser.getEmail()%>">
                    </div>
                    <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" class="form-control" name="full_name" required value="<%=currentUser.getFullName()%>">
                    </div>
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" class="form-control" name="birthdate" required value="<%=currentUser.getSqlBirthdate()%>">
                    </div>
                    <%
                        String emailError = request.getParameter("emailerror");
                        if (emailError != null) {
                    %>
                    <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                        A user is already exists!
                        <button type="button" class="close" data-dismiss="alert" aria-label="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary">UPDATE</button>
                    </div>
                </form>

                <hr>

                <h2>Edit Picture</h2>
                <form action="${pageContext.request.contextPath}/editProfile" class="form-group" method="post">
                    <input type="hidden" name="birthdate" value="<%=currentUser.getSqlBirthdate()%>">
                    <div class="form-group">
                        <label>Picture URL</label>
                        <input type="text" class="form-control" name="picture_url" required value="<%=currentUser.getPictureURL()%>">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary">UPDATE</button>
                    </div>
                </form>

                <hr>

                <h2>Update Password</h2>
                <form action="${pageContext.request.contextPath}/editProfile" class="form-group" method="post">
                    <input type="hidden" name="birthdate" value="<%=currentUser.getSqlBirthdate()%>">
                    <div class="form-group">
                        <label>Old Password</label>
                        <input type="password" class="form-control" name="old_password" required>
                    </div>
                    <div class="form-group">
                        <label>New Password</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>Re-New Password : </label>
                        <input type="password" class="form-control" name="re_password" required>
                    </div>
                    <%
                        String passwordError = request.getParameter("passworderror");
                        if (passwordError != null) {
                    %>
                    <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                        Passwords are not same!
                        <button type="button" class="close" data-dismiss="alert" aria-label="close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary">UPDATE</button>
                    </div>
                </form>
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
