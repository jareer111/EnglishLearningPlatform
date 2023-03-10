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
    <title>Grammar Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css"/>
</head>
<body>


<div class="container mt-5">
    <table id="example" class="table table-striped" style="width:100%">
        <c:if test="${grammars.size() == 0}">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">No grammars found!</h4>
                <p>There are no grammars in the database. Please add grammars to the database.</p>
                <hr>
                <p class="mb-0">If you are the teacher, please add grammars to the database.</p>
            </div>
        </c:if>
        <c:if test="${grammars.size() != 0}">
            <thead>
            <tr>
                <th>№</th>
                <th>Title</th>
                <th>Level</th>
                <th>Score</th>
                <th>Test</th>
                <th>Grammar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${grammars}" var="grammar">
                <tr>
                    <td>${i=i+1}</td>
                    <td id="title">${grammar.getTitle()}</td>
                    <td>${grammar.getLevel()}</td>
                    <td>
                            ${grammar.getScore()}
                    </td>
                    <td>
                        <form action="/grammar/test" method="get">
                            <input type="hidden" name="grammarId" value="${grammar.getId()}">
                            <button type="submit" class="btn btn-primary">Start test</button>
                        </form>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="/grammar">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </c:if>

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