<%@ page import="java.util.List" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Grammar" %><%--
  Created by IntelliJ IDEA.
  User: javohir
  Date: 2/19/2023
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <title>Grammars</title>

</head>
<body>
<div class="row offset-1">
    <table class="table table-striped">
        <tbody>
        <%
            List<Grammar> grammars = (List<Grammar>) session.getAttribute("grammars");
            if (grammars == null) {
                grammars = new ArrayList<>();
            }
            if (grammars.size() == 0) {
        %>
        <tr>
            <td colspan="6" style="text-align: center"><h2>No grammars found</h2></td>
        </tr>
        <div class="col-12 offset-1">
            <a href="/teacher/grammar/add" class="btn btn-primary" type="submit"> Add </a>
        </div>
        <%} else {%>
        <h2 style="text-align: center; color: #0f5132; background-color: #9ec5fe; align-self: center">Grammars</h2>
        <div class="col-12 offset-1">
            <a href="/teacher/grammar/add" class="btn btn-primary" type="submit"> Add </a>
        </div>
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">Score</th>
            <th scope="col">Level</th>
            <th scope="col">Created At</th>
        </tr>
        </thead>
        <%for (Grammar grammar : grammars) {%>
        <tr>
            <th>${i=i+1}</th>
            <td><%=grammar.getTitle()%>
            </td>
            <td><%=grammar.getScore()%>
            </td>
            <td><%=grammar.getLevel()%>
            </td>
            <td><%=grammar.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))%>
            </td>
            <td>
                <a class="btn btn-warning" href="/teacher/grammar/update/<%=grammar.getId()%>">Update</a> ||
                <a class="btn btn-danger" href="/teacher/grammar/delete/<%=grammar.getId()%>">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <div class="col-12 offset-1">
        <a href="/teacher" class="btn btn-primary" type="submit"> Back </a>
    </div>
</div>
</body>
</html>
