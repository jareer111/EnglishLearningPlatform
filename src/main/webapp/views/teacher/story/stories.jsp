<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/19/2023
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>Stories</title>

</head>
<body>
<div class="row offset-1">
    <table class="table table-striped">
        <tbody>
        <%
            List<Story> stories = (List<Story>) session.getAttribute("stories");
            if (stories == null) {
                stories = new ArrayList<>();
            }
            if (stories.size() == 0) {
        %>
        <tr>
            <td colspan="6" style="text-align: center"><h2>No stories found</h2></td>
        </tr>
        <div class="col-12 offset-1">
            <a href="<c:url value="/teacher/story/add"/>" class="btn btn-primary" type="submit"> Add </a>
        </div>
        <%} else {%>
        <h2 style="text-align: center; color: #0f5132; background-color: #9ec5fe; align-self: center">Stories</h2>
        <div class="col-12 offset-1">
            <a href="/teacher/story/add" class="btn btn-primary" type="submit"> Add </a>
        </div>
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Score</th>
            <th scope="col">Level</th>
            <th scope="col">Created At</th>
        </tr>
        </thead>
        <%for (Story story : stories) {%>
        <tr>
            <th>${i=i+1}</th>
            <td><%=story.getTitle()%>
            </td>
            <td><%=story.getAuthor()%>
            </td>
            <td><%=story.getScore()%>
            </td>
            <td><%=story.getLevel()%>
            </td>
            <td><%=story.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))%>
            </td>
            <td>
                <a class="btn btn-warning" href="/teacher/story/update/<%=story.getId()%>">Update</a> ||
                <a class="btn btn-danger" href="/teacher/story/delete/<%=story.getId()%>">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <div class="col-12 offset-1">
        <a href="/teacher" class="btn btn-primary" type="submit">Back</a>
    </div>
</div>
</body>
</html>
