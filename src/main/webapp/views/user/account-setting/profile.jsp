<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.jareer.englishlearningplatform.domains.Users" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: abdullo
  Date: 2/19/23
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="cache-control" content="no-cache">
    <title>Account Settings UI Design</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="views/user/account-setting/css/style.css">

</head>
<body>
<c:set var="firstname" value="${(not empty user.getFirstName())? user.getFirstName() : 'Firstname'}"/>
<c:set var="lastname" value="${(not empty user.getLastName())? user.getLastName() : 'Lastname'}"/>
<c:set var="email" value="${user.getEmail()}"/>
<c:set var="username" value="${user.getUsername()}"/>
<c:set var="score" value="${user.getScore()}"/>
<c:set var="level" value="${user.getLevel().toString()}"/>
<c:set var="user_id" value="${user.getId()}"/>
<section class="py-5 my-5">
    <form method="post" action="/profile">
        <div class="container">
            <h1 class="mb-5">Account Settings</h1>
            <div class="bg-white shadow rounded-lg d-block d-sm-flex">
                <div class="profile-tab-nav border-right">
                    <div class="p-4">
                        <div class="img-circle text-center mb-3">
                            <img src="views/user/account-setting/img/user2.jpg" alt="Image" class="shadow">
                        </div>
                        <h4 class="text-center">${firstname.concat(' '.concat(lastname))}</h4>
                    </div>
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="account-tab" data-toggle="pill" href="#account"
                           role="tab" aria-controls="account" aria-selected="true">
                            <i class="fa fa-home text-center mr-1"></i>
                            Account
                        </a>
                        <a class="nav-link" href="/change-password">
                            <i class="fa fa-key text-center mr-1"></i>
                            Password
                        </a>
                        <a class="nav-link" href="/profile/history">
                            <i class="fa fa-history text-center mr-1"></i>
                            View History
                        </a>
                    </div>
                </div>
                <div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
                        <h3 class="mb-4">Account Settings</h3>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Firstname</label>
                                    <input type="hidden" name="user_id" value="${user_id}">
                                    <input type="text" class="form-control" name="firstname" value="${firstname}">
                                <div>
                                <c:if test="${firstname_err != null}">
                                    <snap class="small text-danger"><c:out value="${firstname_err}"></c:out></snap>
                                </c:if>
                            </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Username</label>
                                    <input type="text" class="form-control" name="username" value="${username}">
                                <div>
                                <c:if test="${username_error != null}">
                                    <snap class="small text-danger"><c:out value="${username_error}"></c:out></snap>
                                </c:if>
                            </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Lastname</label>
                                    <input type="text" class="form-control" name="lastname" value="${lastname}">
                                <div>
                                <c:if test="${lastname_err != null}">
                                    <snap class="small text-danger"><c:out value="${date_error}"></c:out></snap>
                                </c:if>
                            </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="text" class="form-control" name="email" value="${email}">
                                <div>
                                <c:if test="${email_error != null}">
                                    <snap class="small text-danger"><c:out value="${email_error}"></c:out></snap>
                                </c:if>
                            </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Your Current Score</label>
                                    <input readonly type="text" class="form-control disabled" value="${score}">
                                </div>
                            </div>

                            <% if (((Users) request.getSession().getAttribute("user")).getLevel().toString().equals("DEFAULT")) {%>
                            <div class="col-md-6">
                                <div class="form-group">
                                <label style="font-size: smaller; color: red; margin-top: -16px">You have not taken the english assessment test to determine your level. Please take the test!</label>
                                <a href="/assessment" class="btn btn-primary">Take the test</a>
                                </div>
                            </div>

                            <%} else {%>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Your current level</label>
                                    <input readonly type="text" class="form-control" value="${level}">
                                </div>
                            </div>
                            <% } %>

                        </div>
                        <div>
                            <button class="btn btn-primary" type="submit">Update</button>
                            <button class="btn btn-light"><a href="/user">Cancel</a> </button>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                        <h3 class="mb-4">Password Settings</h3>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Old password</label>
                                    <input type="hidden" name="pass_settings">
                                    <input type="password" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>New password</label>
                                    <input type="password" class="form-control">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Confirm new password</label>
                                    <input type="password" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div>
                            <button class="btn btn-primary">Update</button>
                            <button class="btn btn-light">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>

<script>
    window.addEventListener('pageshow', function(event) {
        // Reload the page if it is not coming from the cache
        if (event.persisted) {
            window.location.reload();
        }
    });
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
