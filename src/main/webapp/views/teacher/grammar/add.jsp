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

<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Fill in the form to add new topic</h1>

<form class="row g-3" method="post" enctype="multipart/form-data">
    <div class="col-md-5 offset-1">
        <label for="validationServer01" class="form-label">Topic title</label>
        <input type="text" class="form-control is-valid" id="validationServer01" name="title"
               placeholder="Enter title of the new topic " required>
        <div class="valid-feedback">
            Looks good!
        </div>
    </div>

    <div class="col-md-4 offset-1">
        <label for="validationServer03" class="form-label">Points</label>
        <input type="number" class="form-control is-valid" id="validationServer03" name="score"
               placeholder="enter points" required>
    </div>


    <div class="col-md-4 offset-1">
        <label for="validationServer04" class="form-label">Select level</label>
        <select name="level" id="validationServer04" required>
            <c:forEach items="${levels}" var="level">
                <option value="${level}">${level}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-3 offset-1">
        <label for="file" class="form-label">Upload the PDF file of the theme</label>
        <input type="file" class="form-control" id="file" name="file" aria-label="file example" required>
        <div class="invalid-feedback">Example invalid form file feedback</div>
    </div>

    <h4 style="text-align: center">Add question related to the topic</h4>

    <div class="wrapper" style="background-color: white">

        <label for="question" class="form-label">Insert question</label>
        <textarea style="background-color: whitesmoke" type="text" class="b" id="question" name="question" placeholder="Enter question" required></textarea>
    </div>
    <div class="wrapper" style="background-color: white">
        <label for="question" class="form-label">Enter options here</label>

        <input style="background-color: whitesmoke" type="text" class="b" id="option1" name="option1" placeholder="Enter option 1" required>
        <input style="background-color: whitesmoke" type="text" class="b" id="option2" name="option2" placeholder="Enter option 2" required>
        <input style="background-color: whitesmoke" type="text" class="b" id="option3" name="option3" placeholder="Enter option 3" required>
        <input style="background-color: whitesmoke" type="text" class="b" id="option4" name="option4" placeholder="Enter option 4" required>
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
        <button class="btn btn-primary" type="submit">Add topic</button>
    </div>
</form>
</body>
</html>