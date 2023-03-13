<%@ page import="com.jareer.englishlearningplatform.domains.Grammar" %>
<%@ page import="com.jareer.englishlearningplatform.servlets.user.story.StoryServlet" %><%--

  Created by IntelliJ IDEA.
  User: javohir
  Date: 15/02/23
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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

<form class="container-fluid fs-5">
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
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Practice
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/grammar">Grammar</a></li>
                                <li><a class="dropdown-item" href="/vocabulary">Vocabulary</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div id="pdf_container" class="col-lg-7 bg-body-secondary col--dialog-scrollablesm-11 m-4"></div>
        <div class="col-lg-4 bg-body-secondary col-sm-11 mx-4">
            <h1>Translator</h1>
            <section>
                <div class="input  ">
                    <label>
                        <span>English 🇬🇧</span>
                        <textarea id="input" placeholder=" " rows="9" class="form-control  col-md-12 fs-5"
                                  oninput="myFunction()"></textarea>
                    </label>
                </div>
                <div class="output">
                    <label>
                        <span>Uzbek🇺🇿</span>
                        <textarea rows="9" id="output" readonly="readonly"
                                  class="form-control  col-md-12 fs-5">
                        </textarea>
                    </label>
                </div>
                <br>
            </section>
        </div>
    </div>
    <div class="row">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-outline-primary  col-1 mx-4" data-bs-toggle="modal"
                data-bs-target="#staticBackdrop">
            Save story
        </button>
        <button type="button" class="btn btn-outline-info  col-2  offset-1" data-bs-toggle="modal"
                data-bs-target="#myModal">
            Recommended words
        </button>
        <button type="button" class="btn btn-outline-success  col-1  offset-1" data-bs-toggle="modal"
                data-bs-target="#addWordModal">
           Add new word
        </button>
        <button type="button" class="btn btn-outline-success  col-1  offset-1" data-bs-toggle="modal"
                data-bs-target="#exampleModal">
            Finish reading
        </button>
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">

                        <h5 class="modal-title" id="staticBackdropLabel">Finish lesson</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you confirm?
                    </div>
                    <form method="post" action="http://localhost:8080/reading" class="modal-footer d-flex offset-1">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <input value="${storyId}" name="storyId" type="hidden">
                        <input value="${userId}" name="userId" type="hidden">
                        <button type="submit" class="btn btn-success">Yes</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Story feedback</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form method="post" action="http://localhost:8080/reading">

                        <div class="modal-body">

                            <div>
                                <input value="${storyId}" name="storyId" type="hidden">
                                <input value="${userId}" name="userId" type="hidden">
                                <label for="message-text" class="col-form-label">Feedback:</label>
                                <input class="form-control" placeholder="Optional" id="message-text"
                                       name="feedback">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Send message</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <div class="modal fade" id="addWordModal" tabindex="-1" aria-labelledby="wordModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="wordModalLabel">Adding new word</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form method="post" action="http://localhost:8080/reading">
                        <div class="modal-body">

                            <div>
                                <input value="${storyId}" name="storyId" type="hidden">

                                <input value="${true}" name="addWord" type="hidden">
                                <input value="${userId}" name="userId" type="hidden">
                                <label for="message-text" class="col-form-label">English word:</label>
                                <input class="form-control" placeholder="Enter english word" id="eword"
                                       name="eword">
                                <label for="message-text" class="col-form-label">Meaning:</label>
                                <input class="form-control" placeholder="Enter meaning" id="uword"
                                       name="uword">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal" id="myModal">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Recommended words</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <h3>Some text to enable scrolling..</h3>
                        ${vocabularyList}
                        <c:forEach var="vocabluary" items="${vocubuluryList}">
                            <ul>
                                <li>${vocabluary.word}-${vocabluary.meaning}</li>
                            </ul>
                        </c:forEach>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf_viewer.min.css" rel="stylesheet"
      type="text/css"/>

<script type="text/javascript">


    function myFunction() {
        const x = document.getElementById("input").value;
        const url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=uz&dt=t&q=" + x; //console.log(url);
        $.getJSON(url, function (data) {
            $('textarea#output').val(data[0][0][0]);
        });
    }


    var pdfjsLib = window['pdfjs-dist/build/pdf'];
    pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.6.347/pdf.worker.min.js';
    var pdfDoc = null;
    var scale = 2.5;
    if (IsMobile()) {
        scale = 1.7
    }
    var resolution = IsMobile() ? 1.5 : 1; //Set Resolution as per Desktop and Mobile.

    function LoadPdfFromUrl(url) {
        //Read PDF from URL.
        console.log(url)
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