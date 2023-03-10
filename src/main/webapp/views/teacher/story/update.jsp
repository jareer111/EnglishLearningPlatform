<%@ page import="java.util.List" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Vocabulary" %><%--
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
    <title>Update story</title>
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
<body>

<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Change fields that you want to edit in story</h1>

<form class="row g-3" method="post" enctype="multipart/form-data">
    <div class="col-md-5 offset-1">
        <label for="validationServer01" class="form-label">Story title</label>
        <input type="text" class="form-control is-valid" id="validationServer01" name="title"
               placeholder="Enter title of the new story " required value="${story.getTitle()}">
        <div class="valid-feedback">
            Looks good!
        </div>
    </div>
    <div class="col-md-4 offset-1">
        <label for="validationServer02" class="form-label">Author(s)</label>
        <input type="text" class="form-control is-valid" id="validationServer02" name="author"
               placeholder="enter author(s) name" required value="${story.getAuthor()}">
        <div class="valid-feedback">
            Looks good!
        </div>
    </div>
    <div class="col-md-4 offset-1">
        <label for="validationServer03" class="form-label">Points</label>
        <input type="number" class="form-control is-valid" id="validationServer03" name="score"
               placeholder="enter points" required value="${story.getScore()}">
    </div>


    <div class="col-md-4 offset-1">
        <label for="validationServer04" class="form-label">Select level</label>
        <select name="level" id="validationServer04" required>
            <option>${story.getLevel()}</option>
            <c:forEach items="${levels}" var="level">
                <option value="${level}">${level}</option>
            </c:forEach>
        </select>

    </div>

    <div class="col-md-3 offset-1">
        <label for="file" class="form-label">Upload the PDF file of the story</label>
        <input value="${story.getDocument().getFilePath()}" type="file" class="form-control" id="file" name="file"
               aria-label="file example">
        <div class="invalid-feedback">Example invalid form file feedback</div>
    </div>


    <%
        List<Vocabulary> list = (List<Vocabulary>) session.getAttribute("vocabularies");
        if (list != null && list.size() > 0) {%>
    <h2 style="text-align: center">Words added</h2>
    <table class="table table-striped offset-1">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">English</th>
            <th scope="col">Uzbek</th>
        </tr>
        </thead>
        <%for (Vocabulary vocabulary : list) {%>
        <tr>
            <th>${i=i+1}</th>
            <td><%=vocabulary.getWord()%>
            </td>
            <td><%=vocabulary.getMeaning()%>
            </td>
            <td>
                <a class="btn btn-danger" href="/teacher/story/word/delete/<%=vocabulary.getId()%>">Delete</a>
            </td>
        </tr>
        <%
            } %>
    </table>
    <%
    } else {
    %>
    <h2 style="text-align: center">No words added yet</h2>
    <%}%>


    <div class="wrapper">

        <h2 style="text-align: center">Add words</h2>
        <div id="en_options" class="b">

        </div>
        <div id="uz_options" class="b">

        </div>
        <div class="controls">
            <a href="#" id="add_more_fields"><i class="fa fa-plus">Add more</i></a>
            <a href="#" id="remove_fields"><i class="fa fa-plus">Remove</i></a>
        </div>
    </div>


    <div class="col-12 offset-1">
        <a class="btn btn-secondary" type="submit" href="/teacher/story/list"> Back </a>
        <a class="btn btn-danger" type="submit" href="/teacher/story/delete/${story.getId()}">Delete story</a>
        <button class="btn btn-primary" type="submit">Update story</button>
    </div>
</form>


<script>
    var en_options = document.getElementById('en_options');
    var add_more_fields = document.getElementById('add_more_fields');
    var remove_fields = document.getElementById('remove_fields');

    add_more_fields.onclick = function () {
        var newField = document.createElement('input');
        newField.setAttribute('type', 'text');
        newField.setAttribute('name', 'en_options[]');
        newField.setAttribute('class', 'en_options');
        newField.setAttribute('size', 50);
        newField.setAttribute('placeholder', 'english word');
        en_options.appendChild(newField);

        var newField2 = document.createElement('input');
        newField2.setAttribute('type', 'text');
        newField2.setAttribute('name', 'uz_options[]');
        newField2.setAttribute('class', 'survey_options');
        newField2.setAttribute('size', 50);
        newField2.setAttribute('placeholder', 'uzbek word');

        uz_options.appendChild(newField2);
    }

    remove_fields.onclick = function () {
        var en_tags = en_options.getElementsByTagName('input');
        var uz_tags = uz_options.getElementsByTagName('input');
        if (en_tags.length > 0) {
            en_options.removeChild(en_tags[(en_tags.length) - 1]);
            uz_options.removeChild(uz_tags[(uz_tags.length) - 1]);
        }
    }

</script>

</body>
</html>
