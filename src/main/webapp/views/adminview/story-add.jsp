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
    <title>Add story</title>
    <jsp:include page="/fragments/css.jsp"/>
    <link rel="stylesheet" href="C:\pdp\BOOTCAMP\jakarta\English-Learning-Platform\src\main\webapp\style.css">
    <style>
        .a{
            background-color: #f0f5ff;
            font-family: 'Jost', sans-serif;
            color: #fff;
        }

        .wrapper{
            width: 400px;
            margin: 40px auto;
            padding: 10px;
            border-radius: 5px;
            background: white;
            box-shadow: 0px 10px 40px 0px rgba(47, 47, 47, .1);
        }

        .b[type="text"]{
            padding: 10px;
            margin: 10px auto;
            display: block;
            border-radius: 5px;
            border: 1px solid lightgrey;
            background: none;
            width: 274px;
            color: black;
        }

        input[type="text"]:focus{
            outline: none;
        }
        .controls{
            width: 294px;
            margin: 15px auto;
        }
        #remove_fields{
            float: right;
        }
        .controls a i.fa-minus{
            margin-right: 5px;
        }

        a{
            color: black;
            text-decoration: none;
        }
        h1{
            text-align: center;
            font-size: 48px;
            color: #232c3d;
        }
    </style>
</head>
<body>

<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Fill in the story form</h1>

<form class="row g-3" method="post" enctype="multipart/form-data">
    <div class="col-md-5 offset-1">
        <label for="validationServer01" class="form-label">Story title</label>
        <input type="text" class="form-control is-valid" id="validationServer01" name="title"
               placeholder="Enter title of the new story " required>
        <div class="valid-feedback">
            Looks good!
        </div>
    </div>
    <div class="col-md-4 offset-1">
        <label for="validationServer02" class="form-label">Author(s)</label>
        <input type="text" class="form-control is-valid" id="validationServer02" name="author"
               placeholder="enter author(s) name" required>
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
        <label for="file" class="form-label">Upload the PDF file of the story</label>
        <input type="file" class="form-control" id="file" name="file" aria-label="file example" required>
        <div class="invalid-feedback">Example invalid form file feedback</div>
    </div>

    <h2 style="text-align: center">Add words</h2>

    <div class="wrapper">
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
        <button class="btn btn-primary" type="submit">Add story</button>
    </div>
</form>


<script>
    var en_options=document.getElementById('en_options');
    var add_more_fields=document.getElementById('add_more_fields');
    var remove_fields=document.getElementById('remove_fields');

    add_more_fields.onclick=function(){
        var newField=document.createElement('input');
        newField.setAttribute('type', 'text');
        newField.setAttribute('name', 'en_options[]');
        newField.setAttribute('class', 'en_options');
        newField.setAttribute('size', 50);
        newField.setAttribute('placeholder', 'english word');
        en_options.appendChild(newField);

        var newField2=document.createElement('input');
        newField2.setAttribute('type', 'text');
        newField2.setAttribute('name', 'uz_options[]');
        newField2.setAttribute('class', 'survey_options');
        newField2.setAttribute('size', 50);
        newField2.setAttribute('placeholder', 'uzbek word');

        uz_options.appendChild(newField2);
    }

    remove_fields.onclick=function(){
        var en_tags=en_options.getElementsByTagName('input');
        var uz_tags=uz_options.getElementsByTagName('input');
        if(en_tags.length>0){
            en_options.removeChild(en_tags[(en_tags.length) - 1]);
            uz_options.removeChild(uz_tags[(uz_tags.length) - 1]);
        }
    }

</script>

</body>
</html>
