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
    <title>Add grammar</title>
    <jsp:include page="/fragments/css.jsp"/>
    <link rel="stylesheet" href="C:\pdp\BOOTCAMP\jakarta\English-Learning-Platform\src\main\webapp\style.css">
    <style>
        .a {
            background-color: #f0f5ff;
            font-family: 'Jost', sans-serif;
            color: #fff;
        }

        .wrapper1 {
            width: 800px;
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

<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Enter new question</h1>
<h4 style="text-align: center">Add question related to the topic</h4>

<form class="row g-3" method="post" action="/teacher/grammar/add-question/${grammar.getId()}">

    <div class="offset-1">
        <label for="question">Insert question here</label>
        <input type="text" class="form-control" id="question" name="question" placeholder="Enter question">
    </div>

    <div class="wrapper1 offset-1">
        <label for="answer1">Insert answer here</label>
        <input type="text" class="form-control" id="answer1" name="option1" placeholder="option 1">
        <input type="text" class="form-control" id="answer2" name="option2" placeholder="option 2">
        <input type="text" class="form-control" id="answer3" name="option3" placeholder="option 3">
        <input type="text" class="form-control" id="answer4" name="option4" placeholder="option 4">
    </div>
    <div class="wrapper1 offset-1">
        <label for="correctAnswer" class="form-label">Choose correct answer</label>
        <select id="correctAnswer" name="correctAnswer" required >
            <option selected="true" disabled="disabled">Select a Option</option>
            <option value="1">Option 1</option>
            <option value="2">Option 2</option>
            <option value="3">Option 3</option>
            <option value="4">Option 4</option>
        </select>
    </div>

    <div class="col-12 offset-1">
        <a class="btn btn-secondary" href="/teacher/grammar/list" type="submit"> Back </a>
        <button class="btn btn-primary" type="submit">Add question</button>
    </div>
</form>
</body>
</html>