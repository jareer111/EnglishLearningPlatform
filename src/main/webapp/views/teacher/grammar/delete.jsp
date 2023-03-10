<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/fragments/css.jsp"/>
    <title>Delete grammar</title>
</head>
<body>

<form method="post">
    <div class="col-md-5 offset-1">
        <h2 style="text-align: center">
            Are you sure you want to delete this grammar topic?
        </h2>
        <div class="col-12 offset-1">
            <a class="btn btn-secondary" type="submit" href="/teacher/grammar/list"> Back </a>
            <button class="btn btn-danger" type="submit">Yes</button>
        </div>
    </div>
</form>
</body>
</html>
