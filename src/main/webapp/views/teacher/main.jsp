<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/19/2023
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher</title>
    <jsp:include page="/fragments/css.jsp"/>
    <style>
        .wrapper {
            width: 800px;
            margin: 150px auto;
            padding: 20px;
            border-radius: 10px;
            background: white;
            box-shadow: 0px 10px 40px 0px rgba(47, 47, 47, .1);
        }
    </style>

</head>
<body style="background-color: beige">

<div class="wrapper">
    <div style="text-align: center">
        <h1 style="color: #1d2124">Welcome to teacher page!</h1>
    </div>

    <div style="text-align: center">
        <a type="button" class="btn btn-primary btn-lg" href="/teacher/grammar/list">See grammar page</a>
        <a type="button" class="btn btn-primary btn-lg" href="/teacher/story/list">See story page</a>
    </div>
</div>

</body>
</html>
