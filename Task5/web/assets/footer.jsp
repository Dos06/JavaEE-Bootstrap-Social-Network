<!DOCTYPE html>
<%@ page import="javaee.db.Language" %>
<%@ page import="javaee.db.DBManager" %>
<%@ page import="java.util.ArrayList" %>

<%@include file="/assets/bootstrap.jsp"%>
<style><%@include file="/assets/style.css"%></style>
<div class="container">
    <nav class="navbar fixed-bottom navbar-expand-sm footer <%=theme%>">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <form class="form-inline my-2 my-lg-0 mx-auto" action="${pageContext.request.contextPath}/setCookieLanguage" method="post">
                <select class="form-control mr-sm-2" name="language" required>
                    <option class="form-control" disabled selected>Choose Language</option>
                    <%
                        ArrayList<Language> languages = DBManager.getLanguages();
                        if (languages.size() > 0) {
                            for (Language language : languages) {
                    %>
                    <option class="form-control" value="<%=language.getCode()%>"><%=language.getName()%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <%--                        <input type="text" class="form-control mr-sm-2" placeholder="Search" aria-label="Search">--%>
                <button class="btn btn-outline-success my-2 my-sm-0">Change</button>
            </form>

            <form class="form-inline my-2 my-lg-0 mx-auto" action="${pageContext.request.contextPath}/setCookieTheme" method="post">
                <select class="form-control mr-sm-2" name="theme" required>
                    <option class="form-control" disabled selected>Choose Theme</option>
                    <%
                        ArrayList<String> themes = new ArrayList<>();
                        themes.add("theme-standard");
                        themes.add("theme-dark");
                        themes.add("theme-light");
                        themes.add("theme-barbie");
                        themes.add("theme-happy");
                        themes.add("theme-monochrome");
                        themes.add("theme-monochrome-inverse");
                        themes.add("theme-large");
                        themes.add("theme-small");
                        if (themes.size() > 0) {
                            for (String th : themes) {
                    %>
                    <option class="form-control" value="<%=th%>"><%=th.substring(0,1).toUpperCase() + th.substring(1).toLowerCase()%></option>
                    <%
                            }
                        }
                    %>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0">Change</button>
            </form>
        </div>
    </nav>
</div>

