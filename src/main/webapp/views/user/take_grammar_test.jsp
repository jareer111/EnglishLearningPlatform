<%@ page import="java.util.List" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Variants" %>
<%@ page import="com.jareer.englishlearningplatform.servlets.user.story.StoryServlet" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.jareer.englishlearningplatform.domains.QuizHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dilgan
  Date: 17/02/23
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Font Awesome -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
        rel="stylesheet"
/>
<!-- Google Fonts -->
<link
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
        rel="stylesheet"
/>
<!-- MDB -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
        rel="stylesheet"
/>
<html>
<head>
    <title>Grammar Test</title>

</head>
<style>

    * {
        box-sizing: border-box;
    }

    body {
        background-color: #b8c6db;
        background-image: linear-gradient(315deg, #b8c6db 0%, #f5f7f7 100%);
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        overflow: hidden;
        margin: 0;
    }

    .quiz-container {
        background-color: #fff;
        box-shadow: 0 0 10px 2px rgba(100, 100, 100, 0.1);
        width: 600px;
        overflow: hidden;
        text-decoration-color: black;
    }

    .quiz-header {
        padding: 4rem;
    }

    h2 {
        padding: 1rem;
        text-align: center;
        margin: 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    ul li {
        font-size: 1.2rem;
        margin: 1rem 0;
    }

    ul li label {
        cursor: pointer;
    }

    button {
        background-color: #03cae4;
        color: #fff;
        border: none;
        display: block;
        width: 100%;
        cursor: pointer;
        font-size: 1.1rem;
        font-family: inherit;
        padding: 1.3rem;
    }

    button:hover {
        background-color: #04adc4;
    }

    button:focus {
        outline: none;
        background-color: #44b927;
    }
</style>
<body id="body" onload="quizData()">

<div class="quiz-container" id="quiz">
    <div class="quiz-header">
        <h2 id="question">Question Text <input type="hidden" name="questionId" id="questionId"></h2>
        <ul>
            <li>
                <input type="radio" name="answer" id="a" class="answer">
                <label for="a" id="a_text">Answer</label>
            </li>
            <li>
                <input type="radio" name="answer" id="b" class="answer">
                <label for="b" id="b_text">Answer</label>
            </li>
            <li>
                <input type="radio" name="answer" id="c" class="answer">
                <label for="c" id="c_text">Answer</label>
            </li>
            <li>
                <input type="radio" name="answer" id="d" class="answer">
                <label for="d" id="d_text">Answer</label>
            </li>
        </ul>
    </div>

    <input type="hidden" id="grammar_id" name="grammarId" value="${grammarId}">
    <input type="hidden" id="user_id" name="user_id" value="${userId}">
    <button id="submit" >Submit</button>

</div>


<script>
    <jsp:include page="js/practise/quiz.js"></jsp:include>
</script>

</body>
</html>
