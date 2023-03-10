<%--
  Created by IntelliJ IDEA.
  User: manguberdi
  Date: 21/02/23
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LevelTest</title>
</head>
<body>
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

  <button id="submit" >Submit</button>
</body>
</html>
