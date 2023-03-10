<%--
  Created by IntelliJ IDEA.
  User: javohir
  Date: 21/02/2023
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<html>
<head>
    <title>Vocabulary Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css"/>
</head>
<body>


<div class="container mt-5">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
        <tr>
            <th>№</th>
            <th>Title</th>
            <th>Count</th>
            <th>Test</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${helperList}" var="helper">

            <tr>
                <td>${i=i+1}</td>
                <td id="title">${helper.getStoryTitle()}</td>
                <td>${helper.getWordsCount()}</td>
                <input type="hidden" name="userId" id="user_id" value="${user_id}">
                <input type="hidden" name="userId" id="story_id" value="${helper.getStoryId()}">
                <td>
                    <a class="btn btn-primary" href="vocabulary/test/${helper.getStoryId()}" >Start</a>
                </td>
            </tr>

                </c:forEach>
        </tbody>
    </table>
</div>


<script src="/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script>

<script>

    $(document).ready(function () {
        $('#example').DataTable()
    })
</script>
</body>
</html>