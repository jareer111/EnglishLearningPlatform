<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Grammar" %>
<%@ page import="com.jareer.englishlearningplatform.servlets.user.story.StoryServlet" %><%--
  Created by IntelliJ IDEA.
  User: manguberdi
  Date: 15/02/23
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grammar</title>
    <jsp:include page="/fragments/bootstrapcss.jsp"/>
</head>

<style>
    h1 {
        margin: 0;
        padding: 32px;
        font-size: 32px;
        font-weight: 400;
        text-align: center;
        color: greenyellow;
        background-color: slategrey;
    }

    section {
        margin: 0 auto;
        padding: 0 16px;
        max-width: 1200px;
        background-color: white;
    }


    .input,
    .output {
        display: flex;
        flex-direction: column;
        flex: 1 1 320px;
        margin: 0 16px;
    }

    label {
        margin: 24px 0 0;
    }

    textarea {
        resize: horizontal;
    }


</style>
<style type="text/css">
    #pdf_container {
        text-align: center;
        display: flex;
        padding: 5px;
        overflow: auto
    }
</style>

<body onload="LoadPdfFromUrl('${file}')" style="background-color: #F3F3F3">

<div class="container-fluid fs-5 col-md-10 offset-1">
    <div class="row mb-2">
        <nav class="navbar navbar-expand-lg navbar-light bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">English learning platform</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Practice
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/reading">Reading</a></li>
                                <li><a class="dropdown-item" href="/vocabulary">Vocabulary</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div id="pdf_container" class="col-md-10 offset-1 bg-body-secondary col-dialog-scrollablesm-11 m-4"></div>
    </div>
    <div class="row ">
        <form method="post" action="/grammar">
            <input type="hidden" value="${grammarId}" name="grammarId">
            <button type="submit" class="btn btn-outline-primary text-center col-md-4 offset-4">
                Take test
            </button>
        </form>
    </div>
</div>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf_viewer.min.css" rel="stylesheet"
      type="text/css"/>

<script type="text/javascript">

    var pdfjsLib = window['pdfjs-dist/build/pdf'];
    pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf.worker.min.js';
    var pdfDoc = null;
    var scale = 2.8;
    if (IsMobile()) {
        scale = 1.7
    }
    var resolution = IsMobile() ? 1.5 : 1; //Set Resolution as per Desktop and Mobile.

    function LoadPdfFromUrl(url) {
        //Read PDF from URL.
        pdfjsLib.getDocument(url).promise.then(function (pdfDoc_) {
            pdfDoc = pdfDoc_;

            //Reference the Container DIV.
            var pdf_container = document.getElementById("pdf_container");
            pdf_container.style.display = "block";
            pdf_container.style.height = IsMobile() ? "1200px" : "820px";

            //Loop and render all pages.
            for (var i = 1; i <= pdfDoc.numPages; i++) {
                RenderPage(pdf_container, i);
            }
        });
    };

    function RenderPage(pdf_container, num) {
        pdfDoc.getPage(num).then(function (page) {
            //Create Canvas element and append to the Container DIV.
            var canvas = document.createElement('canvas');
            canvas.id = 'pdf-' + num;
            ctx = canvas.getContext('2d');
            pdf_container.appendChild(canvas);

            //Create and add empty DIV to add SPACE between pages.
            var spacer = document.createElement("div");
            spacer.style.height = "20px";
            pdf_container.appendChild(spacer);

            //Set the Canvas dimensions using ViewPort and Scale.
            var viewport = page.getViewport({scale: scale});
            canvas.height = resolution * viewport.height;
            canvas.width = resolution * viewport.width;

            //Render the PDF page.
            var renderContext = {
                canvasContext: ctx,
                viewport: viewport,
                transform: [resolution, 0, 0, resolution, 0, 0]
            };

            page.render(renderContext);
        });
    };

    function IsMobile() {
        var r = new RegExp("Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini");
        return r.test(navigator.userAgent);
    }


</script>

<jsp:include page="/fragments/bootstrapjs.jsp"/>
</body>
</html>