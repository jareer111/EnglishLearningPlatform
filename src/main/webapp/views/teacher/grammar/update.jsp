<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/15/2023
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update grammar</title>
    <jsp:include page="/fragments/css.jsp"/>
    <link rel="stylesheet" href="C:\pdp\BOOTCAMP\jakarta\English-Learning-Platform\src\main\webapp\style.css">
    <style>
        .a {
            background-color: #f0f5ff;
            font-family: 'Jost', sans-serif;
            color: #fff;
        }

        .wrapper {
            width: 400px;
            margin: 40px auto;
            padding: 10px;
            border-radius: 5px;
            background: white;
            box-shadow: 0px 10px 40px 0px rgba(47, 47, 47, .1);
        }

        .b[type="text"] {
            padding: 10px;
            margin: 10px auto;
            display: block;
            border-radius: 5px;
            border: 1px solid lightgrey;
            background: none;
            width: 274px;
            color: black;
        }

        input[type="text"]:focus {
            outline: none;
        }

        .controls {
            width: 294px;
            margin: 15px auto;
        }

        #remove_fields {
            float: right;
        }

        .controls a i.fa-minus {
            margin-right: 5px;
        }

        a {
            color: black;
            text-decoration: none;
        }

        h1 {
            text-align: center;
            font-size: 48px;
            color: #232c3d;
        }
    </style>
</head>
<body style="background-color: whitesmoke">


<form class="row g-3" method="post" enctype="multipart/form-data">
    <c:if test="${not empty grammar}">
        <h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Change fields that you want to edit in
            grammar topic</h1>
        <div class="col-md-5 offset-1">
            <label for="validationServer01" class="form-label">Topic title</label>
            <input type="text" class="form-control is-valid" id="validationServer01" name="title"
                   placeholder="Enter title of the new topic " required value="${grammar.getTitle()}">
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>

        <div class="col-md-4 offset-1">
            <label for="validationServer03" class="form-label">Points</label>
            <input type="number" class="form-control is-valid" id="validationServer03" name="score"
                   placeholder="enter points" required value="${grammar.getScore()}">
        </div>

        <div class="col-md-4 offset-1">
            <label for="validationServer04" class="form-label">Select level</label>
            <select name="level" id="validationServer04" required>
                <option>${grammar.getLevel()}</option>
                <c:forEach items="${levels}" var="level">
                    <option value="${level}">${level}</option>
                </c:forEach>

            </select>
        </div>
        <div class="col-md-3 offset-1">
            <label for="file" class="form-label">Upload the PDF file of the theme</label>
            <input type="file" class="form-control" id="file" name="file" aria-label="file example">
            <div class="invalid-feedback">Example invalid form file feedback</div>
        </div>


        <div class="col-12 offset-1">
            <a class="btn btn-secondary" type="submit" href="/teacher/grammar/list"> Back </a>
            <a class="btn btn-success" href="/teacher/grammar/add-question/${grammar.getId()}">Add question</a>
            <a class="btn btn-danger" type="submit" href="/teacher/grammar/delete/${grammar.getId()}"> Delete topic </a>
            <button class="btn btn-primary" type="submit">Update topic</button>
        </div>
    </c:if>
    <c:if test="${empty grammar}">
        <div class="alert alert-warning" role="alert">
            <h2 style="text-align: center">Grammar not found!</h2>
        </div>
    </c:if>

    <c:if test="${not empty questions && questions.size()>0}">
        <h3 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Questions of this topic</h3>
        <table class="table table-striped" style="margin-left: 50px">
            <thead>
            <tr>
                <th></th>
                <th>Question</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td>${i=i+1}</td>
                    <td><h3>${question.getTitle()}</h3></td>
                    <td>
                        <a class="btn btn-warning" href="/teacher/grammar/update-question/${question.getId()}">Edit</a>
                        <a class="btn btn-danger" href="/teacher/grammar/delete-question/${question.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty questions && questions.size()>0}">
        <div class="alert alert-info" role="alert">
            <h2 style="text-align: center">No questions added yet</h2>
        </div>
    </c:if>

</form>
</body>
</html>